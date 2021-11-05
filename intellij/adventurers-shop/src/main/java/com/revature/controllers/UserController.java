package com.revature.controllers;

import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

import java.util.Scanner;

public class UserController {
    public Scanner sc;
    private final UserService us;
    private User currentUser;

    public UserController() {
        sc = new Scanner(System.in);
        this.us = new UserService();
    }

    public boolean isCustomer() {
        if (currentUser == null) return false;
        return currentUser.getRole().equals("CUSTOMER");
    }

    public boolean isEmployee() {
        if (currentUser == null) return false;
        return currentUser.getRole().equals("EMPLOYEE");
    }

    public boolean isLoggedOut() {
        return currentUser == null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }

    public void attemptLogin(Scanner scan) {
        System.out.println("Please enter your username");
        String username = scan.nextLine();
        System.out.println("Please enter your password");
        String password = scan.nextLine();
        try {
            User foundUser = us.login(username, password);
            this.currentUser = foundUser;
            System.out.println("Logged in as " + foundUser.getUsername());
        } catch (UserNotFoundException e) {
            System.out.println("User doesn't exist.");
        }
    }

    public void attemptRegistration(Scanner scan) {
        System.out.println("Please enter a username:");
        String username = scan.nextLine();
        System.out.println("Please enter a password:");
        String password = scan.nextLine();
        User newUser = new User(username, password);
        System.out.println("Please choose a role. 1 for customer, 2 for employee.");
        String roleChoice = scan.nextLine();
        try {
            int newUserId = us.register(newUser, roleChoice);
            System.out.println("Registration successful.");
            System.out.println("Logged in as " + newUser.getUsername() + ".");
            this.currentUser = newUser;
            this.currentUser.setId(newUserId);
        } catch (InvalidRole e) {
            System.out.println("Please choose a valid role next time. 1 for customer, 2 for employee.");
        }
    }

}
