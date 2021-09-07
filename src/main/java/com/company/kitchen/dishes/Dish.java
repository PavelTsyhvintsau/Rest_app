package com.company.kitchen.dishes;

public class Dish {
    private String dishName;
    private int dishCookingTime;
    private String dishImagePath;
    private DishType dishType;

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

    public Dish(String dishName, int dishCookingTime, String dishImagePath, DishType dishType) {
        this.dishName = dishName;
        this.dishCookingTime = dishCookingTime;
        this.dishImagePath = dishImagePath;
        this.dishType = dishType;
    }
}
