package com.revature.repositories;

import com.revature.models.Item;
import com.revature.models.users.User;

import java.util.ArrayList;

public class UserArray implements GenericDao<User> {
    private final ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public User add(User user) {
        users.add(user);
        return user;
    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        for (User user : users) {
            if(id == user.getId()){
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User updatedUser) {
        for (User user1 : users) {
            if(user1.getId() == updatedUser.getId()){
                users.set(user1.getId(), updatedUser);
                return users.get(user1.getId());
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO: implement once primary keys from postgresql are available instead of hard coded id numbers
    }
}
