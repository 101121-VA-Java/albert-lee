package com.revature.repositories;

import com.revature.models.Item;
import com.revature.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferPostgres implements GenericDao<Offer>{
    @Override
    public int add(Offer offer) {
        return 0;
    }

    @Override
    public List<Offer> getAll() {
        String sql = "select * from users;";
        List<Offer> users = new ArrayList<>();
        return users;
    }

    @Override
    public Offer getById(int id) {
        return null;
    }

    @Override
    public int update(Offer user) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }
}
