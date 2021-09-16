package com.company.servlets.servlet;



import com.company.dao.UserDAO;
import com.company.model.User;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AddUserServlet extends HttpServlet {



    private AtomicInteger id;

    private AtomicReference<UserDAO> dao;
    @Override
    public void init() throws ServletException {
        final Object dao = getServletContext().getAttribute("dao");
        if (dao == null ) {

            throw new IllegalStateException("You're repo does not initialize! )))))");
        } else {

            this.dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
        }
        id = new AtomicInteger(3);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        if (Utils.requestIsValid(req)) {

            final String login = req.getParameter("login");
            final String password = req.getParameter("password");
            final User.ROLE role= User.ROLE.valueOf(req.getParameter("role"));

            final User user = new User();
            final int id = this.id.getAndIncrement();
            user.setId(id);
            user.setPassword(password);
            user.setLogin(login);
            user.setRole(role);

            dao.get().add(user);
        }

        resp.sendRedirect(req.getContextPath()+"/updateUsers");
    }
}
