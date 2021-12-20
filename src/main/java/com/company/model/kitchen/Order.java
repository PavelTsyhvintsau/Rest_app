package com.company.model.kitchen;

import com.company.model.User;
import com.company.model.kitchen.dishes.Dish;
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
    private long orderToClientTime;
    private String orderToClientTimeString;
    private Cook cook;
    private boolean done;
    private Orderstatus orderstatus;

    public enum Orderstatus{
        INQUEUE,
        INWORK,
        ISREADY,
        ISCLOSE
    }
    public Order(User user){
        this.user = user;
        initDishes();
    }

    public long getOrderCreateTime() { return orderCreateTime; }
    public long getOrderStartCookingTime() { return orderStartCookingTime; }
    public long getOrderStartCookingTimeLong(){
        return orderStartCookingTime;
    }
    public long getOrderEndCookingTimeLong(){
        return orderEndCookingTime;
    }
    public Orderstatus getOrderstatus() {
        return orderstatus;
    }
    public long getOrderToClientTime() {
        return orderToClientTime;
    }
    public String getOrderToClientTimeString() {
        if (orderToClientTime==0){return "---";}
        else {
            String result=new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderToClientTime);
        result=formatter.format(date);
        this.orderToClientTimeString =result;
        return result;
        }
    }
    public boolean isDone() {
        return done;
    }
    public String getOrderCreateTimeString(){
        String result=new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderCreateTime);
        result=formatter.format(date);
        this.orderStartCreateString =result;
        return result;
    }
    public Cook getCook() {return cook; }
    public void setCook(Cook cook) { this.cook = cook; }
    public void setDone(boolean done) {
        this.done = done;
    }
    public void setOrderstatus(Orderstatus orderstatus) {
        this.orderstatus = orderstatus;
    }
    public void setOrderToClientTime(long orderToClientTime) {
        this.orderToClientTime = orderToClientTime;
    }
    public void setOrderCreateTime(long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
    public void setOrderStartCookingTime(long orderStartCookingTime) {
        this.orderStartCookingTime = orderStartCookingTime;
    }
    public String getOrderStartCookingTimeString(){
        if(orderStartCookingTime==0){
            return "---";
        }else{
            String result=new String();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date =new Date(orderStartCookingTime);
            result=formatter.format(date);
            this.orderStartCookingTimeString =result;
            return result;
        }
    }
    public String getOrderTimeFromCreating(){
        Long curTime=System.currentTimeMillis();
        Long facticalLiveTime=(curTime-orderCreateTime);
        long second = (facticalLiveTime / 1000) % 60;
        long minute = (facticalLiveTime / (1000 * 60)) % 60;
        long hour = (facticalLiveTime / (1000 * 60 * 60)) % 24;
        String time = String.format("%02d:%02d:%02d", hour, minute, second);
        return time;
    }
    public String getTimeFactCookingString(){
        String result=new String();
        if(orderStartCookingTime==0|| orderEndCookingTime==0){
            result="---";
            return result;
        }else{
        long delta=(orderEndCookingTime-orderStartCookingTime);
            long second = (delta / 1000) % 60;
            long minute = (delta / (1000 * 60)) % 60;
            long hour = (delta / (1000 * 60 * 60)) % 24;
        result=String.format("%02d:%02d:%02d", hour, minute, second);
        return result;}
    }
    public void setOrderEndCookingTime(long orderEndCookingTime) {
        this.orderEndCookingTime = orderEndCookingTime;
    }
    public String getOrderEndCookingTime(){
        String result=new String();
        if(orderEndCookingTime==0){
            result="---";
            return result;
        }else{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date =new Date(orderEndCookingTime);
        result=formatter.format(date);
        this.orderEndCookingTimeString =result;
        return result;}
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
    public User getUser() {
        return user;
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
    public int getCookingTimeMinutes() {
        return getTotalCookingTime()%60;
    }
    public int getCookingTimeHours() {
         return getTotalCookingTime()/60;
    }
    protected void initDishes(){dishes=new HashMap<>();}
    public Integer[][] getDishesArray(){
        Integer [][] array=new Integer[2][dishes.size()];
        int i=0;
        for(Map.Entry<Dish,Integer> e:dishes.entrySet()){
            array[0][i]=e.getKey().getId();
            array[1][i]=e.getValue();
            i++;
        }
        return array;

    }
}
