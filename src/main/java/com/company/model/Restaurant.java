package com.company.model;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Restaurant {
    private AtomicReference<UserDAO> dao;
    private Menu menu;
    //private LinkedBlockingQueue<Order> queueOrders;
    //private ArrayList<Order> ordersBank;
    public Connection getConnection(){
        InitialContext initContext= null;
        Connection connection=null;
        try {
            initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
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
            String selectTableSQL = "SELECT name, role, password, id, is_active FROM ALLUSERS";
            ResultSet rs=statement.executeQuery(selectTableSQL);
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
        }    }
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
            String selectTableSQL = "SELECT id, dish_name, dish_type, dish_image_path, price, dish_cooking_time, active FROM alldish";
            ResultSet rs=statement.executeQuery(selectTableSQL);
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
        }    }
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
    public Dish getDishFromDB(int id) {
        Connection connection=null;
        Statement statement = null;
        Dish dish=null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
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
        Connection connection=null;
        try {
            order.setOrderstatus(Order.Orderstatus.INQUEUE);
            order.setOrderCreateTime(System.currentTimeMillis());
            String insTableSQL ="INSERT INTO orders (user_id ,table_number,create_time,status,dishes,start_cooking_time, end_cooking_time,to_client_time ) VALUES (?,?,?,?,?,?,?,?)";

            connection=getConnection();
            Array masSQL=connection.createArrayOf("integer",order.getDishesArray());
            PreparedStatement preparedStatement =connection.prepareStatement(insTableSQL);
            preparedStatement.setInt(1, order.getCreatorID());
            preparedStatement.setInt(2,order.getTableNumber());
            preparedStatement.setTimestamp(3,new java.sql.Timestamp(order.getOrderCreateTime()));
            preparedStatement.setString(4,order.getOrderstatus().toString());
            preparedStatement.setArray(5,masSQL);
            preparedStatement.setTimestamp(6,new java.sql.Timestamp(order.getOrderStartCookingTime()));
            preparedStatement.setTimestamp(7,new java.sql.Timestamp(order.getOrderEndCookingTimeLong()));
            preparedStatement.setTimestamp(8,new java.sql.Timestamp(order.getOrderToClientTime()));

            preparedStatement.executeUpdate();
            System.out.println("========= заказ в очереди готово");

        } catch (SQLException throwables) {
            System.out.println("===ошибка помещения заказа в бд/очередь");
            throwables.printStackTrace();
        }finally {
                try {
                    if (connection != null)connection.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }
    public HashMap<Dish,Integer> getOrderDishes(Integer[][] data){
        HashMap<Dish,Integer> result=new HashMap<>();
        for(int i=0; i<data[0].length;i++){
            result.put(getDishFromDB(data[0][i]),data[1][i]);
        }return result;
    }
    public ArrayList<Order> getOrdersListFromDd(){
        Connection connection=null;
        Statement statement = null;
        ArrayList<Order> result=new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String selectTableSQL = "SELECT id, " +
                    "user_id, " +
                    "table_number," +
                    " create_time, " +
                    "start_cooking_time, " +
                    "end_cooking_time, " +
                    "to_client_time, " +
                    "status," +
                    "done," +
                    "cook_id ," +
                    "dishes FROM orders ";
            ResultSet rs=statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                int id=rs.getInt("id");
                int userID=rs.getInt("user_id");
                int cookID=rs.getInt("cook_id");
                int tableNum=rs.getInt("table_number");
                Timestamp createTimeStamp=rs.getTimestamp("create_time");
                long createTime=createTimeStamp.getTime();
                Timestamp startCookingTimeStamp=rs.getTimestamp("start_cooking_time");
                long startCookingTime=startCookingTimeStamp.getTime();
                Timestamp endCookingTimeStamp=rs.getTimestamp("end_cooking_time");
                long endCookingTime=endCookingTimeStamp.getTime();
                Timestamp toClientTimeStamp=rs.getTimestamp("to_client_time");
                Long toClientTime=toClientTimeStamp.getTime();
                Order.Orderstatus orderstatus=Order.Orderstatus.valueOf(rs.getString("status"));
                Boolean done=rs.getBoolean("done");
                Array array=rs.getArray("dishes");
                Integer[][] data=(Integer[][]) array.getArray();
                HashMap<Dish,Integer>dishList=getOrderDishes(data);
                Order order=new Order(userID,dishList,id,tableNum,createTime,startCookingTime,endCookingTime,toClientTime,done,orderstatus,cookID );
                result.add(order);
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
        return result;
    }
    public Order getOrderFromDdBiID(int ID){
        Connection connection=null;
        Statement statement = null;
        Order result=null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String selectTableSQL = "SELECT id, " +
                    "user_id, " +
                    "table_number," +
                    " create_time, " +
                    "start_cooking_time, " +
                    "end_cooking_time, " +
                    "to_client_time, " +
                    "status," +
                    "done," +
                    "cook_id ," +
                    "dishes FROM orders order by create_time where id= "+ID;
            ResultSet rs=statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                int id=rs.getInt("id");
                int userID=rs.getInt("user_id");
                int cookID=rs.getInt("cook_id");
                int tableNum=rs.getInt("table_number");
                Timestamp createTimeStamp=rs.getTimestamp("create_time");
                long createTime=createTimeStamp.getTime();
                Timestamp startCookingTimeStamp=rs.getTimestamp("start_cooking_time");
                long startCookingTime=startCookingTimeStamp.getTime();
                Timestamp endCookingTimeStamp=rs.getTimestamp("end_cooking_time");
                long endCookingTime=endCookingTimeStamp.getTime();
                Timestamp toClientTimeStamp=rs.getTimestamp("to_client_time");
                Long toClientTime=toClientTimeStamp.getTime();
                Order.Orderstatus orderstatus=Order.Orderstatus.valueOf(rs.getString("status"));
                Boolean done=rs.getBoolean("done");
                Array array=rs.getArray("dishes");
                Integer[][] data=(Integer[][]) array.getArray();
                HashMap<Dish,Integer>dishList=getOrderDishes(data);
                Order order=new Order(userID,dishList,id,tableNum,createTime,startCookingTime,endCookingTime,toClientTime,done,orderstatus,cookID );
                result=order;

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
        return result;
    }
    public int getOrderFromQueueDd(int cookID){
        Connection connection=null;
        Statement statement = null;
        int id=-1;
        try {
            System.out.println("гет ордер бай айди начали"+id);
            connection = getConnection();
            statement = connection.createStatement();
            String selectTableSQL =
                    "SELECT id, " +
                    " create_time " +
                    "FROM orders where status='INQUEUE' order by (create_time)";
            ResultSet rs=statement.executeQuery(selectTableSQL);
            System.out.println("Выполнена блокировка и вычитка строки");
            if (rs.next()) {
                id=rs.getInt("id");
                System.out.println(" id ордера="+id);
            }
            long date=System.currentTimeMillis();
            java.sql.Timestamp timestamp=new java.sql.Timestamp(date);
            String udateTableSQL = "UPDATE orders SET cook_id= "+cookID+", status = 'INWORK',start_cooking_time="+timestamp+" WHERE USER_ID ="+id;
            statement.execute(udateTableSQL);
            /*String endLockTableSQL = "END;";
            statement.execute(endLockTableSQL);*/

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
        return id;
    }



    //public LinkedBlockingQueue<Order> getQueueOrders() {
    //    return queueOrders;
    //}
    //public void setQueueOrders(LinkedBlockingQueue<Order> queueOrders) {
    //    this.queueOrders = queueOrders;
    //}
    //public ArrayList<Order> getOrdersBank() {
    //    return ordersBank;
    //}
    //public void setOrdersBank(ArrayList<Order> ordersBank) {
     //   this.ordersBank = ordersBank;
    //}
}
