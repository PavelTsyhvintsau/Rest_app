package com.company.servlets.servlet;

import com.company.model.User;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class AddOrderToQueueServlet extends HttpServlet {
    private LinkedBlockingQueue<Order> queueOrders;
    @Override
    public void init() throws ServletException {
        final Object queueOrders = getServletContext().getAttribute("queueOrders");
        if (queueOrders == null) {
            throw new IllegalStateException("You're queueOrder does not initialize! )))))");
        } else {
            this.queueOrders = (LinkedBlockingQueue<Order>) getServletContext().getAttribute("queueOrders");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order=(Order)request.getSession().getAttribute("order");
        if(order.isEmpty()){
            response.sendRedirect(request.getContextPath()+"/menu_for_ordering");
        }else {
            try {
                queueOrders.put(order);
                System.out.println("заказ помещен в очередь");
                Order orderNew=new Order((User)request.getSession().getAttribute("user"));
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
