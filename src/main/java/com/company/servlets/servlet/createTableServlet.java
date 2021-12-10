package com.company.servlets.servlet;

import com.company.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class createTableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO ALLUSERS"
                + "(ID, NAME, PAROLE, ROLE) " + "VALUES"
                + "(3,'mkyong3','1','ADMIN')";
        try {
            initContext = new InitialContext();
            System.out.println("создан initContext");
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
            System.out.println("создано DataSource");
            connection = ds.getConnection();
            System.out.println("создано соединение с базой");
            statement = connection.createStatement();
            System.out.println("создано statement");
            // выполнить вставку в таблицу
            //statement.executeUpdate(insertTableSQL);
            System.out.println("выполнена вставка в таблицу");
            String selectTableSQL = "SELECT name, role, parole, id FROM ALLUSERS where id=2";
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("вычитан сет из базы");
            ArrayList<User> users=new ArrayList<>();

            while (rs.next()) {
                String name=rs.getString("name");
                String role=rs.getString("role");
                int id=rs.getInt("id");
                String parole=rs.getString("parole");

                System.out.println("id : " + id);
                System.out.println("name : " + name);
                System.out.println("role : " + role);
                System.out.println("parole : " + parole);
                User.ROLE userRole=(User.ROLE) User.ROLE.valueOf(role);
                System.out.println(" роль создана");
                User user=new User(id, name,parole,userRole);
                System.out.println("user created/ role" + user.getRole().toString());
            }

            //String name=rs.getString("name");
            //System.out.println("прочитан нэйм");
            //String role=rs.getString("role");
            //System.out.println("прочитан роль");
            //System.out.println(name+"- имя прочитано. роль- "+role);
            request.getRequestDispatcher("/WEB-INF/view/tableInBase.jsp").forward(request, response);

        } catch (NamingException | SQLException e) {
            System.out.println("словил эксепшен");
            e.printStackTrace();
        }

    }
}
