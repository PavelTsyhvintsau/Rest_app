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
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;
   private ArrayList<Order> ordersBank;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
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
        restaurant.setDao();
        restaurant.setMenu(this.menu);
        restaurant.setQueueOrders(this.queueOrders);
        restaurant.setOrdersBank(this.ordersBank);
        servletContext.setAttribute("restaurant", restaurant);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //dao = null;
    }
}