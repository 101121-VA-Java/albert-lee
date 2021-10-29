package com.revature.models;
import com.revature.models.items.Item;
import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> itemsForSale = new ArrayList<Item>();
    public int getNumberOfItemsForSale(){
        return itemsForSale.size();
    }
    public void addItemForSale(Item itemToSell){
        itemsForSale.add(itemToSell);
        System.out.println("Now selling new item");
    }
    public ArrayList<Item> getItemsForSale() {
        return this.itemsForSale;
    }
}
