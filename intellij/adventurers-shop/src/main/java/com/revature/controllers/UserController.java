package com.revature.controllers;

import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Offer;
import com.revature.models.Item;
import com.revature.models.users.User;
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

    public void logout() {
        this.currentUser = null;
    }

    public boolean isValidOffer(int newOfferPrice, int prevOfferPrice, int buyPrice) {
        if(newOfferPrice >= buyPrice){
            System.out.println("Please just buy the item for $" + buyPrice + " instead of bidding.");
            return false;
        } else if (currentUser.getCashOnHand() < newOfferPrice) {
            System.out.println("Sorry, you can't afford that offer at the moment.");
            return false;
        } else if (newOfferPrice <= prevOfferPrice) {
            System.out.println(("Sorry, your offer must be greater than the previous one."));
            return false;
        }
        return true;
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
            us.register(newUser, roleChoice);
            System.out.println("Registration successful.");
            System.out.println("Logged in as " + newUser.getUsername() + ".");
            this.currentUser = newUser;
        } catch (InvalidRole e) {
            System.out.println("Please choose a valid role next time. 1 for customer, 2 for employee.");
        }
    }

    public void printPlayerInventory() {
        if(us.getPlayerInventory((currentUser.getId())).getItems().isEmpty()){
            System.out.println("You don't have any items at the moment!");
            return;
        }
        for (Item item : us.getPlayerInventory(currentUser.getId()).getItems()) {
            System.out.println(item.getName());
        }
    }

    public void makeOffer(Item result) {
        if (result == null) System.out.println("Item not found");
        else {
            int newOfferPrice = getNewOfferPrice(result);
            if (isValidOffer(newOfferPrice, result.getHighestOfferPrice(), result.getPrice())) {
                result.setHighestOffer(new Offer(newOfferPrice, currentUser.getId()));
                System.out.println("Congratulations. You now have the highest bid of $" + result.getHighestOfferPrice() + " for " + result.getName());
            }
        }
    }

    private int getNewOfferPrice(Item result) {
        return result.hasOffer() ? takeInFirstBid() : takeInCompetingBid(result);
    }

    private int takeInFirstBid() {
        System.out.println("There are no bids yet. How much would you like to offer?");
        return Integer.parseInt(sc.nextLine());
    }

    private int takeInCompetingBid(Item result) {
        System.out.println("The current highest bid is: $" + result.getHighestOfferPrice());
        System.out.println("How much would you like to bid? Bids lower than or equal to the current highest offer will be ignored.");
        return Integer.parseInt(sc.nextLine());
    }

    public void acceptOffer(Item item){
        us.addItemToInventory(item, currentUser.getId());
    }
}
