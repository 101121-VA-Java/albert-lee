package com.revature.models.items.weapons;

import com.revature.models.items.Item;
import com.revature.models.items.Sellable;

public class Dagger extends Item implements Sellable {
    private final String name;

    public Dagger(int price, String name){
        this.setPrice(price);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void sellNotification(){
        System.out.println("This item has been sold");
    }
}
