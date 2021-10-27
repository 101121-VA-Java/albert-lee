package com.revature.models.users;

import com.revature.models.items.Sellable;

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
