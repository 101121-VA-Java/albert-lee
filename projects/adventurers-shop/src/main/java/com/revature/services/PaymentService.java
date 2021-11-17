package com.revature.services;

import com.revature.models.Payment;
import com.revature.repositories.PaymentPostgres;

import java.util.List;

public class PaymentService {
    public final PaymentPostgres pp;

    public PaymentService() {
        pp = new PaymentPostgres();
    }

    public List<Payment> getAll(){
        return pp.getAll();
    }

    public void makePayment(String itemName) {
        pp.deleteById(pp.getFirstPayment(itemName).getId());
    }

    public int add(Payment payment) {
        return pp.add(payment);
    }

    public List<Payment> getByName(String itemName) {
        List<Payment> payments = pp.getByName(itemName);
        return payments;
    }
}
