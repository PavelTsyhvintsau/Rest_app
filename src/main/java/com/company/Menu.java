package com.company;

import com.company.kitchen.dishes.Dish;
import com.company.kitchen.dishes.DishType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Menu {
    private HashSet<Dish> dishesList=new HashSet<>();

    public HashSet<Dish> getMenu() {
        return dishesList;
    }

    public List<Dish> getMenuByType(DishType dishType){
        List<Dish> typeDishesList=new ArrayList<>();
        for (Dish dish:dishesList
             ) {if(dish.getDishType().equals(dishType))typeDishesList.add(dish);
        }return typeDishesList;
    }
    public boolean addDishToMenu(Dish dish){
        return dishesList.add(dish);
    }
    public boolean remooveDishFromMenu(Dish dish){
        return dishesList.remove(dish);
    }
    public boolean contains (Dish dish){
        return dishesList.contains(dish);
    }
}
