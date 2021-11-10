package com.revature.controllers;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.Payment;
import com.revature.services.OfferService;
import com.revature.utils.LogUtil;

public class OfferController {
    private final OfferService os;
    private static OfferController firstInstance = null;

    private OfferController() {
        os = new OfferService();
    }

    public static OfferController getInstance() {
        if(firstInstance == null){
            firstInstance = new OfferController();
        }
        return firstInstance;
    }

    public void attemptOffer(UserController uc, ItemController ic){
        System.out.println("What is the name of the item you would like to bid for?");
        String itemName = uc.getScanner().nextLine();
        int currentBidPrice = os.op.getOfferPriceByItemName(itemName);
        Offer newOffer = new Offer();
        if(currentBidPrice <= 0){
            System.out.println("There are no bids yet. How much would you like to offer?");
            currentBidPrice = Integer.parseInt(uc.getScanner().nextLine());
            newOffer.setPrice(currentBidPrice);
            System.out.println("New bid placed for $" + currentBidPrice);
        } else {
            System.out.println("The current highest bid is: $" + currentBidPrice);
            System.out.println("How much would you like to bid? Bids lower than or equal to the current highest offer will be ignored.");
            int newBidPrice = Integer.parseInt(uc.getScanner().nextLine());
            if(newBidPrice > currentBidPrice) {
                newOffer.setPrice(newBidPrice);
                System.out.println("New bid placed for $" + newBidPrice);
            }
            else {
                LogUtil.descriptiveError("Bid too low, please try again.");
            }
        }
        int itemId = ic.getItemService().getIdByName(itemName);
        int currentUserId = uc.getCurrentUser().getId();
        newOffer.setOwnerId(currentUserId);
        newOffer.setItemId(itemId);
        os.addOffer(newOffer);
    }

    public void acceptOffer(UserController uc, ItemController ic, PaymentController pc) {
        System.out.println("What is the name of the item you want to sell?");
        String itemName = uc.getScanner().nextLine();
        Item item = ic.getItemService().ip.getByName(itemName);
        int highestOffer = os.op.getOfferPriceByItemName(itemName);
        int highestBidderId = os.op.getHighestBidderId(itemName);
        System.out.println("Accept offer for $" + highestOffer + "? Y / N");
        String choice = uc.getScanner().nextLine();
        if(choice.equals("Y") | choice.equals("y")){
            ic.getItemService().ip.purchaseItem(highestBidderId, item.getId());
            os.removeOffers(ic.getItemService().getIdByName(itemName));
            Payment payment1 = new Payment();
            Payment payment2 = new Payment();
            if(highestOffer % 2 == 0){
                payment1 = new Payment(highestBidderId, item.getId(), highestOffer/2);
                payment2 = new Payment(highestBidderId, item.getId(), highestOffer/2);
            } else if(highestOffer % 2 == 1) {
                payment1 = new Payment(highestBidderId, item.getId(), (highestOffer + 1)/2);
                payment2 = new Payment(highestBidderId, item.getId(), (highestOffer - 1)/2);
            }
            pc.add(payment1);
            pc.add(payment2);
            System.out.println(itemName + " has been sold for $" + highestOffer);
        } else {
            System.out.println("Cancelling. Returning to main menu.");
        }
    }
}
