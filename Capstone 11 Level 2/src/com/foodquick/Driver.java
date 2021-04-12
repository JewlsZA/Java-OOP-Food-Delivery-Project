package com.foodquick;

public class Driver implements Comparable<Integer> {
    // Attributes
   private String driverName;
   private String driverLocation;
   private int driverDeliveries;

    // Constructor
    public Driver(String driverName, String driverLocation, int driverDeliveries) {
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.driverDeliveries = driverDeliveries;
    }

    // adds +1 driver deliveries to a driver that was allocated a order
    public void incrementDriverDeliveries(){
        this.driverDeliveries = driverDeliveries + 1;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public int getDriverDeliveries() {
        return driverDeliveries;
    }

    public void setDriverDeliveries(int driverDeliveries) {
        this.driverDeliveries = driverDeliveries;
    }

    //method compares driver with least deliveries in right conditions
    @Override
    public int compareTo(Integer driverDeliveries) {
        // x > y        -> x is left hand, y is right hand
        // 3 > 5
        // Rules:
        // 1 if the left hand value is greater than the right hand value in the condition
        // 0 if equal
        // -1 if less than

        int returnValue = 0;

        if (this.driverDeliveries > driverDeliveries) {
            returnValue = 1;
        } else if (this.driverDeliveries == driverDeliveries) {
            returnValue = 0;
        }  else {
            returnValue = -1;
        }

        return returnValue;
    }
    // toString for writing back to driver file
    @Override
    public String toString() {
        return String.format("%s, %s, %d", this.driverName, this.driverLocation, this.driverDeliveries);
    }
}
