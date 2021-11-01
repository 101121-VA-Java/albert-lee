package com.revature.models;
import com.revature.repositories.ItemArray;

public class Shop {
    private ItemArray itemsForSale;

    public Shop() {
        this.itemsForSale = new ItemArray();
    }

    public void add(Item itemToSell){
        itemsForSale.add(itemToSell);
        System.out.println("Now selling new item");
    }

    public void remove(int index){
        itemsForSale.getItems().remove(index);
    }

    public ItemArray getItemsForSale() {
        return this.itemsForSale;
    }
}
