package com.revature.services;

import com.revature.utils.LogUtil;
import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserPostgres;
import com.revature.utils.PasswordUtil;

public class UserService {
    private final UserPostgres up;

    public UserService(){
        up = new UserPostgres();
    }

    public int register(User user, String roleChoice) throws InvalidRole {
        if(!roleChoice.equals("1") && !roleChoice.equals("2")) throw new InvalidRole();
        else if(roleChoice.equals("1")) user.setRole("CUSTOMER");
        else user.setRole("EMPLOYEE");
        int newUserId = -1;
        try{
            newUserId = up.add(user);
            if(newUserId == -1) throw new Exception();
        } catch(Exception e){
            LogUtil.descriptiveError("Registration failed. User already exists or an input was bad.");
        }
        return newUserId;
    }

    public User login(String username, String password) throws UserNotFoundException{
        try {
            for (User user : up.getAll()) {
                if(user.getUsername().equals(username) && PasswordUtil.isCorrectPassword(password, user.getPassword())){
                    return user;
                }
            }
            throw new Exception();
        } catch (Exception e) {
            LogUtil.descriptiveError("Bad username/password");
        }
        return null;
    }
}
