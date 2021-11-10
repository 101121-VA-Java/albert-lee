package com.revature.models.items;

public class Item {
    private int id;
    private int price;
    private int ownerId;
    private String name;
    private String type;

    public Item(){
    }

    public Item(String itemName, String price, String type) {
        this.name = itemName;
        this.price = Integer.parseInt(price);
        this.type = type;
    }

    public Item(int id, String name, int price, int ownerId, String type){
        this.id = id;
        this.name = name;
        this.price = price;
        this.ownerId = ownerId;
        this.type = type;
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

    public String getType() { return type; }
}


