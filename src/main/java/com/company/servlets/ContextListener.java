package com.company.servlets;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.Restaurant;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;
import com.company.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
/**
 * ContextListener put user dao to servlet context.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    /**
     * Fake database connector.
     */
    private Restaurant restaurant;
    private AtomicReference<UserDAO> dao;
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;
   private ArrayList<Order> ordersBank;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
//c3p0=
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver"             );
            cpds.setJdbcUrl    ("jdbc:postgresql://localhost:5432/Restaurant");
            cpds.setUser       ("postgres"   );
            cpds.setPassword   ("1");

            Properties properties = new Properties();
            properties.setProperty ("user"             , "postgres"   );
            properties.setProperty ("password"         , "1");
            properties.setProperty ("useUnicode"       , "true"      );
            properties.setProperty ("characterEncoding", "UTF8"      );
            cpds.setProperties(properties);

            // set options
            cpds.setMaxStatements             (180);
            cpds.setMaxStatementsPerConnection(180);
            cpds.setMinPoolSize               ( 50);
            cpds.setAcquireIncrement          ( 10);
            cpds.setMaxPoolSize               ( 60);
            cpds.setMaxIdleTime               ( 30);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("cpds", cpds);
//=c3p0
        restaurant=new Restaurant();
        queueOrders=new LinkedBlockingQueue<>();
        servletContext.setAttribute("queueOrders", queueOrders);
        ordersBank=new ArrayList<>();
        servletContext.setAttribute("ordersBank",ordersBank);
        servletContext.setAttribute("dao", dao);
        menu=new AtomicReference<>(new Menu());
        for (DishType e:DishType.values()){
            menu.get().getDishTypeList().add(e.toString());
        }
        menu.get().addDishToMenu(new Dish("Пюре с котлетой",1,"src/main/java/com/company/dao/pictures/olive.jpg", DishType.HOT,1));
        menu.get().addDishToMenu(new Dish("Сельд под шубой",30,"com/company/dao/pictures/olive.jpg", DishType.SALAD,2));
        menu.get().addDishToMenu(new Dish("Цезарь",15,"com/company/dao/pictures/olive.jpg", DishType.SALAD,3));
        menu.get().addDishToMenu(new Dish("Оливье",43,"https://potokmedia.ru/wp-content/uploads/2020/12/word-image-9.jpeg", DishType.SALAD,4));
        for(Dish e:menu.get().getDishesList()){
            e.setPrice((int)(Math.random()*500));
            e.setActive(true);
        }
        servletContext.setAttribute("menu", menu);
        dao=restaurant.getDao(cpds);
        restaurant.setMenu(this.menu);
        restaurant.setQueueOrders(this.queueOrders);
        restaurant.setOrdersBank(this.ordersBank);
        servletContext.setAttribute("restaurant", restaurant);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                //LOG.log(Level.INFO, String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                //LOG.log(Level.SEVERE, String.format("Error deregistering driver %s", driver), e);
            }
        }
        restaurant=null;
        //dao = null;
    }
}