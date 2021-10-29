package com.revature.models.users;

public class User {
    protected static int numberOfUsers=1;
    private int id;
    private String username;
    private String password;
    private Role role;

    public User(){
        super();
    }

    public User(String username, String password){
        this.id = numberOfUsers;
        User.numberOfUsers += 1;
        this.username = username;
        this.password = password;
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
}
