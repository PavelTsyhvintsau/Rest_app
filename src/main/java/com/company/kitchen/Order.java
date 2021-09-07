package com.company.kitchen;

import com.company.TabletWaiter;
import com.company.kitchen.dishes.Dish;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final TabletWaiter tabletWaiter;
    protected HashMap<Dish, Integer> dishes;

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public TabletWaiter getTablet() {
        return tabletWaiter;
    }

    public Order(TabletWaiter tablet) throws IOException {
        this.tabletWaiter = tablet;
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
