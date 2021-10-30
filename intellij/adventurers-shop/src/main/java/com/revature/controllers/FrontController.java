package com.revature.controllers;

import com.revature.models.Shop;
import com.revature.models.users.Role;
import java.util.Scanner;

public class FrontController {
    private Scanner sc;
    private UserController uc;
    private ItemController ic;
    private Shop ash;

    public FrontController() {
        sc = new Scanner(System.in);
        uc = new UserController();
        ic = new ItemController();
        ash = new Shop();
    }

    public void run() {
        coreLogic();
        sc.close();
    }

    private void coreLogic() {
        String status = "run";
        while (status.equals("run")) {
            if (uc.getCurrentUser() == null) {
                printLoggedOutOptions();
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        uc.attemptLogin(sc);
                        break;
                    case "2":
                        uc.attemptRegistration(sc);
                        break;
                    case "3":
                        status = "exit";
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }

            if (uc.getCurrentUser().getRole() == Role.CUSTOMER) {
                printCustomerOptions();
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ic.printAllItemsForSale();
                        break;
                    case "2":
                        ic.printPlayerInventory(uc.getCurrentUser());
                        break;
                    case "3":
                        ic.makeOffer(uc.getCurrentUser(), sc);
                        break;
                    case "4":
                        //view remaining payments for an item
                        break;
                    case "5":
                        status = "exit";
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }

            if (uc.getCurrentUser().getRole() == Role.EMPLOYEE) {
                printEmployeeOptions();
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ic.add(sc);
                        break;
                    case "2":
                        ic.remove(sc);
                        break;
                    case "3":
                        //offerDetails for specific item, accept or reject
                        break;
                    case "4":
                        //view payments for all items
                        System.out.println("option 4");
                        break;
                    case "5":
                        status = "exit";
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }
    }

    private void printLoggedOutOptions() {
        System.out.println("Welcome to the Adventurer's Shop!");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Exit");
    }

    private void printCustomerOptions() {
        System.out.println("You are logged in.");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: View items for sale");
        System.out.println("2: View items on sale that are mine");
        System.out.println("3: Make an offer for an item");
        System.out.println("4: View remaining payments for an item");
        System.out.println("5: Exit");
    }

    private void printEmployeeOptions() {
        System.out.println("You are logged in.");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: List item for sale");
        System.out.println("2: Remove listing");
        System.out.println("3: Accept or reject a pending offer for an item");
        System.out.println("4: View all payments");
        System.out.println("5: Exit");
    }


}