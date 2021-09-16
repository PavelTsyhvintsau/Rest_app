package com.company.model.kitchen;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private final String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public boolean isBusy() {
        return busy;
    }

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
        busy = true;
        Order order = arg;
        ConsoleHelper.writeMessage("Start cooking - " + order);

        /*CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(row);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        setChanged();
        notifyObservers(order);
        busy = false;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                if (!queue.isEmpty()) {
                    if (!this.isBusy()) {
                        this.startCookingOrder(queue.take());
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }

}
