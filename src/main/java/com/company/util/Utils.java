package com.company.util;



import com.company.model.kitchen.dishes.Dish;
import com.company.model.User;

import javax.servlet.http.HttpServletRequest;
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
}
