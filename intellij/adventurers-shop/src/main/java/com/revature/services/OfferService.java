package com.revature.services;

import com.revature.controllers.ItemController;
import com.revature.controllers.UserController;
import com.revature.exceptions.BidTooLowException;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.repositories.OfferPostgres;
import java.util.List;
import java.util.Locale;

public class OfferService {
    public final OfferPostgres op;

    public OfferService() {
        op = new OfferPostgres();
    }

    public List<Offer> getAll(){
        return op.getAll();
    }

    public void addOffer(Offer offer) throws BidTooLowException{
        op.add(offer);
    }

    public void removeOffers(int itemId){
        op.deleteOffers(itemId);
    }
}
