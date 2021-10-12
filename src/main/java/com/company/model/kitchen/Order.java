package com.company.model.kitchen;


import com.company.model.User;

import com.company.model.kitchen.dishes.Dish;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final User user;
    private HashMap<Dish, Integer> dishes;
    private  int totalPrice=0;
    private int id;
    private int tableNumber;
    private long orderCreateTime;
    private String orderStartCreateString;
    private long orderStartCookingTime;
    private String orderStartCookingTimeString;
    private long orderEndCookingTime;
    private String orderEndCookingTimeString;

    public String getOrderCreateTime(){
        String result=new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderCreateTime);
        result=formatter.format(date);
        this.orderStartCreateString =result;
        return result;
    }

    public void setOrderCreateTime(long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public void setOrderStartCookingTime(long orderStartCookingTime) {
        this.orderStartCookingTime = orderStartCookingTime;
    }
    public String getOrderStartCookingTime(){
        String result=new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderStartCookingTime);
        result=formatter.format(date);
        this.orderStartCookingTimeString =result;
        return result;
    }
    public void setOrderEndCookingTime(long orderEndCookingTime) {
        this.orderEndCookingTime = orderEndCookingTime;
    }
    public String getOrderEndCookingTime(){
        String result=new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderEndCookingTime);
        result=formatter.format(date);
        this.orderEndCookingTimeString =result;
        return result;
    }

    public int getTableNumber() {return tableNumber; }

    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber;}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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
    }public int getCookingTimeMinutes() {
        return getTotalCookingTime()%60;
    }
    public int getCookingTimeHours() {
         return getTotalCookingTime()/60;
    }
    protected void initDishes() throws IOException {
        dishes=new HashMap<>();
        //дописать инициализацию списка заказа
        // this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

}
