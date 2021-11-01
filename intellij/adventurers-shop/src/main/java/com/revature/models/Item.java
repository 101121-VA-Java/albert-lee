package com.revature.models;

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

    public int getHighestOfferPrice() {
        if(highestOffer == null) return 0;
        else return highestOffer.getPrice();
    }

    public boolean hasOffer() { return highestOffer == null; }

    public void setHighestOffer(Offer highestOffer) {
        this.highestOffer= highestOffer;
    }
}
