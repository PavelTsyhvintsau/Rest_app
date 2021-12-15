package com.company.servlets.servlet;

import com.company.dao.UserDAO;
import com.company.model.Restaurant;
import com.company.model.User;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteUserServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        List<User> list=restaurant.getDao().get().getStore();
        if (Utils.idIsNumber(req)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == Integer.parseInt(req.getParameter("id"))) {
                    list.remove(i);
                }
            }

        }

        resp.sendRedirect(req.getContextPath()+"/updateUsers" );
    }
}
