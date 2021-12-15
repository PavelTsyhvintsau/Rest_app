package com.company.model;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;
import com.mchange.v2.c3p0.ComboPooledDataSource;

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

   /* public Connection doConnection (){
        InitialContext initContext= null;
        Connection connection=null;
        try {
            initContext = new InitialContext();
            //System.out.println("создан initContext");
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
            //System.out.println("создано DataSource");
            connection = ds.getConnection();
        }catch (NamingException | SQLException e){
            System.out.println("экзепшн соединения с БД");
        }
        return connection;
    }*/
   public Connection doConnection (ComboPooledDataSource cpds){
       Connection connection=null;
       try {
           connection=cpds.getConnection();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }return connection;
   }
    public void setDao(ComboPooledDataSource cpds) {
        AtomicReference<UserDAO> dao = new AtomicReference<>(new UserDAO());
        for (User.ROLE e: User.ROLE.values()){
            dao.get().getRoleList().add(e.toString());
        }
        Statement statement = null;

        try {
            Connection con=doConnection(cpds);
            statement = con.createStatement();
            //System.out.println("создано statement");
            String selectTableSQL = "SELECT name, role, password, id, is_active FROM ALLUSERS";
            ResultSet rs=statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String nameuser=rs.getString("name");
                String role=rs.getString("role");
                int id=rs.getInt("id");
                String password=rs.getString("password");
                User.ROLE userRole=User.ROLE.valueOf(role);
                Boolean isActive=false;
                if(rs.getString("is_active").equals("t")){
                    isActive=true;
                }
                User user=new User(id, nameuser,password,userRole);
                user.setActive(isActive);
                dao.get().add(user);
            }
            con.close();
        }catch (SQLException e){
            System.out.println("экзепшн вычитивания юзеров из БД");
        }
        this.dao = dao;
    }
    public void addUser(ComboPooledDataSource cpds, String login, String password, String role){
        Statement statement = null;
        Connection con=doConnection(cpds);
        String insertUser = "INSERT INTO allusers" + "( name, password, role, is_active) " + "VALUES" + "('"+login+"', '"+password+"', '"+role+"', 'true')";
        try {
            statement = con.createStatement();
            statement.executeUpdate(insertUser);
            System.out.println("----------юзер вставлен");
            con.close();
        }catch (SQLException e){
            System.out.println("экзепшн добавления юзера в БД");
        }
    }
    public AtomicReference<UserDAO> getDao(ComboPooledDataSource cpds) {
        setDao(cpds);
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
