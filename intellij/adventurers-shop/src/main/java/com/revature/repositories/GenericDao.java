package com.revature.repositories;

import com.revature.models.users.User;

import java.util.List;

public interface GenericDao<T> {
    int add(T t);
    List<User> getAll();
    T getById(int id);
    T update(T t);
    void delete(int id);
}
