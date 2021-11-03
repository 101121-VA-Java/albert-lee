package com.revature.repositories;

import com.revature.models.users.User;

import java.util.List;

public interface GenericDao<T> {
    int add(T t);
    List<T> getAll();
    T getById(int id);
    int update(T t);
    void delete(int id);
}
