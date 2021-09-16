package com.company.servlets;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;
import com.company.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dao = new AtomicReference<>(new UserDAO());
        for (User.ROLE e: User.ROLE.values()){
            dao.get().getRoleList().add(e.toString());
        }

        dao.get().add(new User(1, "Pavel", "1", User.ROLE.ADMIN));
        dao.get().add(new User(2, "Egor", "1", User.ROLE.COOK));

        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        servletContext.setAttribute("dao", dao);

        menu=new AtomicReference<>(new Menu());
        for (DishType e:DishType.values()){
            menu.get().getDishTypeList().add(e.toString());
        }


        menu.get().addDishToMenu(new Dish("Пюре с котлетой",300,"src/main/java/com/company/dao/pictures/olive.jpg", DishType.HOT,1));
        menu.get().addDishToMenu(new Dish("Оливье",250,"com/company/dao/pictures/olive.jpg", DishType.SALAT,2));
        menu.get().addDishToMenu(new Dish("Оливье",250,"com/company/dao/pictures/olive.jpg", DishType.SALAT,3));
        menu.get().addDishToMenu(new Dish("Оливье",250,"https://potokmedia.ru/wp-content/uploads/2020/12/word-image-9.jpeg", DishType.SALAT,4));
        servletContext.setAttribute("menu", menu);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //dao = null;
    }
}