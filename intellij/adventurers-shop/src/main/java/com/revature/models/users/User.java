package com.revature.models.users;

import com.revature.models.items.Item;
import com.revature.repositories.ItemArray;

public class User {
    protected static int numberOfUsers=1;
    private int id;
    private String username;
    private String password;
    private Role role;
    private ItemArray playerInventory;

    public User(){
        super();
    }

    public User(String username, String password){
        this.id = numberOfUsers;
        User.numberOfUsers += 1;
        this.username = username;
        this.password = password;
        this.playerInventory = new ItemArray();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void addItemToInventory(Item item){
        this.playerInventory.add(item);
    }

    public ItemArray getPlayerInventory(){
        return playerInventory;
    };
}
