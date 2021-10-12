package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AddOrderToQueueServlet extends HttpServlet {

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

        idOrder = new AtomicInteger(1);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order=(Order)request.getSession().getAttribute("order");
        if(order.isEmpty()){
            response.sendRedirect(request.getContextPath()+"/menu_for_ordering");
        }else {
            try {
                restaurant.getQueueOrders().put(order);
                order.setOrderCreateTime(System.currentTimeMillis());
                System.out.println("заказ помещен в очередь");
                Order orderNew=new Order((User)request.getSession().getAttribute("user"));
                final int id = this.idOrder.getAndIncrement();
                order.setTableNumber(Integer.parseInt(request.getParameter("tableNumber")));
                orderNew.setId(id);

                request.getSession().removeAttribute("order");
                request.getSession().setAttribute("order",orderNew);
            } catch (InterruptedException e) {
                System.out.println("Can't put order to queue");
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath()+"/menu_for_ordering");
        }
    }


}
