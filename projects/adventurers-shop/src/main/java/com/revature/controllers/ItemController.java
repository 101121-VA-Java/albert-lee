package com.revature.controllers;

import com.revature.models.items.Item;
import com.revature.models.items.RiskyItem;
import com.revature.services.ItemService;

import java.util.Scanner;

public class ItemController {
    private final ItemService is;
    private static ItemController firstInstance = null;

    private ItemController() {
        is = new ItemService();
    }

    public static ItemController getInstance() {
        if(firstInstance == null){
            firstInstance = new ItemController();
        }
        return firstInstance;
    }

    public void addUnownedItemForSale(Scanner sc) {
        System.out.println("What is the name of the item you would like to sell?");
        String name = sc.nextLine();
        System.out.println("How much would you like to sell the item for?");
        String price = sc.nextLine();
        System.out.println("Is this item risky (1) or boring (2)?" );
        int choice = Integer.parseInt(sc.nextLine());
        Item newItem = itemFactory(name, price, choice);
        int status = is.addUnownedItemForSale(newItem);
        if(status != -1) System.out.println(name + " listed for $" + price);
    }

    public void removeItem(Scanner sc){
        System.out.println("What item would you like to remove?");
        String name = sc.nextLine();
        int status = is.removeItemByName(name);
        if(status != -1) System.out.println(name + " was removed from the shop.");
    }

    public void printAllUnownedItemsForSale() {
        is.printItemsForSale();
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

    public ItemService getItemService() {
        return is;
    }

    public Item itemFactory(String name, String price, int type){
        return (type == 1) ? new RiskyItem(name, price, "RISKY") : new Item(name, price, "BORING");
    }
}
