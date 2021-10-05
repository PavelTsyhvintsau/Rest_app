package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AddDishToOrderServlet extends HttpServlet {

    private AtomicReference<Menu> menu;

    @Override
    public void init() throws ServletException {

        final Object menu = getServletContext().getAttribute("menu");
        if (menu == null) {
            throw new IllegalStateException("You're menu repo does not initialize! )))))");
        } else {
            this.menu = (AtomicReference<Menu>) getServletContext().getAttribute("menu");
        }


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("doPost AddDishToOrderServlet in work");
        Order order=(Order) req.getSession().getAttribute("order");

        final String id = req.getParameter("id");
        final Dish dish= menu.get().getDishById(Integer.valueOf(id));
        final int pieces = Integer.parseInt(req.getParameter("pieces"));
        order.putDish(dish,pieces);

        resp.sendRedirect(req.getContextPath()+"/menu_for_ordering");

    }


}
