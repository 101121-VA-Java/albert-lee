package com.revature.models;

public class Payment {
    private final int id;
    private final int payee_id;
    private final int item_id;
    private final int amount;

    public Payment(int id, int payee_id, int item_id, int amount){
        this.id = id;
        this.payee_id = payee_id;
        this.item_id = item_id;
        this.amount = amount;
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
