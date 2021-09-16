package com.company.model.kitchen;


import com.company.model.TabletTable;
import com.company.model.kitchen.dishes.Dish;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final TabletTable tabletTable;
    protected HashMap<Dish, Integer> dishes;

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public TabletTable getTabletTable() {
        return tabletTable;
    }

    public Order(TabletTable tablet) throws IOException {
        this.tabletTable = tablet;
        initDishes();
        //ConsoleHelper.writeMessage(toString());
    }
    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime() {
        int cookingTime = 0;
        if (!isEmpty()){
            for(Map.Entry entry:dishes.entrySet()){
                cookingTime+=((Dish)entry.getKey()).getDishCookingTime();
            }
        }

        return cookingTime;
    }

    protected void initDishes() throws IOException {
        //дописать инициализацию списка заказа
        // this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

}
