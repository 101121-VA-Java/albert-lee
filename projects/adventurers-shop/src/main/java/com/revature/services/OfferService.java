package com.revature.services;

import com.revature.exceptions.BidTooLowException;
import com.revature.models.Offer;
import com.revature.repositories.OfferPostgres;
import java.util.List;

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
