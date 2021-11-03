package com.revature.models.users;
public class User {
    private int id=0;
    private final String username;
    private final String password;
    private String role;
    private int cashOnHand;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String role, int cashOnHand){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cashOnHand = cashOnHand;
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

    public String getRole(){
        return this.role;
    }

    public int getCashOnHand() { return cashOnHand; }

    public void setCashOnHand(int newAmount) { this.cashOnHand = newAmount; }

    public void setRole(String role) {
        this.role = role;
    }

//    public void addItemToInventory(Item item){
//        this.playerInventory.add(item);
//    }

//    public ItemArray getPlayerInventory(){
//        return playerInventory;
//    }
}
