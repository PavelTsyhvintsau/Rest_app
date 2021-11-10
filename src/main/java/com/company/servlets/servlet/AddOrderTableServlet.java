package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class AddOrderTableServlet extends HttpServlet {
    private AtomicInteger idOrder;
    private Restaurant restaurant;
    @Override
    public void init() throws ServletException {
        final Object restaurant = getServletContext().getAttribute("restaurant");
        if (restaurant == null) {
            throw new IllegalStateException("You're restaurant does not initialize! )))))");
        } else {
            this.restaurant = (Restaurant) getServletContext().getAttribute("restaurant");
        }
        idOrder = new AtomicInteger(100000);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order=(Order)request.getSession().getAttribute("order");
        if(order.isEmpty()){
            response.sendRedirect(request.getContextPath()+"/table_menu");
        }else {
            try {
                final int id = this.idOrder.getAndIncrement();
                order.setId(id);
                order.setTableNumber(Integer.parseInt(request.getParameter("tableNumber")));
                restaurant.getQueueOrders().put(order);
                restaurant.getOrdersBank().add(order);
                order.setOrderstatus(Order.Orderstatus.INQUEUE);
                order.setOrderCreateTime(System.currentTimeMillis());
                Order orderNew=new Order((User)request.getSession().getAttribute("user"));
                orderNew.setTableNumber(Integer.parseInt(request.getParameter("tableNumber")));
                request.getSession().removeAttribute("order");
                request.getSession().setAttribute("order",orderNew);
            } catch (InterruptedException e) {
                System.out.println("Can't put order to queue");
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath()+"/table_menu");
        }
    }
}
