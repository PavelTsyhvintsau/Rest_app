package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.kitchen.dishes.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDishServlet extends HttpServlet {
    private Restaurant restaurant;
    @Override
    public void init() throws ServletException {
        final Object restaurant = getServletContext().getAttribute("restaurant");
        if (restaurant == null) {
            throw new IllegalStateException("You're restaurant does not initialize! )))))");
        } else {
            this.restaurant = (Restaurant) getServletContext().getAttribute("restaurant");
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final int cookingTime = Integer.parseInt(req.getParameter("cookingTime"));
        final String type=req.getParameter("type");
        final String imagePath=req.getParameter("imagePath");
        restaurant.updateDish(id,name,type,cookingTime,imagePath);
        resp.sendRedirect(req.getContextPath() + "/dishes_menu_editor");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id =Integer.valueOf(req.getParameter("id"));
        Dish dish =restaurant.getDishFromDB(id);
        req.setAttribute("dish", dish);
        req.setAttribute("menu", restaurant.getMenu());
        req.getRequestDispatcher("/WEB-INF/view/update_dish.jsp").forward(req, resp);
    }
}
