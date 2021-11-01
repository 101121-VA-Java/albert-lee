package com.revature.repositories;

import com.revature.models.Item;

import java.util.ArrayList;

public interface GenericDao<T> {
    T add(T t);
    ArrayList<Item> getAll();
    T getById(int id);
    T update(T t);
    void delete(int id);
}
