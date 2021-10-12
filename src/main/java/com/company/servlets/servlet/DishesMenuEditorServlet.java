package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.Restaurant;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DishesMenuEditorServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("menu", restaurant.getMenu().get());

        req.getRequestDispatcher("/WEB-INF/view/dishes_menu_editor.jsp").forward(req, resp);

    }
}


