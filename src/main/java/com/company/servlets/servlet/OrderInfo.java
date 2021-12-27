package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.kitchen.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderInfo extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr=req.getParameter("id");
        int id=Integer.valueOf(idStr);
        Order order=null;
        for(Order e:restaurant.getOrdersListFromDd()){
            if(e.getId()==id){
                order=e;
            }
        }
        req.setAttribute("order", order);
        req.getRequestDispatcher("/WEB-INF/view/order_info_page.jsp").forward(req, resp);
    }
}
