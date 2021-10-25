package com.revature.driver;

import com.revature.exceptions.BuyException;
import com.revature.models.*;

public class Driver {
    public static void main(String[] args) {
        Customer c1 = new Customer("Billy", 100);
        Seller s1 = new Seller("Bob");

        c1.setName("John");
        c1.printName();
        System.out.println(s1.getName());

        Shop shop1 = new Shop();

        Dagger exampleDagger = new Dagger(101, "Fancy Unaffordable Knife");

        shop1.setOneItemForSale(exampleDagger);
        System.out.println(shop1.getNumberOfItemsForSale() + " item(s) on sale.");

        try {
            System.out.println("Attempting to buy " + exampleDagger.getName() + ".");
            c1.buyDagger(exampleDagger);
        } catch (BuyException e) {
            System.out.println("Sorry, you can't afford that right now.");
        }
    }
}
