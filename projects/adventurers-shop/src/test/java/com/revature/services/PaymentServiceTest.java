package com.revature.services;

import com.revature.models.Payment;
import com.revature.repositories.PaymentPostgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private static PaymentService ps;
    private static PaymentPostgres pp;

    @BeforeAll
    public static void setup(){
        ps = new PaymentService();
        pp = new PaymentPostgres();
        pp.delete(1);
        Payment payment1 = new Payment(1, 1, 1, 2);
        Payment payment2 = new Payment(2, 1, 1, 3);
        pp.add(payment1);
        pp.add(payment2);
    }

    @Test
    void getAll() {
        int actual = ps.getAll().size();
        assertTrue(actual >= 0);
    }

    @Test
    void makePayment() {
        int actual = pp.getByName("apple").size();
        ps.makePayment("apple");
        int expected = pp.getByName("apple").size();
        assertNotEquals(actual, expected);
    }

    @Test
    void add(){
        Payment payment1 = new Payment(1, 1, 1, 2);
        int expected = pp.getAll().size();
        pp.add(payment1);
        int actual = pp.getAll().size();
        assertNotEquals(expected, actual);
    }

    @Test
    void getByName(){
        int expected = pp.getByName("apple").size();
        assertTrue(expected >= 0);
    }
}