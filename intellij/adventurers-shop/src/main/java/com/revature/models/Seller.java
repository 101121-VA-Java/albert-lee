package com.revature.models;

public class Seller extends User {
    private Sellable[] itemsForSale;
    private int timeLeft;

    public Seller(String name) {
        super();
        this.name = name;
        this.timeLeft = 600;
    }

    public Sellable[] getItemsForSale(){
        return itemsForSale;
    }
}
