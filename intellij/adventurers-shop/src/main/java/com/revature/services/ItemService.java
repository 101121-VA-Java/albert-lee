package com.revature.services;

import com.revature.models.items.Item;
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

    public int addUnownedItemForSale(Item item){
        int status = -1;
        try{
            int beforeSize = ip.getAll().size();
            status = ip.add(item);
            int afterSize = ip.getAll().size();
            if(beforeSize == afterSize) throw new Exception();
        } catch(Exception e) {
            LogUtil.descriptiveError("Item failed to be added. Check item type and try again.");
        }
        return status;
    }

    public int removeItemByName(String name){
        int status = -1;
        try{
            int beforeSize = ip.getAll().size();
            for (Item item : ip.getAll()) {
                if(name.equals(item.getName()) | ("risky " + name).equals(item.getName()) ) {
                    ip.delete(item.getId());
                }
            }
            int afterSize = ip.getAll().size();
            if (beforeSize == afterSize) throw new Exception();
            status = 1;
        } catch(Exception e) {
            LogUtil.descriptiveError("Item was not found. Please try again.");
        }
        return status;
    }

    public List<Item> printItemsForSale() {
        List<Item> itemsForSale = ip.getAll();
        if (itemsForSale.isEmpty()) {
            System.out.println("Everything is sold out; please check later.");
        } else {
            System.out.println(itemsForSale.size() + " item(s) for sale.");
            for (Item item : itemsForSale) {
                System.out.println("$" + item.getPrice() + " " + item.getName());
            }
        }
        return itemsForSale;
    }
}
