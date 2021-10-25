package com.revature.models;

public class User {
    protected String name;
    protected static int numberOfUsers;

    public User(){
        this.name = "default";
        User.numberOfUsers += 1;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void printName(){
        System.out.println(this.name);
    }
}
