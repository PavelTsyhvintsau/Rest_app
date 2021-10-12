package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.Restaurant;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DishesPriceCorrectingServlet extends HttpServlet {
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("do get DishPriceCorrecting in work");
        req.setAttribute("menu", restaurant.getMenu().get());
        req.getRequestDispatcher("/WEB-INF/view/dishes_price_update.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post DishPriceCorrecting in work");
        req.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        String newPriceStr=req.getParameter("dishNewPrise");
        System.out.println("id="+id+"; newprice="+newPriceStr);
        if(newPriceStr.equals("")){
            newPriceStr="0";}

        int newPrice = 0;

            newPrice = Integer.parseInt(newPriceStr);
            final Dish dish= restaurant.getMenu().get().getDishById(Integer.valueOf(id));
            dish.setPrice(newPrice);

        System.out.println("цена установлена: "+newPrice);
        resp.sendRedirect(req.getContextPath() + "/update_dish_price");
    }
}
