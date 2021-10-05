package com.company.model;

import com.company.model.kitchen.Cook;

public class User {

    private int id;

    private String login;

    private String password;

    private ROLE role;
    private Cook cook;

    public User() {
    }

    public User(int id, String login, String password, ROLE role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        if(role.equals(ROLE.COOK)){
            cook=new Cook(login);
        }
    }

    public Cook getCook() {
        return cook;
    }


    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public enum ROLE {
        ADMIN,
        TABLET_TABLE,
        COOK,
        MANAGER,
        WAITER,
        UNKNOWN
    }
}