package com.foodquick;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Drivers {
  private static ArrayList<Driver> drivers = loadDrivers();

    private static ArrayList<Driver> loadDrivers() {
        ArrayList<Driver> tempDrivers = new ArrayList<>();
        try {
            File driversFile = new File("drivers.txt");
            Scanner sc = new Scanner(driversFile);

            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                String[] sDriver = nextLine.split(", ");

                int currentDriverLoad = 0;

                if (sDriver.length > 2) {
                    currentDriverLoad = sDriver[2].isEmpty() ? 0 : Integer.parseInt(sDriver[2]);
                }

                Driver tempDriver = new Driver(sDriver[0], sDriver[1], currentDriverLoad);
                tempDrivers.add(tempDriver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempDrivers;
    }
    //method returns a driver that meets the same location criteria as customer and restaurant
    public static Driver assignDriver(Restaurant restaurant, Customer customer) {
        Supplier<Stream<Driver>> streamSupplier = () -> {
            Stream<Driver> sameLocationDrivers = drivers.stream().filter(
                    (Driver driver) -> restaurant.getLocation().equals(driver.getDriverLocation())
                            && customer.getLocation().equals(driver.getDriverLocation()));
            return sameLocationDrivers;
        };

        Optional<Driver> optionalLeastEncumberedDriver = streamSupplier.get().min(
                (Driver driver1, Driver driver2) -> driver1.compareTo(driver2.getDriverDeliveries()));
        Driver leastEncumberedDriver = null;

        // Increment the load of the driver being assigned
        if (optionalLeastEncumberedDriver.isPresent()) {
            leastEncumberedDriver = optionalLeastEncumberedDriver.get();
        }

        return leastEncumberedDriver;
    }
    // updates the drivers file with the new driver loads
    public static void writeDriverFile() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("drivers.txt"));

        drivers.forEach((driver) -> {
            try {
                bufferedWriter.write(driver.toString() + "\r\n");
            } catch (IOException e) {
                System.out.println("File Write Error");
                e.printStackTrace();
            }
        });

        bufferedWriter.close();
    }
}