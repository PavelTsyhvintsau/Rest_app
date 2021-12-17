package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final int id = Integer.parseInt(req.getParameter("id"));
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String role=req.getParameter("role");
        String isActive=req.getParameter("active");
        restaurant.updateUser(login,password,role,isActive,id);
        resp.sendRedirect(req.getContextPath() + "/updateUsers");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");
        final User user =restaurant.getDao().get().getById(Integer.parseInt(id));
        req.setAttribute("user", user);
            req.setAttribute("dao", restaurant.getDao().get());
            req.getRequestDispatcher("/WEB-INF/view/update_user.jsp").forward(req, resp);
        }
}
