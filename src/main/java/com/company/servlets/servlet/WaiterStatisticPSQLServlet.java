package com.company.servlets.servlet;

import com.company.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WaiterStatisticPSQLServlet extends HttpServlet {
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


    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        final String sortBy = req.getParameter("sortBy");
        final String start = req.getParameter("trip-start");
        final String end = req.getParameter("trip-end");
        final String[] users=req.getParameterValues("usersID");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
