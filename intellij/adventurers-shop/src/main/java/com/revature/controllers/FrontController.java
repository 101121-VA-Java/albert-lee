package com.revature.controllers;

import java.util.Scanner;

public class FrontController {
    private Scanner sc;
    private EmployeeController ec;

    public FrontController() {
        sc = new Scanner(System.in);
        ec = new EmployeeController();
    }

    public void run() {
        String status = "loggedOut";
        while(status.equals("loggedOut")) {
            printLoggedOutOptions();
            String choice = sc.nextLine();
            switch(choice) {
                case "1":
                    // TODO: try to login
                    ec.searchForEmployee(sc);
                    // TODO: find the user based on inputs given
                    // TODO: if an employee is not found, handle error message and or redirect to registering
                    break;
                case "2":
                    //TODO: try to register
                    ec.registerEmployee(sc);
                    //TODO: if registration fails, handle error message or input validations

                    break;
                case "3":
                    System.out.println("You've chosen to exit. Now exiting.");
                    status = "exit";
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        sc.close();
    }

    private void printLoggedOutOptions() {
        System.out.println("Welcome to the Adventurer's Shop!");
        System.out.println("Enter the appropriate number to get started.");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Exit");
    }
}