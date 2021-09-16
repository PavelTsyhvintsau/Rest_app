package com.company.servlets.filter;

import java.util.*;

public class SecurityConfig {
    public static final String ADMIN = "ADMIN";
    public static final String COOK = "COOK";
    public static final String WAITER = "WAITER";
    public static final String MANAGER = "MANAGER";
    public static final String TABLET_TABLE = "TABLET_TABLE";
    public static final String UNKNOWN = "UNKNOWN";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {
        System.out.println("now init SecurityConf");

        // Конфигурация для роли "ADMIN".
        List<String> urlAdmin = new ArrayList<String>();

        urlAdmin.add("/updateUsers");
        urlAdmin.add("/add_user");
        urlAdmin.add("/delete_user");
        urlAdmin.add("/update_user");
        urlAdmin.add("/add_user");

        mapConfig.put(COOK, urlAdmin);

        // Конфигурация для роли "COOK".
        List<String> urlCook = new ArrayList<String>();

        urlCook.add("/dishes_menu_editor");
        urlCook.add("/add_dish");
        urlCook.add("/delete_dish");
        urlCook.add("/update_dish");
        urlCook.add("/add_dish");
        urlCook.add("/activate_dish");

        mapConfig.put(ADMIN, urlCook);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
    public static List<String> getUrlPatternsAllSecurityPages() {
        ArrayList<String>result=new ArrayList<>();
        for (Map.Entry<String, List<String>> entry:mapConfig.entrySet()){
            for(String e:entry.getValue()){
               result.add(e);
            }
        }
        return result;
    }

}

