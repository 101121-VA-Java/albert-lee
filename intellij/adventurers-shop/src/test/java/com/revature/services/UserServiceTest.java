package com.revature.services;

import com.revature.exceptions.InvalidRole;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserPostgres;
import org.junit.jupiter.api.*;

class UserServiceTest {
    private static UserService us;
    private static UserPostgres up;


    @BeforeAll
    public static void setup(){
        us = new UserService();
        up = new UserPostgres();
        up.delete(2);
    }

    @Test
    void register() {
        User newUser = new User("username", "password");
        int actual = 0;
        try {
            actual = us.register(newUser, "1");
        } catch (InvalidRole e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(actual > 0);
    }

    @Test
    void login() {
        int expectedUserId = 1;
        int actualUserId = 0;
        try {
            actualUserId = us.login("1", "1").getId();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(actualUserId, expectedUserId);
    }

}