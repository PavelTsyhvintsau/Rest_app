package com.company.servlets.servlet;


import com.company.dao.UserDAO;
import com.company.model.User;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateUserServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String role=req.getParameter("role");

        final User user = dao.get().getById(Integer.valueOf(id));
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(User.ROLE.valueOf(role));

        resp.sendRedirect(req.getContextPath() + "/updateUsers");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String id = req.getParameter("id");
        Map<Integer, User> users= new HashMap<>();
        for (User element:dao.get().getStore()){
            users.put(element.getId(),element);
        }

        if (Utils.idUserIsInvalid(id, users)) {
            resp.sendRedirect(req.getContextPath() + "/updateUsers");
            return;
        }

        final User user =users.get(Integer.parseInt(id));
        req.setAttribute("user", user);
        req.setAttribute("dao", dao.get());
        req.getRequestDispatcher("/WEB-INF/view/update_user.jsp")
                .forward(req, resp);
    }
}
