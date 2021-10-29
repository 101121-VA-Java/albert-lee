package com.revature.controllers;

import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.users.User;
import com.revature.services.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService us = new UserService();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void attemptLogin(Scanner scan) {
        System.out.println("Please enter your username");
        String username = scan.nextLine();
        System.out.println("Please enter your password");
        String password = scan.nextLine();
        try {
            User foundUser = us.login(username, password);
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
            us.register(newUser, roleChoice);
        } catch (InvalidRole e) {
            System.out.println("Please choose a valid role next time. 1 for customer, 2 for employee.");
        }

        System.out.println("Employee has been registered.");
        this.currentUser = newUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
