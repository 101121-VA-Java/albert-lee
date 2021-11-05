package com.revature.services;

import com.revature.models.Item;
import com.revature.repositories.ItemPostgres;

import java.util.List;

public class ItemService {
    public final ItemPostgres ip;

    public ItemService() {
        ip = new ItemPostgres();
    }

    public List<Item> getAll(){
        return ip.getAll();
    }

    public int getIdByName(String name) {
        return ip.getIdByName(name);
    }

    public List<Item> getItemsBelongingToUserId(int id) {
        return ip.getByOwnerId(id);
    }

    public void addUnownedItemForSale(Item item){
        ip.add(item);
    }

    public void removeItemByName(String name){
        for (Item item : ip.getAll()) {
            if(name.equals(item.getName())){
                ip.delete(item.getId());
            }
        }
    }
}
