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
import java.util.ArrayList;

public class WaiterOrderToBankServlet extends HttpServlet {
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
        int id=Integer.parseInt(req.getParameter("ID"));
        ArrayList<Order>ordersComplete=restaurant.getOrdersBank();
        for (int i=0;i<ordersComplete.size();i++){
            if(ordersComplete.get(i).getId()==id){
                ordersComplete.get(i).setOrderstatus(Order.Orderstatus.ISCLOSE);
                ordersComplete.get(i).setOrderToClientTime(System.currentTimeMillis());
            }
        }
        resp.sendRedirect(req.getContextPath()+"/waiter_ordering" );
    }
}
