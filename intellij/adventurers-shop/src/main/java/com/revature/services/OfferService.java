package com.revature.services;

import com.revature.controllers.ItemController;
import com.revature.controllers.UserController;
import com.revature.exceptions.BidTooLowException;
import com.revature.models.Offer;
import com.revature.repositories.OfferPostgres;
import java.util.List;

public class OfferService {
    private final OfferPostgres op;

    public OfferService() {
        op = new OfferPostgres();
    }

    public List<Offer> getAll(){
        return op.getAll();
    }

    public void addOffer(String name, ItemController ic, UserController uc) throws BidTooLowException{
        int currentBidPrice = op.getOfferPriceByItemName(name);
        Offer newOffer = new Offer();
        if(currentBidPrice <= 0){
            System.out.println("There are no bids yet. How much would you like to offer?");
            currentBidPrice = Integer.parseInt(uc.sc.nextLine());
            newOffer.setPrice(currentBidPrice);
        } else {
            System.out.println("The current highest bid is: $" + currentBidPrice);
            System.out.println("How much would you like to bid? Bids lower than or equal to the current highest offer will be ignored.");
            int newBidPrice = Integer.parseInt(uc.sc.nextLine());
            if(newBidPrice > currentBidPrice) newOffer.setPrice(newBidPrice);
            else throw new BidTooLowException();
        }
        int itemId = ic.is.getIdByName(name);
        int currentUserId = uc.getCurrentUser().getId();
        newOffer.setOwnerId(currentUserId);
        newOffer.setItemId(itemId);
        removeLowerOffers(itemId);
        op.add(newOffer);
    }

    public void removeLowerOffers(int itemId){
        op.deleteLowerOffers(itemId);
    }
}
