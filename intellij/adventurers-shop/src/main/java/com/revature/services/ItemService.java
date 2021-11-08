package com.revature.services;

import com.revature.models.Item;
import com.revature.repositories.ItemPostgres;
import com.revature.utils.LogUtil;

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
        try{
            int beforeSize = ip.getAll().size();
            ip.add(item);
            int afterSize = ip.getAll().size();
            if(beforeSize == afterSize) throw new Exception();
        } catch(Exception e) {
            LogUtil.descriptiveError("Item failed to be added. Please try again.");
        }
        System.out.println(item.getName() + " listed for $" + item.getPrice());
    }

    public void removeItemByName(String name){
        for (Item item : ip.getAll()) {
            if(name.equals(item.getName())){
                try{
                    int beforeSize = ip.getAll().size();
                    ip.delete(item.getId());
                    int afterSize = ip.getAll().size();
                    if(beforeSize == afterSize) throw new Exception();
                } catch(Exception e){
                    LogUtil.descriptiveError("Failed to remove item. Please try again.");
                }
                System.out.println(item.getName() + " was removed from the shop.");
            }
        }
    }
}
