package com.foodquick;

public class Meal {

    //Attributes
    private Food foodItem;
    private int mealQuantity;

    //Constructor
    public Meal(Food foodItem, int mealQuantity) {
        this.foodItem = foodItem;
        this.mealQuantity = mealQuantity;
    }

    public Food getFoodItem() {
        return foodItem;
    }

    public int getMealQuantity() {
        return mealQuantity;
    }
    // returns the total meal cost of a given order
    public double getMealCost() {
        return this.foodItem.getFoodPrice() * mealQuantity;
    }
}
