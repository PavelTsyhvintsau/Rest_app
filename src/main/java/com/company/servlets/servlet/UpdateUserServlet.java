package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.util.Utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        final String id = req.getParameter("id");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String role=req.getParameter("role");
        final String isActive=req.getParameter("active");

        final User user = restaurant.getDao((ComboPooledDataSource)req.getAttribute("cpds")).get().getById(Integer.valueOf(id));
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(User.ROLE.valueOf(role));
        if(isActive==null){
            user.setActive(false);
        }else {
            user.setActive(true);
        }

        resp.sendRedirect(req.getContextPath() + "/updateUsers");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String id = req.getParameter("id");
        Map<Integer, User> users= new HashMap<>();
        for (User element:restaurant.getDao((ComboPooledDataSource)req.getAttribute("cpds")).get().getStore()){
            users.put(element.getId(),element);
        }

        if (Utils.idUserIsInvalid(id, users)) {
            resp.sendRedirect(req.getContextPath() + "/updateUsers");
            return;
        }

        final User user =users.get(Integer.parseInt(id));
        req.setAttribute("user", user);
        req.setAttribute("dao", restaurant.getDao((ComboPooledDataSource)req.getAttribute("cpds")).get());
        req.getRequestDispatcher("/WEB-INF/view/update_user.jsp").forward(req, resp);
    }
}
