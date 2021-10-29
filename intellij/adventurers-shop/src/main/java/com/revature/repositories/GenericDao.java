package com.revature.repositories;

import com.revature.models.items.Item;

import java.util.ArrayList;

public interface GenericDao<T> {
    T add(T t);
    ArrayList<Item> getAll();
    T getById(int id);
    T update(T t);
    int delete(int id);
}
