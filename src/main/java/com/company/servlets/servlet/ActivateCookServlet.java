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


public class ActivateCookServlet extends HttpServlet {
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
        String id=req.getParameter("id");
        int orderID=restaurant.getOrderFromQueueDd(Integer.parseInt(id));
        User user=(User)req.getSession().getAttribute("user");
        user.setCurrentOrder(orderID);
        req.getSession().setAttribute("user",user);
        if(user.getCurrentOrder()!=-1){
            req.setAttribute("curOrder", restaurant.getOrderFromDdBiID(user.getCurrentOrder()));
        }
        resp.sendRedirect(req.getContextPath()+"/cooking_page" );
    }
}



