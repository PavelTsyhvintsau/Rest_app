package com.company.model.kitchen;

import com.company.model.kitchen.dishes.Dish;
import com.company.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final int creatorID;
    private HashMap<Dish, Integer> dishes;
    private  int totalPrice=0;
    private int id;
    private int tableNumber;
    private long orderCreateTime;
    private String orderStartCreateString;
    private long orderStartCookingTime=-1;
    private String orderStartCookingTimeString;
    private long orderEndCookingTime=-1;
    private String orderEndCookingTimeString;
    private long orderToClientTime=-1;
    private String orderToClientTimeString;
    private int cookID=-1;
    private boolean done;
    private Orderstatus orderstatus;

    public enum Orderstatus{
        INQUEUE,
        INWORK,
        ISREADY,
        ISCLOSE
    }
    public Order(int userID){
        this.creatorID = userID;
        initDishes();
    }

    public Order(int userID,
                 HashMap<Dish, Integer> dishes,
                 int id, int tableNumber,
                 long orderCreateTime,
                 long orderStartCookingTime,
                 long orderEndCookingTime,
                 long orderToClientTime,
                 boolean done,
                 Order.Orderstatus orderstatus,
                 int cookID) {
        this.creatorID = userID;
        this.dishes = dishes;
        this.id = id;
        this.tableNumber = tableNumber;
        this.orderCreateTime = orderCreateTime;
        this.orderStartCookingTime = orderStartCookingTime;
        this.orderEndCookingTime = orderEndCookingTime;
        this.orderToClientTime = orderToClientTime;
        this.done = done;
        this.orderstatus = orderstatus;
        this.cookID=cookID;
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
        if (orderToClientTime==-1){return "---";}
        else {
            String result=Utils.formatTimeDate(orderToClientTime);
        this.orderToClientTimeString =result;
        return result;
        }
    }
    public boolean isDone() {
        return done;
    }
    public String getOrderCreateTimeString(){
        String result= Utils.formatTimeDate(orderCreateTime);
        this.orderStartCreateString =result;
        return result;
    }
    public int getCookID() {return cookID; }
    public void setCookID(int cookID) { this.cookID = cookID; }
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
        if(orderStartCookingTime==-1){
            return "---";
        }else{
            String result=Utils.formatTimeDate(orderStartCookingTime);
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
        if(orderStartCookingTime==-1|| orderEndCookingTime==-1){
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
        if(orderEndCookingTime==-1){
            result="---";
            return result;
        }else{
        result=Utils.formatTimeDate(orderEndCookingTime);
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
    public int getCreatorID() {
        return creatorID;
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
