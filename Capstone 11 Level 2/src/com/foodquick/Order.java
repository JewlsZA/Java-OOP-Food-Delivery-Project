package com.foodquick;

import java.util.ArrayList;
import java.util.Formatter;

public class Order implements Comparable<Order> {

    private Restaurant restaurant;
    private Customer customer;
    private Driver driver;
    private ArrayList<Meal> mealOrders;
    private String specialInstructions;
    private int orderNumber;

    public String getCustomerName(){
       return customer.getCustomerName();
    }

    public String getCustomerLocation(){
        return  customer.getLocation();
    }

    //constructor
    public Order(Restaurant restaurant, Customer customer, Driver driver) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.driver = driver;
        this.mealOrders = new ArrayList<>();
        setOrderNumber();
    }

    public double calculateOrderTotal() {
        double orderTotal = 0.00;
        for (int i = 0; i < mealOrders.size(); i++) {
            orderTotal += mealOrders.get(i).getMealCost();
        }
        return orderTotal;
    }
    //method adds a food with quantity to the meal order
    public void addMeal(Food food, int foodQuantity) {
        Meal newMeal = new Meal(food, foodQuantity);
        this.mealOrders.add(newMeal);
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    private void setOrderNumber() {
        this.orderNumber = ((int)(Math.random()*9000)+1000);
    }

    private String getDriverText() {
        if (this.driver == null) {
            return "Sorry! Our drivers are too far away from you to be able to deliver to your location";
        } else {
            return this.driver.getDriverName()
                    + " is nearest to the restaurant and so they will be delivering your order to you at:";
        }
    }
    // method prints the completed order to file, the file name is made unique using the order number
    public void printInvoice() {
        System.out.println("Generating Invoice...");

        try {
            String outputFileName = String.format("invoice-%s.txt", orderNumber);
            Formatter f = new Formatter(outputFileName);
            f.format("Order number: %d %s", getOrderNumber(), "\r\n");
            f.format("%s %s %s", "Customer: ", customer.getCustomerName(), "\r\n");
            f.format("%s %s %s", "Email: ", customer.getCustomerEmail(), "\r\n");
            f.format("%s %s %s", "Phone Number: ", customer.getPhoneNumber(), "\r\n");
            f.format("%s %s %s", "Location: ", customer.getLocation(), "\r\n");
            f.format("%s", "\r\n");
            f.format("%s %s %s %s %s", "You have ordered the following from", restaurant.getRestaurantName(), "in", restaurant.getLocation(), ": \r\n");
            f.format("%s", "\r\n");
            for (Meal mealOrder : mealOrders) {
                f.format("%d x %s (R %,.2f)\r\n", mealOrder.getMealQuantity(), mealOrder.getFoodItem().getFoodName(),
                        mealOrder.getFoodItem().getFoodPrice());
            }
            f.format("%s", "\r\n");
            f.format("%s %s %s", "Special Instructions: ", this.specialInstructions, "\r\n");
            f.format("%s", "\r\n");
            f.format("Total: %,.2f \r\n", this.calculateOrderTotal());
            f.format("%s", "\r\n");
            f.format("%s \r\n", this.getDriverText());
            f.format("%s", "\r\n");
            f.format("%s %s %s %s", customer.getAddressNum(), " ", customer.getAddressStreet(), "\r\n");
            f.format("%s %s", customer.getAddressArea(), "\r\n");
            f.format("%s", "\r\n");
            f.format("%s %s %s", "If you need to contact the restaurant, their number is ", restaurant.getPhoneNumber(), "\r\n");
            f.close();
        //exception handling
        } catch (Exception e) {
            System.out.println("Error printing to file");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Order orderToCompareTo) {
        return this.customer.getCustomerName().compareTo(orderToCompareTo.getCustomerName());
    }
}
