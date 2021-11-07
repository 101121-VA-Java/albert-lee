package com.revature.services;

import com.revature.models.Offer;
import com.revature.repositories.OfferPostgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceTest {
    private static OfferService os;
    private static OfferPostgres op;

    @BeforeAll
    public static void setup() {
        os = new OfferService();
        op = new OfferPostgres();
        os.removeOffers(1);
        os.addOffer(new Offer(3, 1, 1));
    }

    @Test
    void getAll() {
        List<Offer> actual = os.getAll();
        assertAll(() -> assertEquals(1, actual.get(0).getOwnerId()),
                () -> assertEquals(1, actual.get(0).getItemId()),
                () -> assertEquals(3, actual.get(0).getPrice())
        );
    }

    @Test
    void addOffer() {
        Offer newOffer = new Offer(4, 1, 1);
        os.addOffer(newOffer);
        int expected = 1;
        int actual = os.getAll().size();
        assertNotEquals(expected, actual);
    }

    @Test
    void removeOffers() {
        int before = os.getAll().size();
        os.removeOffers(1);
        int after = os.getAll().size();
        assertNotEquals(before, after);
    }
}