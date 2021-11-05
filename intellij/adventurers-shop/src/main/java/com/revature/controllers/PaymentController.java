package com.revature.controllers;

import com.revature.models.Payment;
import com.revature.services.PaymentService;

public class PaymentController {
    public final PaymentService ps;

    public PaymentController() {
        ps = new PaymentService();
    }

    public void printAllPaymentsOutstanding() {
        if(ps.getAll().isEmpty()) System.out.println("There are no outstanding payments.");
        else {
            System.out.println("There are " + ps.getAll().size() + " payments outstanding.");
            for (Payment payment : ps.getAll()) {
                System.out.println("Customer " + payment.getPayeeId() + " owes " + payment.getAmount());
            }
        }
    }
}
