package com.revature.models;

public class Dagger implements Sellable {
    private final int price;
    private final String name;

    public Dagger(int price, String name){
        this.price = price;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public void sellNotification(){
        System.out.println("This item has been sold");
    }
}
