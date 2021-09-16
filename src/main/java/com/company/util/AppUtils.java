package com.company.util;

import com.company.model.User;

import javax.servlet.http.HttpSession;

public class AppUtils {


    // Сохранить информацию пользователя в Session.
    public static void setSessionUserParam(HttpSession session, User.ROLE role, String login) {

        session.setAttribute("role", role);
        session.setAttribute("login", login);
    }

    // Получить роль пользователя, сохраненную в Session.
    public static User.ROLE getSessionRole(HttpSession session) {
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        return role;
    }
    // Получить логин пользователя, сохраненную в Session.
    public static String getSessionLogin(HttpSession session) {
        String login = (String) session.getAttribute("login");
        return login;
    }


}
