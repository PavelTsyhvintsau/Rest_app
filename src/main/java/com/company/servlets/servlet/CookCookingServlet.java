package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Cook;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CookCookingServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("queueOrders", restaurant.getQueueOrders());
        Cook cook=((User)req.getSession().getAttribute("user")).getCook();
        cook.setQueue(restaurant.getQueueOrders());
        req.setAttribute("cook",cook);


        req.getRequestDispatcher("/WEB-INF/view/cook_page_cooking.jsp").forward(req, resp);
    }


}
