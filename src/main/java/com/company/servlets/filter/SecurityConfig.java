package com.company.servlets.filter;

import java.util.*;

public class SecurityConfig {
    public static final String ADMIN = "ADMIN";
    public static final String COOK = "COOK";
    public static final String WAITER = "WAITER";
    public static final String MANAGER = "MANAGER";
    public static final String TABLET_TABLE = "TABLET_TABLE";
    public static final String UNKNOWN = "UNKNOWN";
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "ADMIN".
        List<String> urlAdmin = new ArrayList<String>();
        urlAdmin.add("/updateUsers");
        urlAdmin.add("/add_user");
        urlAdmin.add("/delete_user");
        urlAdmin.add("/update_user");
        urlAdmin.add("/add_user");
        urlAdmin.add("/logout");
        urlAdmin.add("/update_dish_price");
        urlAdmin.add("/admin_look_orders");
        urlAdmin.add("/style/style1.css");
        urlAdmin.add("/js/timer.js");
        urlAdmin.add("/order_info");
        urlAdmin.add("/admin_statistic_page");
        urlAdmin.add("/cooks_statistic");
        urlAdmin.add("/waiter_statistic");
        urlAdmin.add("/dish_statistic");
        urlAdmin.add("/order_completed_page");
        mapConfig.put(ADMIN, urlAdmin);


        // Конфигурация для роли "COOK".
        List<String> urlCook = new ArrayList<String>();
        urlCook.add("/dishes_menu_editor");
        urlCook.add("/add_dish");
        urlCook.add("/delete_dish");
        urlCook.add("/update_dish");
        urlCook.add("/add_dish");
        urlCook.add("/activate_dish");
        urlCook.add("/logout");
        urlCook.add("/cooking_page");
        urlCook.add("/activate_cook");
        urlCook.add("/order_completed");
        urlCook.add("/js/timer.js");
        urlCook.add("/style/style1.css");
        urlCook.add("/order_info");
        mapConfig.put(COOK, urlCook);


        // Конфигурация для роли "WAITER".
        List<String> urlWaiter = new ArrayList<String>();
        urlWaiter.add("/menu_for_ordering");
        urlWaiter.add("/logout");
        urlWaiter.add("/add_dish_to_order");
        urlWaiter.add("/add_order_to_queue");
        urlWaiter.add("/waiter_ordering");
        urlWaiter.add("/order_to_bank");
        urlWaiter.add("/style/style1.css");
        urlWaiter.add("/js/timer.js");
        urlWaiter.add("/order_info");
        urlWaiter.add("/waiter_create_tabletTable");

        mapConfig.put(WAITER,urlWaiter);

        // Конфигурация для роли "TABLET_TABLE".
        List<String> urlTable = new ArrayList<String>();
        urlTable.add("/table_menu");
        urlTable.add("/add_dish_to_orderTable");
        urlTable.add("/add_order_to_TableQueue");
        urlTable.add("/logout");
        urlTable.add("/style/style1.css");
        mapConfig.put(TABLET_TABLE, urlTable);

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

