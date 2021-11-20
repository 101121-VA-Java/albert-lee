package com.revature.models;

public class Reimbursement {

    private int amount;
    private String description;
    private int authorId;
    private int typeId;

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getTypeId() {
        return typeId;
    }
}
