package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
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
        int idOrder=Integer.parseInt(req.getParameter("orderid"));
        User user=(User)req.getSession().getAttribute("user");
        int idUser=user.getId();
        restaurant.setOrderStatusReady(Order.Orderstatus.ISREADY,idOrder,idUser);
        user.setCurrentOrder(-1);
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath()+"/cooking_page" );
    }
}