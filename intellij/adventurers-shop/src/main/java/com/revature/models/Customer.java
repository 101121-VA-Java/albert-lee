package com.revature.models;

import com.revature.exceptions.BuyException;

public class Customer extends User {
    private int cash;
    public Customer(String name, int cash){
        super();
        this.name = name;
        this.cash = cash;
    }

    public void buyDagger(Dagger item) throws RuntimeException{
        if(this.cash <= item.getPrice()) throw new BuyException();
        this.cash -= item.getPrice();
    }
}
