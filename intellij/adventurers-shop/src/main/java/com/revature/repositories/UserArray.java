package com.revature.repositories;

import com.revature.models.Item;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserArray implements GenericDao<User> {
    private final ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public int add(User user) {
        users.add(user);
        return 1;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        for (User user : users) {
            if(id == user.getId()){
//                return user;
            }
        }
        return null;
    }

    @Override
    public int update(User updatedUser) {
        for (User user1 : users) {
            if(user1.getId() == updatedUser.getId()){
                users.set(user1.getId(), updatedUser);
//                return users.get(user1.getId());
                return 0;
            }
        }
        return 0;
    }

    @Override
    public void delete(int id) {
        // TODO: implement once primary keys from postgresql are available instead of hard coded id numbers
    }
}
