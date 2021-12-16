package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.util.Utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;


public class AddUserServlet extends HttpServlet {
    private AtomicInteger id;
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
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("-----начинаю добавку юзера");
        if (Utils.requestIsValid(req)) {
            final String login = req.getParameter("login");
            final String password = req.getParameter("password");
            final String role= req.getParameter("role");
            String insertTableSQL = "INSERT INTO allusers"
                    + "( name, password, role, is_active) " + "VALUES"
                    + "('"+login+"','"+password+"','"+role+"','true')";
            Statement statement = null;
            Connection connection=null;
            try {
                connection=restaurant.getConnection();
                statement=connection.createStatement();
                System.out.println("-----начинаю добавку юзера/есть коннект    строка="+insertTableSQL);
                statement.executeUpdate(insertTableSQL);
                System.out.println("-----начинаю добавку юзера/есть вставка");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("-----екзепшн добавки юзера");
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
        }

        resp.sendRedirect(req.getContextPath()+"/updateUsers");
    }
}
