package com.revature.models;

public class Payment {
    private int id=0;
    private int payee_id=0;
    private int item_id=0;
    private int amount=0;

    public Payment(){
    }

    public Payment(int payee_id, int item_id, int amount){
        this.payee_id = payee_id;
        this.item_id = item_id;
        this.amount = amount;
    }

    public Payment(int id, int payee_id, int item_id, int amount){
        this.id = id;
        this.payee_id = payee_id;
        this.item_id = item_id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getPayeeId() {
        return payee_id;
    }

    public int getItemId() {
        return item_id;
    }

    public int getAmount() {
        return amount;
    }
}
