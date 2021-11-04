package com.revature.services;

import com.revature.models.Item;
import com.revature.models.users.User;
import com.revature.repositories.ItemArray;
import com.revature.repositories.ItemPostgres;

import java.util.List;

public class ItemService {
    private final ItemPostgres ip;

    public ItemService() {
        ip = new ItemPostgres();
    }

    public List<Item> getAll(){
        return ip.getAll();
    }

    public List<Item> getItemsBelongingToUserId(int id) {
        return ip.getByOwnerId(id);
    }

    public void addItemToInventory(Item item, int id){
//        for (User user : ua.getUsers()) {
////            if(user.getId() == id) user.addItemToInventory(item);
//        }
    }
}
