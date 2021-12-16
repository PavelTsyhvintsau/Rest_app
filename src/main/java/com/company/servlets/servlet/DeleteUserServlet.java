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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        int id=Integer.parseInt(req.getParameter("id"));
        String deleteTableSQL = "DELETE FROM allusers WHERE id = "+id;
        Statement statement = null;
        Connection connection=null;
        try {
            connection=restaurant.getConnection();
            statement=connection.createStatement();
            statement.execute(deleteTableSQL);;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


        resp.sendRedirect(req.getContextPath()+"/updateUsers" );
    }
}
