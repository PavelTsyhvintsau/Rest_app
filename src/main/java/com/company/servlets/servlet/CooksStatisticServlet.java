package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.User;
import com.company.statistic.CookInfo;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparing;

public class CooksStatisticServlet extends HttpServlet {
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
            final String[] cooks=req.getParameterValues("cooks");
            ArrayList<CookInfo> infoCooksList=new ArrayList<>();
            List<String> cooksArray=new ArrayList<>();
            cooksArray= Arrays.asList(cooks);
            if (cooksArray.contains("all")){
                for (User user:restaurant.getDao((ComboPooledDataSource)req.getAttribute("cpds")).get().getStore()){
                    if(user.getRole().equals(User.ROLE.COOK))
                    infoCooksList.add(new CookInfo(user.getLogin(), start,end,restaurant.getOrdersBank()));
                }
            }else{
                for (String cookName:cooks){
                    infoCooksList.add(new CookInfo(cookName, start,end,restaurant.getOrdersBank()));
                }
            }
            if (sortBy.equals("byName")){
                infoCooksList.sort(comparing(CookInfo::getName));
            }
        if (sortBy.equals("byLongPractik")){
            infoCooksList.sort(comparing(CookInfo::getOrdersLongPraсtic));
        }
        if (sortBy.equals("byCount")){
            infoCooksList.sort(comparing(CookInfo::getCountOrders));
        }
        if (sortBy.equals("byLongTheory")){
            infoCooksList.sort(comparing(CookInfo::getOrdersLongTheory));
        }
        req.setAttribute("infoCooksList", infoCooksList);
        req.getRequestDispatcher("/WEB-INF/view/cooksStat.jsp").forward(req, resp);
    }
}