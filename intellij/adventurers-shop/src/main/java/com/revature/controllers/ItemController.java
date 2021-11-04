package com.revature.controllers;

import com.revature.models.Item;
import com.revature.services.ItemService;

import java.util.Scanner;

public class ItemController {
    private final ItemService is;

    public ItemController() {
        is = new ItemService();
    }

    private void makeOffer(Scanner sc) {
        System.out.println("What is the name of the item you would like to bid on?");
        String itemName = sc.nextLine();
        for (Item item : is.getAll()) {
            if (item.getName().equals(itemName)) {
                // get new bid price. if valid, replace with new highest bid.
            }
        }
        System.out.println("Item not found");
    }

    private void acceptOffer(Scanner sc) {
        System.out.println("What is the name of the item you want to sell for its highest current bid price?");
        //uc.acceptOffer(shc.remove(sc.nextLine()));
    }

    public void printAllUnownedItemsForSale() {
        if (is.getAll().isEmpty()) {
            System.out.println("Everything is sold out; please check later.");
        } else {
            System.out.println(is.getAll().size() + " item(s) for sale.");
            for (Item item : is.getAll()) {
                System.out.println("$" + item.getPrice() + " " + item.getName());
            }
        }
    }

    public void printItemsByOwnerId(int id) {
        if (is.getItemsBelongingToUserId(id).isEmpty()) {
            System.out.println("You don't have any items yet!");
        } else {
            System.out.println("You have " + is.getItemsBelongingToUserId(id).size() + " item(s).");
            for (Item item : is.getItemsBelongingToUserId(id)) {
                System.out.println(item.getName());
            }
        }
    }
}
