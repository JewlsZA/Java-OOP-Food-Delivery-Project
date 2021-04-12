package com.foodquick;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FoodQuick {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //array of Food to be used across Restaurants
        Food[] arrFood = new Food[3];
        arrFood[0] = new Food("Pizza", 90);
        arrFood[1] = new Food("Burger", 120);
        arrFood[2] = new Food("Coke", 25);
        //allow storage of multiple restaurants, customers, orders
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        //while loop keeps the program running unless user exits the program with option 5
        while (true) {//keep the current loop active unless break is called
            //local variables to store instances of customers and restaurants
            Customer customer = null;
            Restaurant restaurant = null;

            String choice = offerMainMenuSelection(sc);

            //Customer Creation
            if (choice.equals("1")) {
                createCustomer(sc, customers);

                //Restaurant Creation
            } else if (choice.equals("2")) {
                createRestaurant(sc, restaurants);

                //Order Creation
            } else if (choice.equals("3")) {
                createOrder(sc, arrFood, restaurants, customers, orders);

                // Invoices file creation

                //Print Customer(Sorted) name and Ordernumber
            } else if (choice.equals("4")) {
                printCustomerAndOrderNumberToFile(orders);

                //Print Customer name and location(grouped)
            } else if (choice.equals("5")){
                printCustomerAndLocationToFile(customers, orders);

                // Program exit
            } else if (choice.equals("6")) {
                System.out.println("You chose number 6!");
                System.out.println("Program terminated");
                break;
            }
        }
        sc.close();
    }
    //function/methods called in main
    //Outputs a text file that shows a list of the customer’s names
    //grouped by location.
    private static void printCustomerAndLocationToFile(ArrayList<Customer> customers, ArrayList<Order> orders) throws FileNotFoundException {
        System.out.println("You chose number 5!");
        System.out.println("Printing Customer list by location");
        Formatter c = new Formatter("Customer and Location List.txt");

        Comparator<Order> orderComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getCustomerLocation().compareTo(o2.getCustomerLocation());
            }
        };

        Collections.sort(orders, orderComparator);

        for(Customer myCustomer: customers) {
            c.format("%s %s %s", myCustomer.getCustomerName(), myCustomer.getLocation(), "\r\n");
        }
        c.close();
    }

    //Outputs a text file that shows a list of the customer’s names and
    //order numbers in alphabetical order
    private static void printCustomerAndOrderNumberToFile(ArrayList<Order> orders) throws FileNotFoundException {
        System.out.println("You chose number 4!");
        System.out.println("Printing Customers and Order list file");
        Formatter c = new Formatter("Customer and OrderNumber List.txt");
        Collections.sort(orders);

        for (Order myOrder: orders) {
            // [0] Order order
            c.format("%s %d %s", myOrder.getCustomerName(), myOrder.getOrderNumber(), "\r\n");
        }
        c.close();
    }
    // creates a food order for chosen restaurant and customer
    private static void createOrder(Scanner sc, Food[] arrFood, ArrayList<Restaurant> restaurants, ArrayList<Customer> customers, ArrayList<Order> orders) throws IOException {
        System.out.println("You chose number 3!");
        if (restaurants.size() == 0) {
            System.out.println("Please create a Restaurant to continue");
            System.out.println("Returning to main menu");
        } else if (customers.size() == 0) {
            System.out.println("Please create a Customer to continue");
            System.out.println("Returning to main menu");
        } else {
            System.out.println("Please select your Restaurant");

            int printOptionCount = 0;
            // foreach loop prints out all restaurants
            for (Restaurant printRestaurant : restaurants) {
                printOptionCount++;
                System.out.println(String.format("%d. %s", printOptionCount, printRestaurant));
            }
            int chosenRestaurantInt = sc.nextInt();

            System.out.println("Please select your Customer");

            printOptionCount = 0;
            // foreach loop prints out all customers
            for (Customer printCustomer : customers) {
                printOptionCount++;
                System.out.println(String.format("%d. %s", printOptionCount, printCustomer));
            }
            int chosenCustomerInt = sc.nextInt();

            Restaurant chosenRestaurant = restaurants.get(chosenRestaurantInt -1);
            Customer chosenCustomer = customers.get(chosenCustomerInt -1);
            Driver assignedDriver = Drivers.assignDriver(chosenRestaurant, chosenCustomer);
            //create a new order with chosen objects and adds to the orders Array
            Order order = new Order(chosenRestaurant, chosenCustomer, assignedDriver);
            orders.add(order);

            System.out.println("Creating new Order");
            //add  food and food item quantity to the Order by user input
            while (true) {//keep the current loop active unless break is called

                System.out.println("Welcome " + chosenCustomer.getCustomerName() + " to "
                        + chosenRestaurant.getRestaurantName());
                System.out.println("To add a Pizza enter 1");
                System.out.println("To add a Burger enter 2");
                System.out.println("To add a Coke enter 3");
                System.out.println("To finish order enter 4");
                String chosenMeal = sc.next();
                // Pizza
                if (chosenMeal.equals("1")) {
                    System.out.println("How many Pizzas would you like to order? e.g. 3");
                    int mealQuantity = sc.nextInt();
                    order.addMeal(arrFood[0], mealQuantity);

                    //Burger
                } else if (chosenMeal.equals("2")) {
                    System.out.println("How many Burgers would you like to order? e.g. 2");
                    int mealQuantity = sc.nextInt();
                    order.addMeal(arrFood[1], mealQuantity);

                    //Coke
                } else if (chosenMeal.equals("3")) {
                    System.out.println("How many Cokes would you like to order? e.g. 4");
                    int mealQuantity = sc.nextInt();
                    order.addMeal(arrFood[2], mealQuantity);

                    //Order finish button
                } else if (chosenMeal.equals("4")) {
                    System.out.println("You chose number 4!");
                    System.out.println("Please enter any special instructions for your order, else enter 0");
                    // Meal special instructions are read in here
                    sc.nextLine();
                    String specialInstructions = sc.nextLine();

                    if (specialInstructions == "0") {
                        order.setSpecialInstructions("No Special instructions");
                    } else {
                        order.setSpecialInstructions(specialInstructions);
                    }
                    System.out.println("Order completed!");
                    // calls the method to print the invoice to file
                    order.printInvoice();
                    // increments the assigned driver deliveries count
                    if (assignedDriver != null) {
                        assignedDriver.incrementDriverDeliveries();
                    }

                    Drivers.writeDriverFile();
                    break;
                }
            }
        }
    }
    // restaurant creation method by user input
    private static void createRestaurant(Scanner sc, ArrayList<Restaurant> restaurants) {
        Restaurant restaurant;
        System.out.println("You chose number 2!");
        System.out.println("Creating new Restaurant");
        System.out.println("Please enter restaurant name, e.g. Colcacchio");
        String name = sc.nextLine();
        System.out.println("Please enter restaurant number, e.g. 0835546998");
        String number = sc.nextLine();
        System.out.println("Please enter restaurant city , e.g. Durban");
        String location = sc.nextLine();
        System.out.println("Restaurant successfully created");
        System.out.println("Returning to main menu");
        // inputs to restaurant object
        restaurant = new Restaurant(name, number, location);
        restaurants.add(restaurant);
    }
    // program main menu, this menu is printed to screen after each selection is completed
    private static String offerMainMenuSelection(Scanner sc) {
        System.out.println("Welcome to Food Quick!");
        System.out.println("To create a new Customer enter 1");
        System.out.println("To create a new Restaurant enter 2");
        System.out.println("To create a new Order enter 3");
        System.out.println("To print all Customer and OrderNumber enter 4");
        System.out.println("To print all Customers with location enter 5");
        System.out.println("To exit the program enter 6");
        String choice = sc.nextLine();
        return choice;
    }
    // customer creation method by user input
    private static void createCustomer(Scanner sc, ArrayList<Customer> customers) {
        Customer customer;
        System.out.println("You chose number 1!");
        System.out.println("Creating new Customer");
        System.out.println("Please enter customer name, e.g. John");
        String name = sc.nextLine();
        System.out.println("Please enter customer number, e.g. 0835546998");
        String number = sc.nextLine();
        System.out.println("Please enter customer address number, e.g. 12");
        String addressNum = sc.nextLine();
        System.out.println("Please enter customer address street, e.g. church");
        String addressStreet = sc.nextLine();
        System.out.println("Please enter customer area, e.g. Cape Flats");
        String addressArea = sc.nextLine();
        System.out.println("Please enter customer city, e.g. Cape Town");
        String location = sc.nextLine();
        System.out.println("Please enter customer email, e.g. John@gmail.com");
        String email = sc.nextLine();
        System.out.println("Customer successfully created");
        System.out.println("Returning to main menu");
        // inputs to customer object
        customer = new Customer(name, number, addressNum, addressStreet, addressArea, location, email);
        customers.add(customer);
    }
}


