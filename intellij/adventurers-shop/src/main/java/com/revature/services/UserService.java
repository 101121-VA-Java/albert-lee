package com.revature.services;

import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.users.Role;
import com.revature.models.users.User;
import com.revature.repositories.ItemArray;
import com.revature.repositories.UserArray;

public class UserService {
    private final UserArray ua;

    public UserService(){
        ua = new UserArray();
    }

    public void register(User user, String roleChoice) throws InvalidRole {
        if(!roleChoice.equals("1") && !roleChoice.equals("2")) throw new InvalidRole();
        else if(roleChoice.equals("1")) user.setRole(Role.CUSTOMER);
        else if(roleChoice.equals("2")) user.setRole(Role.EMPLOYEE);
        ua.add(user);
    }

    public User login(String username, String password) throws UserNotFoundException{
        for (User user : ua.getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new UserNotFoundException();
    }


    public ItemArray getPlayerInventory(int id) {
        for (User user : ua.getUsers()) {
            if(user.getId() == id){
                return user.getPlayerInventory();
            }
        } return null;
    }
}
