package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDishToOrderTableServlet extends HttpServlet {
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
        Order order=(Order) req.getSession().getAttribute("order");
        final String id = req.getParameter("id");
        final Dish dish= restaurant.getDishFromDB(Integer.valueOf(id));
        final int pieces = Integer.parseInt(req.getParameter("pieces"));
        order.putDish(dish,pieces);
        resp.sendRedirect(req.getContextPath()+"/table_menu");

    }
}
