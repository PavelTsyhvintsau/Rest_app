package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TableMenuServlet extends HttpServlet {
    private ArrayList<Dish> menuHot;
    private ArrayList<Dish> menuSalat;
    private ArrayList<Dish> menuSoup;
    private ArrayList<Dish> menuGarnish;
    private ArrayList<Dish> menuHotDrink;
    private ArrayList<Dish> menuCouldDrink;
    private Restaurant restaurant;
    @Override
    public void init() throws ServletException {

        final Object restaurant = getServletContext().getAttribute("restaurant");
        if (restaurant == null) {
            throw new IllegalStateException("You're restaurant does not initialize! )))))");
        } else {
            this.restaurant = (Restaurant) getServletContext().getAttribute("restaurant");
        }

        menuHot= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.HOT);
        menuSalat= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.SALAD);
        menuSoup= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.SOUP);
        menuGarnish= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.GARNISH);
        menuHotDrink= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.HOT_DRINK);
        menuCouldDrink= (ArrayList<Dish>) this.restaurant.getMenu().getMenuByType(DishType.COULD_DRINK);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("menu", this.restaurant.getMenu());
        req.setAttribute("queueOrders", restaurant.getOrdersListFromDd());
        req.setAttribute("menuHot", menuHot);
        req.setAttribute("menuSalat", menuSalat);
        req.setAttribute("menuSoup", menuSoup);
        req.setAttribute("menuGarnish", menuGarnish);
        req.setAttribute("menuHotDrink", menuHotDrink);
        req.setAttribute("menuCouldDrink", menuCouldDrink);
        req.setAttribute("restaurant",restaurant);
        req.getRequestDispatcher("/WEB-INF/view/table_menu.jsp").forward(req, resp);

    }

}
