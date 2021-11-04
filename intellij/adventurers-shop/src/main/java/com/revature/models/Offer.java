package com.revature.models;

public class Offer {
    private int offer_id;
    private int price;
    private int ownerId;
    private int itemId;

    public Offer(int price, int belongsTo, int itemId) {
        this.price = price;
        this.ownerId = belongsTo;
        this.itemId = itemId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
}
