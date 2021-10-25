package com.revature.driver;

import com.revature.exceptions.BuyException;
import com.revature.models.*;

/*
OOP exercise
	- Create your own example of OOP principles in Java!
		- your project should include examples of each of the pillars and comments explaining how it's related to OOP
		- Ideally it would print examples to the console as well.

	- MVP (Minimum Viable Product)
		- Using proper package structure, include at least these concepts
			- Encapsulation
				- Access Modifiers
				- Setters/Getters
			- Inheritance
				- extends
				- super
			- Polymorphism
				- method overloading
				- method overriding
			- Abstraction
				- abstract keyword
				- interface keyword
		- Create a custom exception, throw it, and handle it!
*/

public class Driver {
    public static void main(String[] args) {
        // creating a buyer and a seller
        // used access modifiers to set and get instance variables
        // both constructors invoke their parent constructors with the super keyword
        // both constructors also are examples of polymorphism because they both overrode the original user constructor
        Customer c1 = new Customer("Billy", 100);
        Seller s1 = new Seller("Bob");

        // the set name method abstracts away the logic behind changing the user's name
        // similarly, the get name method abstracts away the logic behind getting the user's name
        // also implements inheritance because the setName method comes from the User class
        c1.setName("John");
        c1.printName();
        System.out.println(s1.getName());

        // creating a new shop
        Shop shop1 = new Shop();

        // dagger implements sellable interface, which can extend other sellable items and have base functionality to be implemented in children
        // the sellNotification method is abstract and must be implemented by any class that implements the sellable interface
        Dagger exampleDagger = new Dagger(101, "Fancy Unaffordable Knife");

        // set item for sale
        shop1.setOneItemForSale(exampleDagger);
        System.out.println(shop1.getNumberOfItemsForSale() + " item(s) on sale.");

        // there is a custom runtime error thrown and handled for if the customer tries to buy an item they cannot afford.
        try {
            System.out.println("Attempting to buy " + exampleDagger.getName() + ".");
            c1.buyDagger(exampleDagger);
        } catch (BuyException e) {
            System.out.println("Sorry, you can't afford that right now.");
        }
    }
}
