package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.statistic.WaiterInfo;
import com.company.statistic.CookInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

public class WaiterStatisticServlet extends HttpServlet {
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
        final String start = req.getParameter("trip-start");
        final String end = req.getParameter("trip-end");
        final String[] users=req.getParameterValues("usersID");
        ArrayList<WaiterInfo> infoWaitersList=new ArrayList<>();
        List<String> cooksArray=new ArrayList<>();
        cooksArray= Arrays.asList(users);
        if (cooksArray.contains("all")){
            for (User user:restaurant.getDao().get().getStore()){
                if(user.getRole().equals(User.ROLE.WAITER))
                    infoWaitersList.add(new WaiterInfo(user.getId(), start,end,restaurant));
            }
        }else{
            for (String name:users){
                int id=Integer.parseInt(name);
                infoWaitersList.add(new WaiterInfo(id, start,end,restaurant));
            }
        }
        if (sortBy.equals("byName")){
            infoWaitersList.sort(comparing(WaiterInfo::getName));
        }
        if (sortBy.equals("byCount")){
            infoWaitersList.sort(comparing(WaiterInfo::getCountOrders));
        }
        if (sortBy.equals("byCost")){
            infoWaitersList.sort(comparing(WaiterInfo::getOrdersCost));
        }
        req.setAttribute("start",start);
        req.setAttribute("end",end);
        req.setAttribute("infoWaitersList", infoWaitersList);

        req.getRequestDispatcher("/WEB-INF/view/waitersStat.jsp").forward(req, resp);
    }

}
