package com.revature.models.items;

public class RiskyItem extends Item{
    public RiskyItem(String name, String price, String type) {
        super(name, price, type);
    }

    public RiskyItem(int id, String name, int price, int ownerId, String type) {
        super(id, name, price, ownerId, type);
    }

    @Override
    public String getName(){
        return "risky " + super.getName();
    }
}
