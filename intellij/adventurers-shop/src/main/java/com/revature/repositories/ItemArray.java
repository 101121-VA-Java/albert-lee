package com.revature.repositories;

import com.revature.models.Item;

import java.util.ArrayList;

public class ItemArray implements GenericDao<Item> {
    private final ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public Item add(Item item) {
        items.add(item);
        return item;
    }

    @Override
    public ArrayList<Item> getAll() {
        return items;
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
    public Item update(Item updatedItem) {
        for (Item item1 : items) {
            if(item1.getId() == updatedItem.getId()){
                items.set(item1.getId(), updatedItem);
                return items.get(item1.getId());
            }
        }
        return null;
    }

    @Override
    public void delete(int index) {
        items.remove(index);
    }
}
