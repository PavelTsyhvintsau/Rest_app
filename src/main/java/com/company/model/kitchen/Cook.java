package com.company.model.kitchen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable {
    private final String name;
    //private boolean busy;
    private LinkedBlockingQueue<Order> queue;
    private Order currentOrder;

    public Order getCurrentOrder() { return currentOrder; }

    //public void setBusy(boolean busy) { this.busy = busy; }

    public LinkedBlockingQueue<Order> getQueue() {
        return queue;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

   // public boolean isBusy() {        return busy;}

    public String getName() {
        return name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order arg) {
        //busy = true;
        currentOrder = arg;

        ConsoleHelper.writeMessage("Start cooking - " + currentOrder);

        /*CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(row);*/
        try {
            Thread.sleep(currentOrder.getTotalCookingTime() * 1000+180*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(currentOrder);

        //busy = false;
        currentOrder=null;
    }


}
