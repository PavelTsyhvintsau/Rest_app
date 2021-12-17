package com.company.dao;

import com.company.model.kitchen.dishes.Dish;
import com.company.model.kitchen.dishes.DishType;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Dish> dishesList=new ArrayList<>();
    private final List<String> dishTypeList = new ArrayList<>();
    public List<String> getDishTypeList(){
            return dishTypeList;
    }
    public List<Dish> getDishesList() {
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
}
