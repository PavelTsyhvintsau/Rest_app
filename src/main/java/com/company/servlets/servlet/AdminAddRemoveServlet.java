package com.company.servlets.servlet;

import com.company.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AdminAddRemoveServlet extends HttpServlet {
    private AtomicReference<UserDAO> dao;
    @Override
    public void init() throws ServletException {
        final Object dao = getServletContext().getAttribute("dao");
        if (dao == null ) {

            throw new IllegalStateException("You're repo does not initialize! )))))");
        } else {

            this.dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dao", dao.get());

        req.getRequestDispatcher("/WEB-INF/view/admin_add_remove_users.jsp").forward(req, resp);



    }
}
