package com.revature.models;
public class User {
    private int id;
    private final String username;
    private final String password;
    private String role;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
