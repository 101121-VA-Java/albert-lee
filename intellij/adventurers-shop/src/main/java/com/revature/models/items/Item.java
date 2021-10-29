package com.revature.models.items;

import java.sql.Array;
import java.util.ArrayList;

public class Item {
    public static int numberOfItems;
    private int id;
    private int price;
    private String name;
    private int pendingOffer;

    public Item(){
        this.id = Item.numberOfItems;
        Item.numberOfItems++;
    }

    public Item(String name, int price){
        this.name = name;
        this.price = price;
        this.id = Item.numberOfItems;
        Item.numberOfItems++;

    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPendingOffer() {
        return pendingOffer;
    }

    public void setPendingOffer(int pendingOffer) {
        this.pendingOffer = pendingOffer;
    }
}
