package com.revature.models;

public class Seller extends User {
    private Sellable[] itemsForSale;
    private int timeLeft;

    public Seller(String username) {
        super();
        super.setName(username);
        this.timeLeft = 600;
    }

    public Sellable[] getItemsForSale(){
        return itemsForSale;
    }
}
