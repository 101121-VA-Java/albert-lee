package com.revature.models;

import java.time.LocalDate;

public class BoringTask extends Task {
    //overloaded constructor to account for initializing with a name and due date
    public BoringTask(String name, LocalDate dueDate) {
        super(name, dueDate);
    }

    public void procrastinate() {
        this.setDueDate(this.getDueDate().plusDays(1));
    }

    @Override
    public String toString() {
        return "overridden instance tostring method from boring task class";
    }

}
