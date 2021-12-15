package com.company.model;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Restaurant {
    private AtomicReference<UserDAO> dao;
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;
    private ArrayList<Order> ordersBank;

    public void setDao() {
        AtomicReference<UserDAO> dao = new AtomicReference<>(new UserDAO());
        for (User.ROLE e: User.ROLE.values()){
            dao.get().getRoleList().add(e.toString());
        }
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        try {
            initContext = new InitialContext();
            System.out.println("создан initContext");
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
            System.out.println("создано DataSource");
            connection = ds.getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            String selectTableSQL = "SELECT name, role, password, id FROM ALLUSERS";
            System.out.println("строка");
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("вычитан сет");
            while (rs.next()) {
                String nameuser=rs.getString("name");
                String role=rs.getString("role");
                int id=rs.getInt("id");
                String password=rs.getString("password");
                User.ROLE userRole=User.ROLE.valueOf(role);
                dao.get().add(new User(id, nameuser,password,userRole));
            }
            connection.close();
        }catch (NamingException | SQLException e){
            System.out.println("экзепшн вычитивания юзеров из БД");
        }
        this.dao = dao;
    }
    public AtomicReference<UserDAO> getDao() {
        setDao();
        return dao;
    }
    public AtomicReference<Menu> getMenu() {
        return menu;
    }
    public void setMenu(AtomicReference<Menu> menu) {
        this.menu = menu;
    }
    public LinkedBlockingQueue<Order> getQueueOrders() {
        return queueOrders;
    }
    public void setQueueOrders(LinkedBlockingQueue<Order> queueOrders) {
        this.queueOrders = queueOrders;
    }
    public ArrayList<Order> getOrdersBank() {
        return ordersBank;
    }
    public void setOrdersBank(ArrayList<Order> ordersBank) {
        this.ordersBank = ordersBank;
    }
}
