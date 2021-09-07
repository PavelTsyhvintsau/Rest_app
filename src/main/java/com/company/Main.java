package com.company;

import com.company.statistic.StatisticManager;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        StatisticManager statisticManager=StatisticManager.getInstance();
        Manager manager=new Manager();
        manager.cookCreator("ivan");
        manager.cookCreator("petr");
        manager.cookCreator("ivan");
        manager.cookCreator("petr2");
        manager.cookCreator("petr");
        System.out.println(Arrays.deepToString(manager.getCooksMap().keySet().toArray()));

    }
}
