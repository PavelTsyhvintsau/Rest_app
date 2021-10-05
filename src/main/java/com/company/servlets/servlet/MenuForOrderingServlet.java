package com.company.servlets.servlet;

import com.company.dao.Menu;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class MenuForOrderingServlet extends HttpServlet {
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;
    private ArrayList<Dish> menuHot;
    private ArrayList<Dish> menuSalat;
    private ArrayList<Dish> menuSoup;
    private ArrayList<Dish> menuGarnish;
    private ArrayList<Dish> menuHotDrink;
    private ArrayList<Dish> menuCouldDrink;


    @Override
    public void init() throws ServletException {

        final Object menu = getServletContext().getAttribute("menu");
        if (menu == null ) {

            throw new IllegalStateException("You're menu does not initialize! )))))");
        } else {
            this.menu = (AtomicReference<Menu>) getServletContext().getAttribute("menu");
        }
        final Object queueOrders = getServletContext().getAttribute("queueOrders");
        if (queueOrders == null ) {

            throw new IllegalStateException("You're queueOrders does not initialize! )))))");
        } else {
            this.queueOrders = (LinkedBlockingQueue<Order>) getServletContext().getAttribute("queueOrders");
        }
        menuHot= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.HOT);
        menuSalat= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.SALAD);
        menuSoup= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.SOUP);
        menuGarnish= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.GARNISH);
        menuHotDrink= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.HOT_DRINK);
        menuCouldDrink= (ArrayList<Dish>) this.menu.get().getMenuByType(DishType.COULD_DRINK);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("menu", menu.get());
        req.setAttribute("queueOrders", queueOrders);
        req.setAttribute("menuHot", menuHot);
        req.setAttribute("menuSalat", menuSalat);
        req.setAttribute("menuSoup", menuSoup);
        req.setAttribute("menuGarnish", menuGarnish);
        req.setAttribute("menuHotDrink", menuHotDrink);
        req.setAttribute("menuCouldDrink", menuCouldDrink);




        req.getRequestDispatcher("/WEB-INF/view/menu_for_ordering.jsp").forward(req, resp);

    }

}
