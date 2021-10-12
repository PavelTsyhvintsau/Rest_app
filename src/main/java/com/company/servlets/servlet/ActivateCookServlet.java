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
        Cook cook=((User)req.getSession().getAttribute("user")).getCook();
        if (!restaurant.getQueueOrders().isEmpty()) {
            try {
                cook.setCurrentOrder(restaurant.getQueueOrders().take());
                restaurant.getOrdersInWork().add(cook.getCurrentOrder());
                Order order=cook.getCurrentOrder();
                order.setOrderStartCookingTime(System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println("ошибка взятия заказа из очереди!");
                e.printStackTrace();
            }
        }

        req.setAttribute("cook",cook);
        resp.sendRedirect(req.getContextPath()+"/cooking_page" );
    }

}



