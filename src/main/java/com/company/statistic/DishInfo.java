package com.company.statistic;

import com.company.model.kitchen.dishes.Dish;

public class DishInfo {
    private int count;
    private int cost;
    private Dish dish;

    public DishInfo(Dish dish) {
        this.dish = dish;
        cost=0;
        count=0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Dish getDish() {
        return dish;
    }

    public String getName() {
        return dish.getDishName();
    }
    public int getRubCost(){
        return cost/100;
    }
    public int getPennyCost(){
        return cost%100;
    }
}
