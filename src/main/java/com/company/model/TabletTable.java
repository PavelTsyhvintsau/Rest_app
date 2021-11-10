package com.company.model;

import com.company.model.kitchen.Order;

public class TabletTable {
    private User user;
    private Order order;
    private int table;

    public TabletTable(User user, int table) {
        this.user = user;
        this.table = table;
        order=new Order(user);
    }
}
