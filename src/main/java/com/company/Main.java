package com.company;

import com.company.model.Manager;
import com.company.model.User;
import com.company.servlets.filter.SecurityConfig;
import com.company.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ArrayList<String> arl=new ArrayList<>();
        System.out.println(arl );
        for (User.ROLE e: User.ROLE.values()){
            arl.add(e.toString());
        }
        System.out.println(arl);

    }
}
