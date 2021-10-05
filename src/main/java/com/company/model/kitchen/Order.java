package com.company.model.kitchen;


import com.company.model.User;

import com.company.model.kitchen.dishes.Dish;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final User user;
    private HashMap<Dish, Integer> dishes;
    private  int totalPrice=0;

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }
    public int getTotalPrice(){
        int counter=0;
        if (dishes!=null){
        for(Map.Entry e:dishes.entrySet()){
            counter+= (int) e.getValue()*((Dish)e.getKey()).getPrice();
        }
        return counter;}
        else return counter;
    }
    public int getRublePrice(){
        return getTotalPrice()/100;
    }
    public int getPennyPrice(){
        return getTotalPrice()%100;
    }

    public User getWaiter() {
        return user;
    }
    public Order(User user) throws IOException {
        this.user = user;
        initDishes();
        //ConsoleHelper.writeMessage(toString());
    }
    public void putDish(Dish dish,Integer count){
        if (dishes==null){
            dishes=new HashMap<>();
            dishes.put(dish,count);
        }
        else if (dishes.keySet().contains(dish)){
            int newCount=dishes.get(dish)+count;
            if(newCount<=0){
                dishes.remove(dish);
            }else {
            dishes.put(dish,newCount);}
        }else if(count<=0){}
        else {
            dishes.put(dish,count);
        }
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
        dishes=new HashMap<>();
        //дописать инициализацию списка заказа
        // this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

}
