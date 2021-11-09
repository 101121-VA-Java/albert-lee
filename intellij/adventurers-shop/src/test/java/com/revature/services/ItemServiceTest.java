package com.revature.services;

import com.revature.models.Item;
import com.revature.repositories.ItemPostgres;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    private static ItemService is;
    private static ItemPostgres ip;

    @BeforeAll
    public static void setup(){
        is = new ItemService();
        ip = new ItemPostgres();
    }

    @AfterEach
    public void cleanup(){
        is.removeItemByName("test");
    }

    @Test
    void getAll() {
        List<Item> actual = ip.getAll();
        assertAll(() -> assertEquals(5, actual.get(0).getPrice()),
                () -> assertEquals("apple", actual.get(0).getName()),
                () -> assertEquals(1, actual.size())
                );
    }

    @Test
    void getIdByName() {
        int expected = 1;
        int actual = is.getIdByName("apple");
        assertEquals(expected, actual);
    }

    @Test
    void getItemsBelongingToUserId() {
        int expected = ip.getByOwnerId(1).size();
        int actual = is.getItemsBelongingToUserId(1).size();
        assertEquals(expected, actual);
    }

    @Test
    void addUnownedItemForSale() {
        int expected = 2;
        is.addUnownedItemForSale(new Item("test", "10"));
        int actual = is.getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void removeItemByName() {
        int expected = 1;
        is.addUnownedItemForSale(new Item("test", "10"));
        is.removeItemByName("test");
        int actual = is.getAll().size();
        assertEquals(expected, actual);
    }
}