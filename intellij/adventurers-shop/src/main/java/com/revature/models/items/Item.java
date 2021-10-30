package com.revature.models.items;

import com.revature.models.Offer;

public class Item {
    public static int numberOfItems;
    private int id;
    private int price;
    private String name;
    private Offer highestOffer;

    public Item(String itemName, String price){
        this.name = itemName;
        setPrice(Integer.parseInt(price));
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

    public Offer getHighestOffer() {
        return highestOffer;
    }

    public void setHighestOffer(Offer highestOffer) {
        this.highestOffer= highestOffer;
    }
}
