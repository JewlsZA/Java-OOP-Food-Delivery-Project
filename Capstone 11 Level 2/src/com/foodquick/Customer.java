package com.foodquick;

public class Customer {

    // Attributes
    private String customerName;
    private String phoneNumber;
    private String addressNum;
    private String addressStreet;
    private String addressArea;
    private String location;
    private String customerEmail;

    // Constructor
    public Customer(String customerName, String phoneNumber, String addressNum, String addressStreet, String addressArea, String location, String customerEmail) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.addressNum = addressNum;
        this.addressStreet = addressStreet;
        this.addressArea = addressArea;
        this.location = location;
        this.customerEmail = customerEmail;
    }

    //Get and Set methods
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressNum() {
        return addressNum;
    }

    public void setAddressNum(String addressNum) {
        this.addressNum = addressNum;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    //toString for selecting the customer to complete the order
    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.customerName, this.location, this.phoneNumber);
    }
}
