package com.revature.controllers;

import com.revature.models.items.Item;
import com.revature.services.ItemService;

import java.util.Scanner;

public class ItemController {
    private final ItemService is = new ItemService();
    public void add(Scanner scan){
        System.out.println("What item would you like to sell?");
        String itemName = scan.nextLine();
        System.out.println("Please enter the price you would like for your item.");
        String price = scan.nextLine();
        Item itemToSell = new Item(itemName, price);
        is.add(itemToSell);
        System.out.println(itemName + " was successfully listed.");
    }

    public void remove(Scanner scan) {
        //could possibly remove duplicates, fix or re-implement later
        System.out.println("What item would you like to remove?");
        String itemName = scan.nextLine();
        is.remove(itemName);
        System.out.println(itemName + " was successfully listed.");
    }

    public void printAllItemsForSale(){
        is.printAllItemsForSale();
    }
}
