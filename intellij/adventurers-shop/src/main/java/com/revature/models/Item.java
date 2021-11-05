package com.revature.models;

public class Item {
    private int id;
    private int price;
    private int ownerId;
    private String name;

    public Item(){
    }

    public Item(String itemName, String price) {
        this.name = itemName;
        this.price = Integer.parseInt(price);
    }

    public Item(int id, String name, int price, int ownerId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getOwnerId() { return ownerId; }

    public String getName() {
        return name;
    }

    public void setOwnerId(int id) {
        this.ownerId = id;
    }
}


