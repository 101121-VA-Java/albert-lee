package com.revature.services;

import com.revature.utils.LogUtil;
import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserPostgres;

public class UserService {
    private final UserPostgres up;

    public UserService(){
        up = new UserPostgres();
    }

    public int register(User user, String roleChoice) throws InvalidRole {
        if(!roleChoice.equals("1") && !roleChoice.equals("2")) throw new InvalidRole();
        else if(roleChoice.equals("1")) user.setRole("CUSTOMER");
        else user.setRole("EMPLOYEE");
        return up.add(user);
    }

    public User login(String username, String password) throws UserNotFoundException{
        try {
            for (User user : up.getAll()) {
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    return user;
                }
            }
            throw new UserNotFoundException();
        } catch (UserNotFoundException e) {
            LogUtil.descriptiveError("User Not Found");
        }
        return null;
    }
}
