package com.revature.controllers;

public class FrontController {
    private final UserController uc;
    private final ShopController shc;
    private String status = "run";

    public FrontController() {
        uc = new UserController();
        shc = new ShopController();
    }

    public void run() {
        coreLogic();
        uc.sc.close();
    }

    private void coreLogic() {
        while (status.equals("run")) {
            if (uc.isLoggedOut()) loggedOutOptions();
            if (uc.isCustomer()) customerOptions();
            if (uc.isEmployee()) employeeOptions();
        }
    }

    private void loggedOutOptions() {
        printLoggedOutOptions();
        String choice = uc.sc.nextLine();
        switch (choice) {
            case "1":
                uc.attemptLogin(uc.sc);
                break;
            case "2":
                uc.attemptRegistration(uc.sc);
                break;
            case "3":
                this.status = "exit";
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    private void customerOptions() {
        printCustomerOptions();
        String choice = uc.sc.nextLine();
        switch (choice) {
            case "1":
                shc.printAllItemsForSale();
                break;
            case "2":
                uc.printPlayerInventory();
                break;
            case "3":
                makeOffer();
                break;
            case "4":
                //view remaining payments for an item
                break;
            case "5":
                status = "exit";
                break;
            case "6":
                uc.logout();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    private void employeeOptions() {
        printEmployeeOptions();
        String choice = uc.sc.nextLine();
        switch (choice) {
            case "1":
                shc.add(uc.sc);
                break;
            case "2":
                shc.remove(uc.sc);
                break;
            case "3":
                // checkOffer. which item. if item exists, print offer price. accept y/n.
                break;
            case "4":
                //view payments for all items
                break;
            case "5":
                status = "exit";
                break;
            case "6":
                uc.logout();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    private void printLoggedOutOptions(){
        System.out.println("Welcome to the Adventurer's Shop!");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Exit");
    }

    private void printCustomerOptions(){
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: View items for sale");
        System.out.println("2: View my inventory");
        System.out.println("3: Make an offer for an item");
        System.out.println("4: View remaining payments for an item");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }

    private void printEmployeeOptions() {
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: List item for sale");
        System.out.println("2: Remove listing");
        System.out.println("3: Accept or reject the current highest offer for an item");
        System.out.println("4: View all payments");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }

    private void makeOffer() throws RuntimeException{
        if(uc.getCurrentUser() == null) throw new RuntimeException();
        System.out.println("What is the name of the item you would like to bid on?");
        uc.makeOffer(shc.getItemByName(uc.sc.nextLine()));
    }
}