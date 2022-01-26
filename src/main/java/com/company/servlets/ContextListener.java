package com.company.servlets;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private Menu menu;

   private ArrayList<Order> ordersBank;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        restaurant=new Restaurant();

        restaurant.setMenu();
        servletContext.setAttribute("menu", menu);
        restaurant.setDao();
        servletContext.setAttribute("dao", dao);
        servletContext.setAttribute("restaurant", restaurant);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //dao = null;
    }
}