package com.revature.services;

import com.revature.models.Offer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceTest {
    private static OfferService os;

    @BeforeEach
    public void setup() {
        os = new OfferService();
        os.removeOffers(1);
        os.addOffer(new Offer(3, 1, 1));
    }

    @AfterAll
    public static void cleanUp() {
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
        os.removeOffers(1);
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