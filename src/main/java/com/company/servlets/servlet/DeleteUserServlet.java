package com.company.servlets.servlet;

import com.company.dao.UserDAO;
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        List<User> list=dao.get().getStore();
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
