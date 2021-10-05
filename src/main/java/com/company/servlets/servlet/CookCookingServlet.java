package com.company.servlets.servlet;

import com.company.model.User;
import com.company.model.kitchen.Cook;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class CookCookingServlet extends HttpServlet {
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("queueOrders", queueOrders);
        Cook cook=((User)req.getSession().getAttribute("user")).getCook();
        cook.setQueue(queueOrders);
        Thread cooking=new Thread (cook);
        req.setAttribute("cook",cook);
        cooking.start();

        req.getRequestDispatcher("/WEB-INF/view/cook_page_cooking.jsp").forward(req, resp);
    }
}
