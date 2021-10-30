package com.revature.models.users;

import com.revature.models.Offer;
import com.revature.models.items.Item;
import com.revature.repositories.ItemArray;

public class User {
    protected static int numberOfUsers=1;
    private int id;
    private String username;
    private String password;
    private Role role;
    private ItemArray playerInventory;
    private int cashOnHand;

    public User(){
        super();
    }

    public User(String username, String password){
        this.id = numberOfUsers;
        User.numberOfUsers += 1;
        this.username = username;
        this.password = password;
        this.playerInventory = new ItemArray();
        this.cashOnHand = 100;
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
    }

    public void makeOffer(Item item, int newOfferPrice, int bidderId){
        if(item.getHighestOffer() == null){
            if(cashOnHand > newOfferPrice) {
                item.setHighestOffer(new Offer(newOfferPrice, bidderId));
                System.out.println("Congratulations. You now have the highest bid of $" + item.getHighestOffer().getPrice() + " for " + item.getName());
            } else if(cashOnHand < newOfferPrice){
                System.out.println("Sorry, you can't afford that offer at the moment.");
            }
        }
        else if(item.getHighestOffer().getPrice() <= cashOnHand && newOfferPrice > item.getHighestOffer().getPrice()) {
            item.getHighestOffer().setPrice(newOfferPrice);
            cashOnHand -= item.getHighestOffer().getPrice();
            item.getHighestOffer().setOwnerId(this.getId());
            System.out.println("Congratulations. You now have the highest bid of $" + item.getHighestOffer().getPrice() + " for " + item.getName());
        }
    }
}
