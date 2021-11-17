package com.revature;

import com.revature.controllers.*;

public class Driver {
    public static void main(String[] args) {
        ItemController ic = ItemController.getInstance();
        OfferController oc = OfferController.getInstance();
        PaymentController pc = PaymentController.getInstance();
        UserController uc = UserController.getInstance();
        String status = "run";

        while (status.equals("run")) {
            if (uc.isLoggedOut()) status = loggedOutOptions(uc);
            else if (uc.isCustomer()) status = customerOptions(ic, uc, oc, pc);
            else if (uc.isEmployee()) status = employeeOptions(ic, uc, oc, pc);
        }

        uc.getScanner().close();
    }

    private static String loggedOutOptions(UserController uc) {
        printLoggedOutOptions();
        String result = "run";
        switch (uc.getScanner().nextLine()) {
            case "1":
                uc.attemptLogin(uc.getScanner());
                break;
            case "2":
                uc.attemptRegistration(uc.getScanner());
                break;
            case "3":
                result = "exit";
                break;
            default:
                System.out.println("Invalid input.");
        }
        return result;
    }

    private static String customerOptions(ItemController ic, UserController uc, OfferController oc, PaymentController pc) {
        printCustomerOptions();
        String result = "run";
        switch (uc.getScanner().nextLine()) {
            case "1":
                ic.printAllUnownedItemsForSale();
                break;
            case "2":
                ic.printItemsByOwnerId(uc.getCurrentUser().getId());
                break;
            case "3":
                oc.attemptOffer(uc, ic);
                break;
            case "4":
                pc.makePayment(uc);
                break;
            case "5":
                result = "exit";
                break;
            case "6":
                uc.logout();
                break;
            default:
                System.out.println("Invalid input.");
        }
        return result;
    }

    private static String employeeOptions(ItemController ic, UserController uc, OfferController oc, PaymentController pc) {
        printEmployeeOptions();
        String result = "run";
        switch (uc.getScanner().nextLine()) {
            case "1":
                ic.addUnownedItemForSale(uc.getScanner());
                break;
            case "2":
                ic.removeItem(uc.getScanner());
                break;
            case "3":
                oc.acceptOffer(uc, ic, pc);
                break;
            case "4":
                pc.printAllPaymentsOutstanding();
                break;
            case "5":
                result = "exit";
                break;
            case "6":
                uc.logout();
                break;
            default:
                System.out.println("Invalid input.");
        }
        return result;
    }

    private static void printLoggedOutOptions(){
        System.out.println("Welcome to the Adventurer's Shop!");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Exit");
    }

    private static void printCustomerOptions(){
        System.out.println("This is the customer dashboard.");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: View items for sale");
        System.out.println("2: View my inventory");
        System.out.println("3: Make an offer for an item");
        System.out.println("4: Make a payment");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }

    private static void printEmployeeOptions() {
        System.out.println("This is the employee dashboard.");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: List item for sale");
        System.out.println("2: Remove listing");
        System.out.println("3: Accept or reject the current highest offer for an item");
        System.out.println("4: View all outstanding payments");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }
}
