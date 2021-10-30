package com.revature.models;

public class Offer {
    private int price;
    private int ownerId;

    public Offer(int price, int belongsTo) {
        this.price = price;
        this.ownerId = belongsTo;
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
