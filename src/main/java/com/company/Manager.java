package com.company;

import com.company.kitchen.ConsoleHelper;
import com.company.kitchen.Cook;
import com.company.kitchen.Order;

import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;

public class Manager {
    private SynchronousQueue<Order> orderSynchronousQueue=new SynchronousQueue<>(true);
    private HashMap<String, Cook> cookHashMap =new HashMap<>();
    private HashMap<String, Waiter> waiterHashMap =new HashMap<>();
    private final SynchronousQueue<Order> queue = new SynchronousQueue(true);

    public HashMap<String, Cook> getCooksMap() {
        return cookHashMap;
    }

    public Cook cookCreator(String name){
        if (cookHashMap.containsKey(name)){
            ConsoleHelper.writeMessage(" name ["+name+"] busy... take anather name");
            return cookHashMap.get(name);
        }else return cookHashMap.put(name, new Cook(name));
    }
    public Waiter waiterCreator(String name){
        if (waiterHashMap.containsKey(name)){
            ConsoleHelper.writeMessage(" name ["+name+"] busy... take anather name");
            return waiterHashMap.get(name);
        }else return waiterHashMap.put(name, new Waiter(name));
    }


}
