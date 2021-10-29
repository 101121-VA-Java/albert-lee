package com.revature.repositories;

import java.util.ArrayList;

public interface GenericDao<T> {
    T add(T t);
    ArrayList<T> getAll(int id);
    T getById(int id);
    T update(T t);
    int delete(int id);
}
