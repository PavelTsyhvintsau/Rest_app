package com.company.model;

import com.company.dao.Menu;
import com.company.dao.UserDAO;
import com.company.model.kitchen.Order;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Restaurant {
    private AtomicReference<UserDAO> dao;
    private AtomicReference<Menu> menu;
    private LinkedBlockingQueue<Order> queueOrders;
        private ArrayList<Order> ordersBank;

    public AtomicReference<UserDAO> getDao() {
        return dao;
    }
    public void setDao(AtomicReference<UserDAO> dao) {
        this.dao = dao;
    }
    public AtomicReference<Menu> getMenu() {
        return menu;
    }
    public void setMenu(AtomicReference<Menu> menu) {
        this.menu = menu;
    }
    public LinkedBlockingQueue<Order> getQueueOrders() {
        return queueOrders;
    }
    public void setQueueOrders(LinkedBlockingQueue<Order> queueOrders) {
        this.queueOrders = queueOrders;
    }

    public ArrayList<Order> getOrdersBank() {
        return ordersBank;
    }

    public void setOrdersBank(ArrayList<Order> ordersBank) {
        this.ordersBank = ordersBank;
    }
}
