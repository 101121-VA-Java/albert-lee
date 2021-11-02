package com.revature.services;

import com.revature.models.Item;
import com.revature.models.users.Role;
import com.revature.models.users.User;
import com.revature.repositories.UserArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest {
    private UserArray ua;
    @BeforeEach

    void setUp() {
        ua = new UserArray();
    }

    @Test
    void register() {
        User expected = new User("Albert", "password");
        expected.setRole(Role.CUSTOMER);
        ua.add(expected);
        User actual = ua.getUsers().get(0);
        assertEquals(actual, expected);
    }

    @Test
    void login() {
        User expected = new User("Albert", "password");
        expected.setRole(Role.CUSTOMER);
        ua.add(expected);
        User actual = null;
        for (User user : ua.getUsers()) {
            if(user.getUsername().equals(expected.getUsername())){
                actual = user;
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    void getPlayerInventory() {
        User newUser = new User("Albert", "password");
        newUser.setRole(Role.CUSTOMER);
        ua.add(newUser);
        Item itemToAdd = new Item("pie", "5");
        ua.getUsers().get(0).addItemToInventory(itemToAdd);
        String expected = "pie";
        String actual = ua.getUsers().get(0).getPlayerInventory().getItems().get(0).getName();
        assertEquals(actual, expected);
    }

    @Test
    void addItemToInventory() {
        User newUser = new User("Albert", "password");
        newUser.setRole(Role.CUSTOMER);
        ua.add(newUser);
        Item itemToAdd = new Item("pie", "5");
        ua.getUsers().get(0).addItemToInventory(itemToAdd);
        String expected = "pie";
        String actual = ua.getUsers().get(0).getPlayerInventory().getItems().get(0).getName();
        assertEquals(actual, expected);
    }
}