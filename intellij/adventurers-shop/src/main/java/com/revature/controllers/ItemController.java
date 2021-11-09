package com.revature.controllers;

import com.revature.models.Item;
import com.revature.services.ItemService;

import java.util.Scanner;

public class ItemController {
    public final ItemService is;

    public ItemController() {
        is = new ItemService();
    }

    public void addUnownedItemForSale(Scanner sc) {
        System.out.println("What is the name of the item you would like to sell?");
        String name = sc.nextLine();
        System.out.println("How much would you like to sell the item for?");
        String price = sc.nextLine();
        Item newItemToSell = new Item(name, price);
        is.addUnownedItemForSale(newItemToSell);
        System.out.println(name + " listed for $" + price);
    }

    public void removeItem(Scanner sc){
        System.out.println("What item would you like to remove?");
        String name = sc.nextLine();
        is.removeItemByName(name);
        System.out.println(name + " was removed from the shop.");

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
