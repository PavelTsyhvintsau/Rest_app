package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrderToQueueServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order=(Order)request.getSession().getAttribute("order");
        order.setTableNumber(Integer.parseInt(request.getParameter("tableNumber")));
        if(order.isEmpty()){
            response.sendRedirect(request.getContextPath()+"/menu_for_ordering");
        }else {
            restaurant.putOrderToDdAndINQUEUE(order);
            request.getSession().removeAttribute("order");
            request.getSession().setAttribute("order",new Order((User)request.getSession().getAttribute("user")));
            response.sendRedirect(request.getContextPath()+"/menu_for_ordering");
        }
    }
}
