package com.company.servlets.servlet;

import com.company.model.Restaurant;
import com.company.model.kitchen.dishes.Dish;
import com.company.servlets.filter.SecurityConfig;
import com.company.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig
public class UpdateDishServlet extends HttpServlet {
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
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final int cookingTime = Integer.parseInt(req.getParameter("cookingTime"));
        final String type=req.getParameter("type");
        String imagePath=null;
        Part filePart = req.getPart("file");
        if(filePart.getSize()!=0){
            InputStream fileContent = filePart.getInputStream();
            String dir="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0_Tomcat9055\\webapps\\ROOT\\images\\";
            String namePhoto= Utils.generateName() +".jpeg";
            File foto=new File(dir,namePhoto);
            foto.createNewFile();
            Utils.putDishPhoto(fileContent,foto);
            imagePath="images/"+namePhoto;

        }else{
            imagePath=restaurant.getDishFromDB(Integer.parseInt(id)).getDishImagePath();
        }


        restaurant.updateDish(id,name,type,cookingTime,imagePath);
        resp.sendRedirect(req.getContextPath() + "/dishes_menu_editor");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id =Integer.valueOf(req.getParameter("id"));
        Dish dish =restaurant.getDishFromDB(id);
        req.setAttribute("dish", dish);
        req.setAttribute("menu", restaurant.getMenu());

        req.getRequestDispatcher("/WEB-INF/view/update_dish.jsp").forward(req, resp);
    }
}
