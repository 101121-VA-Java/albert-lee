package com.revature.controllers;

import com.revature.models.Item;
import com.revature.services.ShopService;
import java.util.Scanner;

public class ShopController {
    private final ShopService ss;

    public ShopController() {
        this.ss = new ShopService();
    }

    public void add(Scanner scan){
        System.out.println("What item would you like to sell?");
        String itemName = scan.nextLine();
        System.out.println("Please enter the price you would like for your item.");
        String price = scan.nextLine();
        Item itemToSell = new Item(itemName, price);
        ss.add(itemToSell);
        System.out.println(itemName + " was successfully listed.");
    }

    public void remove(Scanner scan) {
        System.out.println("What item would you like to remove?");
        String itemName = scan.nextLine();
        ss.remove(itemName);
        System.out.println(itemName + " was successfully removed.");
    }

    public void printAllItemsForSale(){
        if(ss.isEmpty()) System.out.println("Nothing is for sale yet!");
        else ss.printAllItemsForSale();
    }

    public Item getItemByName(String name){
        return ss.getItemByName(name);
    }
}
