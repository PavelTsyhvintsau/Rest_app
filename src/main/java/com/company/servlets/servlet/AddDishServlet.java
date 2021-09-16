package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AddDishServlet extends HttpServlet {
    private AtomicReference<Menu> menu;
    private AtomicInteger id;
    @Override
    public void init() throws ServletException {
        final Object menu = getServletContext().getAttribute("menu");
        if (menu == null) {

            throw new IllegalStateException("You're menu repo does not initialize! )))))");
        } else {

            this.menu = (AtomicReference<Menu>) getServletContext().getAttribute("menu");
        }
        id = new AtomicInteger(5);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String dishName = req.getParameter("dishName");
        final DishType dishType =DishType.valueOf(req.getParameter("dishType"));
        final int dishCookingTime = Integer.parseInt(req.getParameter("dishCookingTime"));
        final String dishImagePath = req.getParameter("dishImagePath");

        List<String>listNames=new ArrayList<>();
        for(Dish elem:this.menu.get().getDishesList()){
            listNames.add(elem.getDishName());
        }
        if(listNames.contains(dishName)){
            resp.sendRedirect(req.getContextPath()+"/dishes_menu_editor");
        }else {
            final int id = this.id.getAndIncrement();
            menu.get().addDishToMenu(new Dish(dishName,dishCookingTime,dishImagePath,dishType,id));
            resp.sendRedirect(req.getContextPath()+"/dishes_menu_editor");
        }

    }



}
