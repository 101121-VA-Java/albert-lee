package com.revature.models.users;

import com.revature.exceptions.BuyException;
import com.revature.models.items.weapons.Dagger;

public class Customer extends User {
    private int cash;
    public Customer(String username, int cash){
        super();
        super.setName(username);
        this.cash = cash;
    }

    public void buyDagger(Dagger item) throws RuntimeException{
        if(this.cash <= item.getPrice()) throw new BuyException();
        this.cash -= item.getPrice();
    }
}