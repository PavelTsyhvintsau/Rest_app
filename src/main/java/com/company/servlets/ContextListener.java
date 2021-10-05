package com.company.servlets;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;
import com.company.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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
    private AtomicReference<UserDAO> dao;
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        queueOrders=new LinkedBlockingQueue<>();
        servletContext.setAttribute("queueOrders", queueOrders);

        servletContext.setAttribute("dao", dao);
        dao = new AtomicReference<>(new UserDAO());
        for (User.ROLE e: User.ROLE.values()){
            dao.get().getRoleList().add(e.toString());
        }

        dao.get().add(new User(1, "Admin", "1", User.ROLE.ADMIN));
        dao.get().add(new User(2, "Cook", "1", User.ROLE.COOK));
        dao.get().add(new User(2, "Waiter", "1", User.ROLE.WAITER));


        servletContext.setAttribute("dao", dao);

        menu=new AtomicReference<>(new Menu());
        for (DishType e:DishType.values()){
            menu.get().getDishTypeList().add(e.toString());
        }


        menu.get().addDishToMenu(new Dish("Пюре с котлетой",300,"src/main/java/com/company/dao/pictures/olive.jpg", DishType.HOT,1));
        menu.get().addDishToMenu(new Dish("Сельд под шубой",250,"com/company/dao/pictures/olive.jpg", DishType.SALAD,2));
        menu.get().addDishToMenu(new Dish("Цезарь",250,"com/company/dao/pictures/olive.jpg", DishType.SALAD,3));
        menu.get().addDishToMenu(new Dish("Оливье",250,"https://potokmedia.ru/wp-content/uploads/2020/12/word-image-9.jpeg", DishType.SALAD,4));
        servletContext.setAttribute("menu", menu);
        for(Dish e:menu.get().getDishesList()){
            e.setActive(true);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //dao = null;
    }
}