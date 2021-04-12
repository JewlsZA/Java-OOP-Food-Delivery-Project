package com.foodquick;

public class Food {
    //Attributes
    private String foodName;
    private double foodPrice;

    //Constructor
    public Food(String foodName, double foodPrice) {

        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    //Getters & Setters
    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }


}
