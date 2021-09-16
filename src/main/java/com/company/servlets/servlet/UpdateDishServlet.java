package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateDishServlet extends HttpServlet {
    private AtomicReference<Menu> menu;

    @Override
    public void init() throws ServletException {

        final Object menu = getServletContext().getAttribute("menu");
        if (menu == null ) {

            throw new IllegalStateException("You're dish repo does not initialize! )))))");
        } else {

            this.menu = (AtomicReference<Menu>) getServletContext().getAttribute("menu");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final int cookingTime = Integer.parseInt(req.getParameter("cookingTime"));
        final String type=req.getParameter("type");
        final String imagePath=req.getParameter("imagePath");

        final Dish dish= menu.get().getDishById(Integer.valueOf(id));
        dish.setDishName(name);
        dish.setDishCookingTime(cookingTime);
        dish.setDishType(DishType.valueOf(type));
        dish.setDishImagePath(imagePath);
        resp.sendRedirect(req.getContextPath() + "/dishes_menu_editor");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        Map<Integer,Dish> dishes=new HashMap<>();
        for (Dish element:menu.get().getDishesList()){
            dishes.put(element.getId(),element);
        }
        if (Utils.idDishIsInvalid(id, dishes)) {
            resp.sendRedirect(req.getContextPath() + "/dishes_menu_editor");
            return;
        }
        final Dish dish =dishes.get(Integer.parseInt(id));
        req.setAttribute("dish", dish);
        req.setAttribute("menu", menu.get());
        req.getRequestDispatcher("/WEB-INF/view/update_dish.jsp")
                .forward(req, resp);
    }
}
