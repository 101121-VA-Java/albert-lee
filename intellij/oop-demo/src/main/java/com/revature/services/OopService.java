package com.revature.services;

import java.time.LocalDate;
import com.revature.models.*;

public class OopService {
    private int i;
    protected String s;
    String something; // default access modifier

    public void doStuff(){
        BoringTask bt = new BoringTask("inheritance", LocalDate.now());
        bt.procrastinate();
        System.out.println(bt.getDueDate());
    }
}