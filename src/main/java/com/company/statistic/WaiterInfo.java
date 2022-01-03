package com.company.statistic;

import com.company.model.Restaurant;
import com.company.model.kitchen.Order;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;

public class  WaiterInfo {
    private int id;
    private String name;
    private String dateStart;
    private String dateEnd;
    private ArrayList<Order> userListOrders;
    private ArrayList<Order> dataList;
    private int ordersLongTheory;
    private long ordersLongPraсtic;
    private int ordersCost;

    public WaiterInfo(int id, String dateStart, String dateEnd, Restaurant restaurant) {
        this.id=id;
        this.name =restaurant.getDao().get().getById(id).getLogin();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dataList = restaurant.getOrdersListFromDd();
        this.userListOrders = new ArrayList<>();
        this.ordersLongTheory = 0;//милисекунды
        this.ordersLongPraсtic = 0;
        this.ordersCost=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Order order : dataList) {
            try {
                if (order.getCreatorID()==id&& order.getOrderstatus().equals(Order.Orderstatus.ISCLOSE)) {
                    Date startDate=new Date(order.getOrderStartCookingTimeLong());
                    Date endDate=new Date(format.parse(dateEnd).getTime()+(long)(24*60*60*1000));
                    if(startDate.before(format.parse(dateStart))||
                            startDate.after(endDate)){
                        return;
                    } else{
                        userListOrders.add(order);
                        ordersLongTheory += order.getTotalCookingTime();
                        ordersLongPraсtic += (order.getOrderToClientTime() - order.getOrderEndCookingTimeLong())/60000;
                        ordersCost+=order.getTotalPrice();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int getRublesPrice(){
        return ordersCost/100;
    }
    public int getPennyPrice(){
        return ordersCost%100;
    }
    public int getId(){return id;}
    public String getName() {
        return name;
    }
    public String getDateStart () {
        return dateStart;
    }
    public String getDateEnd () {
        return dateEnd;
    }
    public ArrayList<Order> getListOrders () {
        return userListOrders;
    }
    public int getCountOrders () {
        return userListOrders.size();
    }
    public int getOrdersLongTheory () {
        return ordersLongTheory;
    }
    public long getOrdersLongPraсtic () {
        return ordersLongPraсtic;
    }
    public int getOrdersCost() {
        return ordersCost;
    }
    public void setOrdersCost(int ordersCost) {
        this.ordersCost = ordersCost;
    }
}