package com.revature.controllers;

import com.revature.models.Payment;
import com.revature.services.PaymentService;

import java.util.List;

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

    public void makePayment(UserController uc) {
        System.out.println("What is the name of the item you would like to make a payment towards?");
        String itemName = uc.sc.nextLine();
        int payeeId = uc.getCurrentUser().getId();
        List<Payment> payments = ps.getByName(itemName);
        if(payments.isEmpty()) {
            System.out.println("Item is either paid off or the given item was not found.");
        } else if(payments.size() > 0){
            printPayments(payments, itemName, payeeId);
            continueWithPaymentChoice(uc, itemName);
        }
    }

    public void printPayments(List<Payment> payments, String itemName, int payeeId){
        System.out.println("Payment(s) for " + itemName + " outstanding: ");
        for (Payment payment : payments) {
            if(payment.getPayeeId() == payeeId) {
                System.out.println("Payment of $" + payment.getAmount() + " remaining.");
            }
        }
    }

    public void continueWithPaymentChoice(UserController uc, String itemName) {
        System.out.println("Would you like to continue making a payment? Y / N");
        String choice = uc.sc.nextLine();
        if(choice.equals("Y") | choice.equals("y")) {
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
