package com.company.model;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

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
    private Menu menu;
    private LinkedBlockingQueue<Order> queueOrders;
    private ArrayList<Order> ordersBank;
    public Connection getConnection(){
        InitialContext initContext= null;
        Connection connection=null;
        try {
            initContext = new InitialContext();
            System.out.println("создан initContext");
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
            System.out.println("создано DataSource");
            connection = ds.getConnection();
        }catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public void setDao(){
        AtomicReference<UserDAO> dao = new AtomicReference<>(new UserDAO());
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            String selectTableSQL = "SELECT name, role, password, id, is_active FROM ALLUSERS";
            System.out.println("строка");
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("вычитан сет");
            while (rs.next()) {
                String nameuser=rs.getString("name");
                String role=rs.getString("role");
                int id=rs.getInt("id");
                String password=rs.getString("password");
                String active=rs.getString("is_active");
                User.ROLE userRole=User.ROLE.valueOf(role);
                User user=new User(id, nameuser,password,userRole);
                if ("t".equals(active)){
                    user.setActive(true);
                }else {
                    user.setActive(false);
                }
                dao.get().add(user);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println("экзепшн вычитивания юзеров из БД");
        } finally {
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
        for (User.ROLE e: User.ROLE.values()){
            dao.get().getRoleList().add(e.toString());
        }
        this.dao = dao;
    }
    public AtomicReference<UserDAO> getDao(){
        setDao();
        return dao;
    }
    public void deleteUser (int id){
        String deleteTableSQL = "DELETE FROM allusers WHERE id = "+id;
        Statement statement = null;
        Connection connection=null;
        try {
            connection=getConnection();
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
    }
    public void updateUser(String login,String password, String role, String isActive, int id){
        if(isActive==null)isActive="false";
        String updateTableSQL = "UPDATE allusers SET name = '"+login+"', password = '"+password+"',role = '"+role+"', is_active = '"+isActive+"' WHERE id = "+id;
        Statement statement = null;
        Connection connection=null;
        try {
            connection=getConnection();
            statement=connection.createStatement();
            statement.execute(updateTableSQL);;
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
    }
    public void addUser(String login, String password, String role){
        String insertTableSQL = "INSERT INTO allusers"
                + "( name, password, role, is_active) " + "VALUES"
                + "('"+login+"','"+password+"','"+role+"','true')";
        Statement statement = null;
        Connection connection=null;
        try {
            connection=getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(insertTableSQL);
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
    }
    public void setMenu() {
        menu = new Menu();
        for (DishType e : DishType.values()) {
            menu.getDishTypeList().add(e.toString());
        }
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            String selectTableSQL = "SELECT id, dish_name, dish_type, dish_image_path, price, dish_cooking_time, active FROM alldish";
            System.out.println("строка");
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("вычитан сет");
            while (rs.next()) {
                String dishName=rs.getString("dish_name");
                String type=rs.getString("dish_type");
                String dishImagePath=rs.getString("dish_image_path");
                int id=rs.getInt("id");
                int price=rs.getInt("price");
                int dishCookingTime=rs.getInt("dish_cooking_time");
                String active=rs.getString("active");
                DishType dishType=DishType.valueOf(type);
                Dish dish=new Dish(dishName,dishCookingTime,dishImagePath,dishType,id,price);
                if ("t".equals(active)){
                    dish.setActive(true);
                }else {
                    dish.setActive(false);
                }
                menu.addDishToMenu(dish);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println("экзепшн вычитивания юзеров из БД");
        } finally {
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
    public Menu getMenu() {
        setMenu();
        return menu;
    }
    public void updatePrice(int id, int price) {
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        String updateTableSQL = "UPDATE alldish SET price = '"+price+"' WHERE id = "+id;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            statement.execute(updateTableSQL);;
        }catch (SQLException e){
            System.out.println("экзепшн обновления цены блюда");
        } finally {
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
    public void activateDish(int id, boolean bool) {
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        String updateActive = "UPDATE alldish SET active = '"+bool+"' WHERE id = "+id;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            statement.execute(updateActive);
        }catch (SQLException e){
            System.out.println("экзепшн активации блюда");
        } finally {
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
    public void addDish(String dishName, String dishType, int dishCookingTime, String dishImagePath) {
        String insertTableSQL = "INSERT INTO alldish"
                + "( dish_name, dish_type, dish_cooking_time, dish_image_path, active) " + "VALUES"
                + "('"+dishName+"','"+dishType+"','"+dishCookingTime+"','"+dishImagePath+"','true')";
        Statement statement = null;
        Connection connection=null;
        try {
            connection=getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(insertTableSQL);
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
    }
    public void deleteDish(int id) {
        String deleteTableSQL = "DELETE FROM alldish WHERE id = "+id;
        Statement statement = null;
        Connection connection=null;
        try {
            connection=getConnection();
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
    }
    public void updateDish(String id, String name, String type, int cookingTime, String imagePath) {
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        String updateTableSQL = "UPDATE alldish SET dish_name = '"+name+"', dish_type = '"+type+"', dish_cooking_time = '"+cookingTime+"', dish_image_path = '"+imagePath+"' WHERE id = "+id;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            statement.execute(updateTableSQL);;
        }catch (SQLException e){
            System.out.println("экзепшн обновления блюда");
        } finally {
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
    public Dish getDish(int id) {
        InitialContext initContext= null;
        Connection connection=null;
        Statement statement = null;
        Dish dish=null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            System.out.println("создано statement");
            String selectTableSQL = "SELECT id, dish_name, dish_type, dish_image_path, price, dish_cooking_time, active FROM alldish where id = "+id;
            System.out.println("строка");
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("вычитан сет");
            while (rs.next()&&rs.getInt("id")==id) {
                String dishName=rs.getString("dish_name");
                String type=rs.getString("dish_type");
                String dishImagePath=rs.getString("dish_image_path");
                int price=rs.getInt("price");
                int dishCookingTime=rs.getInt("dish_cooking_time");
                String active=rs.getString("active");
                DishType dishType=DishType.valueOf(type);
                dish=new Dish(dishName,dishCookingTime,dishImagePath,dishType,id,price);
                if ("t".equals(active)){
                    dish.setActive(true);
                }else {
                    dish.setActive(false);
                }
                menu.addDishToMenu(dish);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println("экзепшн вычитивания юзеров из БД");
        } finally {
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
        return dish;
    }
    public void putOrderToDdAndINQUEUE(Order order){
        Statement statement = null;
        Connection connection=null;
        try {
            order.setOrderstatus(Order.Orderstatus.INQUEUE);
            String insertTableSQL = "INSERT INTO orders"
                    + "( user_id, table_number, create_time, status) " + "VALUES"
                    + "('"+order.getUser().getId()+"','"+order.getTableNumber()+"','"+new java.sql.Timestamp(order.getOrderCreateTime())+"','"+order.getOrderstatus().toString()+"')";
            connection=getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(insertTableSQL);
        } catch (SQLException throwables) {
            System.out.println("===ошибка помещения заказа в бд/очередь");
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
