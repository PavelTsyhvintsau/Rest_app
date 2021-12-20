package com.company.model;

import com.company.model.kitchen.Cook;
import com.company.model.kitchen.dishes.Dish;

public class User {

    private int id;
    private String login;
    private String password;
    private ROLE role;
    private Cook cook;
    //private int tableNumber;
    private boolean isActive;


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
        //tableNumber=0;
        isActive=true;
    }

    public boolean isActive() { return isActive;}
    public void setActive(boolean active) { isActive = active; }
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User guest = (User) obj;
        return id == guest.id
                && (login == guest.getLogin()
                || (login != null &&login.equals(guest.getLogin())))        && (password == guest.getPassword()
                || (password != null && password .equals(guest.getPassword())
        ));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = id;
        result = prime * result + ((login == null) ? 0 : login.hashCode());             result = prime * result + id; result = prime * result +
                ((password == null) ? 0 : password.hashCode()); return result;
    }
}