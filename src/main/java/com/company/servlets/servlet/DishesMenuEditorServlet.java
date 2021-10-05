package com.company.servlets.servlet;

import com.company.dao.Menu;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DishesMenuEditorServlet extends HttpServlet {
    private AtomicReference<Menu> menu;


    @Override
    public void init() throws ServletException {

        final Object menu = getServletContext().getAttribute("menu");
        if (menu == null ) {

            throw new IllegalStateException("You're menu does not initialize! )))))");
        } else {

            this.menu = (AtomicReference<Menu>) getServletContext().getAttribute("menu");
        }


    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("menu", menu.get());

        req.getRequestDispatcher("/WEB-INF/view/dishes_menu_editor.jsp").forward(req, resp);

    }
}


