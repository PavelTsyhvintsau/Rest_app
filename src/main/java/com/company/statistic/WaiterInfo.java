package com.company.statistic;

import com.company.model.kitchen.Order;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;

public class  WaiterInfo {
    private String name;
    private String dateStart;
    private String dateEnd;
    private ArrayList<Order> userListOrders;
    private ArrayList<Order> dataList;
    private int ordersLongTheory;
    private long ordersLongPraсtic;
    private int ordersCost;

    public WaiterInfo(String name, String dateStart, String dateEnd, ArrayList<Order> listOrders) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dataList = listOrders;
        this.userListOrders = new ArrayList<>();
        this.ordersLongTheory = 0;
        this.ordersLongPraсtic = 0;
        this.ordersCost=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Order order : dataList) {
            try {
                if (order.getUser().getLogin().equals(name) &&
                        order.getOrderstatus().equals(Order.Orderstatus.ISCLOSE)) {
                    Date startDate=new Date(order.getOrderStartCookingTimeLong());
                    Date endDate=new Date(format.parse(dateEnd).getTime()+(long)(24*60*60*1000));
                    if(startDate.before(format.parse(dateStart))||
                            startDate.after(endDate)){
                        return;
                    } else{
                        userListOrders.add(order);
                        ordersLongTheory += order.getTotalCookingTime();
                        ordersLongPraсtic += order.getOrderEndCookingTimeLong() - order.getOrderStartCookingTimeLong();
                        ordersCost+=order.getTotalPrice();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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