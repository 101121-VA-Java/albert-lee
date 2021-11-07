package com.revature.controllers;

import com.revature.exceptions.BidTooLowException;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.services.OfferService;

import java.util.Locale;

public class OfferController {
    private final OfferService os;

    public OfferController() {
        os = new OfferService();
    }

    public void attemptOffer(UserController uc, ItemController ic){
        System.out.println("What is the name of the item you would like to bid for?");
        String itemName = uc.sc.nextLine();
        int currentBidPrice = os.op.getOfferPriceByItemName(itemName);
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
        int itemId = ic.is.getIdByName(itemName);
        int currentUserId = uc.getCurrentUser().getId();
        newOffer.setOwnerId(currentUserId);
        newOffer.setItemId(itemId);
        os.addOffer(newOffer);
    }

    public void acceptOffer(UserController uc, ItemController ic) {
        System.out.println("What is the name of the item you want to sell?");
        String itemName = uc.sc.nextLine();
        Item item = ic.is.ip.getByName(itemName);
        int highestOffer = os.op.getOfferPriceByItemName(itemName);
        int highestBidderId = os.op.getHighestBidderId(itemName);
        System.out.println("Sell for " + highestOffer + "? Y / N");
        String choice = uc.sc.nextLine();
        if(choice.toUpperCase(Locale.ROOT).equals("Y")){
            ic.is.ip.purchaseItem(highestBidderId, item.getId());
            os.removeOffers(ic.is.getIdByName(itemName));
        } else {
            System.out.println("Returning to main menu.");
        }
    }
}
