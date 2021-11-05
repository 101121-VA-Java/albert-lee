package com.revature.controllers;

import com.revature.services.OfferService;
import java.util.Locale;

public class OfferController {
    private final OfferService os;

    public OfferController() {
        os = new OfferService();
    }

    public void attemptOffer(UserController uc, ItemController ic){
        System.out.println("What is the name of the item you would like to bid for?");
        String nameOfItemToBidOn = uc.sc.nextLine();
        os.addOffer(nameOfItemToBidOn, ic, uc);
    }

    public void acceptOffer(UserController uc, ItemController ic) {
        System.out.println("What is the name of the item you want to sell?");
        String itemName = uc.sc.nextLine();
        os.acceptOffer(itemName, uc, ic);
    }
}
