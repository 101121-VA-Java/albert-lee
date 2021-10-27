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

    public User(String username, String password, Role role){
        this.id = numberOfUsers;
        this.username = username;
        this.password = password;
        this.role = role;
        User.numberOfUsers += 1;
    }

    public void setName(String newName){
        this.username = newName;
    }

    public String getName(){
        return this.username;
    }

    public void printName(){
        System.out.println(this.username);
    }

}
