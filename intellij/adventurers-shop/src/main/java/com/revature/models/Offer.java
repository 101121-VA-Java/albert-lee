package com.revature.models;

public class Offer {
    private int offer_id;
    private int bidPrice;
    private int ownerId;
    private int itemId;

    public Offer(){
    }

    public Offer(int bidPrice, int ownerId, int itemId) {
        this.bidPrice = bidPrice;
        this.ownerId = ownerId;
        this.itemId = itemId;
    }

    public int getPrice() {
        return bidPrice;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setPrice(int bidPrice){
        this.bidPrice = bidPrice;
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public void setItemId(int itemId) {this.itemId = itemId; }
}
