package com.company.util;



import com.company.model.kitchen.dishes.Dish;
import com.company.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null &&
                (id.length() > 0) &&
                id.matches("[+]?\\d+");
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        return login != null && login.length() > 0 &&
                password != null && password.length() > 0 &&
                password.matches("[+]?\\d+");
    }
    public static boolean idUserIsInvalid(final String id, Map<Integer, User> repo) {

        boolean invalid=!(id != null &&
                id.matches("[+]?\\d+") &&
                repo.get(Integer.parseInt(id)) != null);
        System.out.println("user id invalid = "+invalid);
        return invalid;
    }
    public static boolean idDishIsInvalid(final String id, Map<Integer, Dish> repo) {
        boolean invalid=!(id != null &&
                id.matches("[+]?\\d+") &&
                repo.get(Integer.parseInt(id)) != null);
        System.out.println("dish id invalid = "+invalid);
        return invalid;
    }
    public static String formatTimeDate(long data){
        String result="";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result=formater.format(new Date(data));
        return result;
    }
    public static void putDishPhoto( InputStream fileContent, File file) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = fileContent;
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            is.close();
            os.close();
        }
    }
    public static String generateName(){
        String result=null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S");
        result=simpleDateFormat.format(new Date());
        return "file_"+result;
    }
}
