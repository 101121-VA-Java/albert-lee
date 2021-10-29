package com.revature.services;

import com.revature.models.items.Item;
import com.revature.repositories.ItemArray;

public class ItemService {
    private final ItemArray ia;

    public ItemService(){
        ia = new ItemArray();
    }

    public void add(Item i) {
        ia.add(i);
    }

    public int remove(String itemName) {
        for (Item i : ia.getItems()) {
            if (i.getName().equals(itemName)) {
                // TODO : not implemented yet
                ia.delete(i.getId());
                System.out.println("Removed " + i.getName());
            }
        }
        return 0;
    }
}
