package com.revature.controllers;

public class FrontController {
    private final ItemController ic;
    private final OfferController oc;
    private final PaymentController pc;
    private final UserController uc;
    private String status = "run";

    public FrontController() {
        ic = new ItemController();
        oc = new OfferController();
        pc = new PaymentController();
        uc = new UserController();
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
                ic.printAllUnownedItemsForSale();
                break;
            case "2":
                ic.printItemsByOwnerId(uc.getCurrentUser().getId());
                break;
            case "3":
                oc.attemptOffer(uc, ic);
                break;
            case "4":
                pc.makePayment(uc.sc);
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
                ic.addUnownedItemForSale(uc.sc);
                break;
            case "2":
                ic.removeItem(uc.sc);
                break;
            case "3":
                oc.acceptOffer(uc, ic);
                break;
            case "4":
                pc.printAllPaymentsOutstanding();
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
        System.out.println("4: Make a payment");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }

    private void printEmployeeOptions() {
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: List item for sale");
        System.out.println("2: Remove listing");
        System.out.println("3: Accept or reject the current highest offer for an item");
        System.out.println("4: View all outstanding payments");
        System.out.println("5: Exit");
        System.out.println("6: Log out");
    }
}