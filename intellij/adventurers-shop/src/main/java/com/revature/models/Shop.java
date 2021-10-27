package com.revature.models;

import com.revature.models.items.weapons.Dagger;

public class Shop {
    private int numberOfItemsForSale;

    public int getNumberOfItemsForSale(){
        return numberOfItemsForSale;
    }

    public void setOneItemForSale(Dagger daggerToSell){
        System.out.println("Now selling " + daggerToSell.getName());
        this.numberOfItemsForSale += 1;
    }
}
