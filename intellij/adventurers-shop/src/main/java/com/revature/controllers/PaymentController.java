package com.revature.controllers;

import com.revature.models.Payment;
import com.revature.services.PaymentService;

import java.util.Locale;
import java.util.Scanner;

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

    public void makePayment(Scanner sc, int payeeId) {
        System.out.println("What is the name of the item you would like to make a payment towards?");
        String itemName = sc.nextLine();
        int count = 0;
        for (Payment payment : ps.pp.getPaymentsForItemName(itemName)) {
            if(payment.getPayeeId() == payeeId) {
                count++;
            };
        }
        if(count > 0){
            System.out.println("Payment(s) for " + itemName + " outstanding: ");
            for (Payment payment : ps.pp.getPaymentsForItemName(itemName)) {
                if(payment.getPayeeId() == payeeId) {
                    System.out.println("Payment of $" + payment.getAmount() + " remaining.");
                };
            }
        } else if (count == 0) {
            System.out.println("No payments for this item remaining.");
            return;
        }
        System.out.println("Would you like to continue making a payment? Y / N");
        String choice = sc.nextLine();
        if(choice.toUpperCase(Locale.ROOT).equals("Y")) {
            ps.makePayment(itemName);
            System.out.println("Payment towards " + itemName + " completed.");
        } else {
            System.out.println("Cancelling. Returning to main menu.");
        }
    }

    public int add(Payment payment){
        return ps.add(payment);
    }
}
