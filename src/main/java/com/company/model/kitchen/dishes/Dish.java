package com.company.model.kitchen.dishes;

public class Dish {
    private String dishName;
    private int dishCookingTime;
    private String dishImagePath;
    private DishType dishType;
    private int id;
    private boolean active;
    private int price;

    public Dish() {}

    public int isActive() {
        if (active)return 1;
        else return 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getRublePrice(){
        return price/100;
    }
    public int getPennyPrice(){
        return price%100;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getDishName() {
        return dishName;
    }

    public int getDishCookingTime() {
        return dishCookingTime;
    }

    public String getDishImagePath() {
        return dishImagePath;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishCookingTime(int dishCookingTime) {
        this.dishCookingTime = dishCookingTime;
    }

    public void setDishImagePath(String dishImagePath) {
        this.dishImagePath = dishImagePath;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public Dish(String dishName, int dishCookingTime, String dishImagePath, DishType dishType, int id, int price) {
        this.dishName = dishName;
        this.dishCookingTime = dishCookingTime;
        this.dishImagePath = dishImagePath;
        this.dishType = dishType;
        this.id=id;
        this.active=false;
        this.price=price;
    }
}
