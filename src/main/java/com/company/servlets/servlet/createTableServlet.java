package com.company.servlets.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class createTableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InitialContext initContext= null;
        try {
            initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/dbconnect");
            Connection conn = ds.getConnection();
            System.out.println("создано соединение с базой");
            request.getRequestDispatcher("/WEB-INF/view/tableInBase.jsp").forward(request, response);

        } catch (NamingException | SQLException e) {
            System.out.println("не создал соединение с базой");
            e.printStackTrace();
        }


    }
}
