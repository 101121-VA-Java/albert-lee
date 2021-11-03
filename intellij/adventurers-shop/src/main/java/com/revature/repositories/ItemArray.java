package com.revature.repositories;

import com.revature.models.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemArray implements GenericDao<Item> {
    private final ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public int add(Item item) {
        items.add(item);
        return item.getId();
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getById(int id) {
        for (Item item : items) {
            if(id == item.getId()){
                return item;
            }
        }
        return null;
    }

    @Override
    public int update(Item updatedItem) {
        for (Item item1 : items) {
            if(item1.getId() == updatedItem.getId()){
                items.set(item1.getId(), updatedItem);
                return items.get(item1.getId()).getId();
            }
        }
        return 0;
    }

    @Override
    public void delete(int index) {
        items.remove(index);
    }
}
