package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.model.kitchen.Order;
import com.company.model.kitchen.dishes.Dish;
import com.company.statistic.DishInfo;
import com.company.statistic.WaiterInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Comparator.comparing;

public class DishStatisticServlet extends HttpServlet {
    private Restaurant restaurant;

    @Override
    public void init() throws ServletException {

        final Object restaurant = getServletContext().getAttribute("restaurant");
        if (restaurant == null) {
            throw new IllegalStateException("You're restaurant does not initialize! )))))");
        } else {
            this.restaurant = (Restaurant) getServletContext().getAttribute("restaurant");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String sortBy = req.getParameter("sortBy");
        final String dateStart = req.getParameter("trip-start");
        final String dateEnd = req.getParameter("trip-end");
        final String[] dishes=req.getParameterValues("dishes");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<Integer,DishInfo> dishInfoHashMap=new HashMap<>();
        List<String>dishArray= Arrays.asList(dishes);
        if (dishArray.contains("all")){
            for (Order order:restaurant.getOrdersListFromDd()){
                Date startDate=new Date(order.getOrderStartCookingTimeLong());
                try {
                    Date endDate=new Date(format.parse(dateEnd).getTime()+(long)(24*60*60*1000));
                    if (order.getOrderstatus().equals(Order.Orderstatus.ISCLOSE)) {
                        if(startDate.before(format.parse(dateStart))||
                                startDate.after(endDate)) {

                        }else {
                            for (Map.Entry<Dish, Integer> entry : order.getDishes().entrySet()) {
                                if (dishInfoHashMap.containsKey(entry.getKey().getId())) {
                                    int count = dishInfoHashMap.get(entry.getKey().getId()).getCount() + entry.getValue();
                                    int cost = dishInfoHashMap.get(entry.getKey().getId()).getCost() + (entry.getValue() * entry.getKey().getPrice());
                                    dishInfoHashMap.get(entry.getKey().getId()).setCost(cost);
                                    dishInfoHashMap.get(entry.getKey().getId()).setCount(count);
                                } else {
                                    DishInfo dishInfo1 = new DishInfo(entry.getKey());
                                    dishInfo1.setCount(entry.getValue());
                                    dishInfo1.setCost(entry.getValue() * entry.getKey().getPrice());
                                    dishInfoHashMap.put(entry.getKey().getId(), dishInfo1);
                                }
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();System.out.println("----ошибка1");
                }
            }
        }else{
            for (Order order:restaurant.getOrdersListFromDd()){
                Date startDate=new Date(order.getOrderStartCookingTimeLong());
                try {
                    Date endDate=new Date(format.parse(dateEnd).getTime()+(long)(24*60*60*1000));
                    if (order.getOrderstatus().equals(Order.Orderstatus.ISCLOSE)) {
                        if(startDate.before(format.parse(dateStart))||
                                startDate.after(endDate)) {

                        }else {
                            for (Map.Entry<Dish, Integer> entry : order.getDishes().entrySet()) {
                                if (dishInfoHashMap.containsKey(entry.getKey().getId())&&dishArray.contains(String.valueOf(entry.getKey().getId()))) {
                                    int count = dishInfoHashMap.get(entry.getKey().getId()).getCount() + entry.getValue();
                                    int cost = dishInfoHashMap.get(entry.getKey().getId()).getCost() + (entry.getValue() * entry.getKey().getPrice());
                                    dishInfoHashMap.get(entry.getKey().getId()).setCost(cost);
                                    dishInfoHashMap.get(entry.getKey().getId()).setCount(count);
                                } else {
                                    if(dishArray.contains(String.valueOf(entry.getKey().getId()))) {
                                        DishInfo dishInfo1 = new DishInfo(entry.getKey());
                                        dishInfo1.setCount(entry.getValue());
                                        dishInfo1.setCost(entry.getValue() * entry.getKey().getPrice());
                                        dishInfoHashMap.put(entry.getKey().getId(), dishInfo1);
                                    }
                                }
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace(); System.out.println("----ошибка2");
                }
            }
        }

        List <DishInfo> infoDishList=new ArrayList<>();
        for(Map.Entry<Integer,DishInfo> entry:dishInfoHashMap.entrySet()){
            infoDishList.add(entry.getValue());
        }
        if (sortBy.equals("byName")){
            infoDishList.sort(comparing(DishInfo::getName));
        }
        if (sortBy.equals("byCount")){
            infoDishList.sort(comparing(DishInfo::getCount));
        }
        if (sortBy.equals("byCost")){
            infoDishList.sort(comparing(DishInfo::getCost));
        }
        req.setAttribute("infoDishList", infoDishList);
        req.setAttribute("start",dateStart);
        req.setAttribute("end",dateEnd);
        req.getRequestDispatcher("/WEB-INF/view/dishStat.jsp").forward(req, resp);
    }

}
