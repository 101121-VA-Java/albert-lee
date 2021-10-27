package com.revature.models;

import com.revature.models.items.Sellable;
import com.revature.models.items.weapons.Dagger;

import java.util.ArrayList;


public class Shop {
    private ArrayList itemsForSale = new ArrayList();

    public int getNumberOfItemsForSale(){
        return itemsForSale.size();
    }

    public void setOneItemForSale(Dagger daggerToSell){
        itemsForSale.add(daggerToSell);
        System.out.println("Now selling " + daggerToSell.getName());
    }
}
