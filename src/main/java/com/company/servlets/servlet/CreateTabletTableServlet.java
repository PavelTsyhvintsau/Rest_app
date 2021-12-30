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

public class CreateTabletTableServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user=(User)req.getSession().getAttribute("user");
        System.out.println("1=////////////");
        user.setRole(User.ROLE.TABLET_TABLE);
        System.out.println("2=////////////");
        req.getSession().setAttribute("user", user);
        System.out.println("3=////////////");
        Order order=(Order)req.getSession().getAttribute("order");
        System.out.println("4=////////////");
        final String tableNumberStr = req.getParameter("tableNumber");
        System.out.println("5=////////////");
        final int tableNumber = Integer.parseInt(tableNumberStr);
        System.out.println("6=////////////");
        order.setTableNumber(tableNumber);
        System.out.println("7=////////////");
        req.getSession().setAttribute("order",order);
        System.out.println("8=////////////");
        req.getSession().setAttribute("role", User.ROLE.TABLET_TABLE);
        System.out.println("9=////////////");
        req.setAttribute("orderTable", order);
        System.out.println("10=////////////");
        req.setAttribute("menu", this.restaurant.getMenu());
        System.out.println("11=////////////");
        req.setAttribute("queueOrders", restaurant.getOrdersListFromDd());
        System.out.println("12=////////////");
        req.setAttribute("menuHot", menuHot);
        System.out.println("13=////////////");
        req.setAttribute("menuSalat", menuSalat);
        System.out.println("14=////////////");
        req.setAttribute("menuSoup", menuSoup);
        System.out.println("16=////////////");
        req.setAttribute("menuGarnish", menuGarnish);
        System.out.println("17=////////////");
        req.setAttribute("menuHotDrink", menuHotDrink);
        System.out.println("18=////////////");
        req.setAttribute("menuCouldDrink", menuCouldDrink);
        System.out.println("19=////////////");
        req.setAttribute("restaurant",restaurant);
        System.out.println("20=////////////");

        req.getRequestDispatcher("/WEB-INF/view/table_menu.jsp").forward(req, resp);
        System.out.println("21=////////////");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/view/create_tabletTable.jsp").forward(req, res);

    }

}
