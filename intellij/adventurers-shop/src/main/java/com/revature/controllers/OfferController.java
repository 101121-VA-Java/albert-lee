package com.revature.controllers;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.services.OfferService;

import java.util.Scanner;

public class OfferController {
    private final OfferService os;

    public OfferController() {
        os = new OfferService();
    }

    public void attemptOffer(UserController uc, ItemController ic){
        System.out.println("What is the name of the item you would like to bid for?");
        String nameOfItemToBidOn = uc.sc.nextLine();
        os.addOffer(nameOfItemToBidOn, ic, uc);
        //get highest offer
        //the current highest bid is _
        //please bid higher or ignore
        //if new bid is valid
            //add new bid
            //delete old bid
    }

    public void acceptOffer(Scanner sc, ItemController ic) {
        //which item would you like to accept a bid for?
        //get string name to search for.
    }
}
