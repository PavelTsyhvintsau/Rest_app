package com.company.servlets.servlet;


import com.company.model.Restaurant;
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
import java.util.concurrent.atomic.AtomicInteger;

@MultipartConfig
public class AddDishServlet extends HttpServlet {

    private AtomicInteger id;
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
        System.out.println("do post AddDishServlet in work");
        req.setCharacterEncoding("UTF-8");
        final String dishName = req.getParameter("dishName");
        final String dishType =req.getParameter("dishType");
        final int dishCookingTime = Integer.parseInt(req.getParameter("dishCookingTime"));


        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        String dir="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0_Tomcat9055\\webapps\\ROOT\\images\\";
        String namePhoto= Utils.generateName() +".jpeg";
        File foto=new File(dir,namePhoto);
        foto.createNewFile();
        Utils.putDishPhoto(fileContent,foto);
        String imagePath="images/"+namePhoto;

        restaurant.addDish(dishName,dishType,dishCookingTime,imagePath);
        resp.sendRedirect(req.getContextPath()+"/dishes_menu_editor");
    }
}
