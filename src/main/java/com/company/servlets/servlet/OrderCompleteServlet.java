package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Cook;
import com.company.model.kitchen.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCompleteServlet extends HttpServlet {
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
    protected void doPost ( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Cook cook=((User)req.getSession().getAttribute("user")).getCook();
        Order order=cook.getCurrentOrder();
        cook.setCurrentOrder(null);
        for(Order e:restaurant.getOrdersBank()){
            if (e.getId()==order.getId()){
                e.setOrderstatus(Order.Orderstatus.ISREADY);
                e.setOrderEndCookingTime(System.currentTimeMillis());
            }
        }

        req.setAttribute("cook",cook);
        resp.sendRedirect(req.getContextPath()+"/cooking_page" );
    }
}