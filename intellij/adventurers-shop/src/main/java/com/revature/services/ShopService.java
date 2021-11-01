package com.revature.services;

import com.revature.models.Shop;
import com.revature.models.Item;

public class ShopService {
    private final Shop sh;

    public ShopService(){
        sh = new Shop();
    }

    public void add(Item i) {
        sh.add(i);
    }

    public void remove(String itemName) {
        for(int i = 0; i < sh.getItemsForSale().getItems().size(); i++){
            if(sh.getItemsForSale().getItems().get(i).getName().equals(itemName)){
                sh.remove(i);
            }
        }
    }

    public void printAllItemsForSale(){
        System.out.println("The items for sale are: ");
        for (Item item : sh.getItemsForSale().getItems()) {
            System.out.println("$" + item.getPrice() + " " + item.getName());
        }
    }

    public Item getItemByName(String name){
        for (Item item : sh.getItemsForSale().getItems()) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return sh.getItemsForSale().getItems().isEmpty();
    }
}
