package com.revature.services;

import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.repositories.PaymentPostgres;

import java.util.List;

public class PaymentService {
    public final PaymentPostgres pp;

    public PaymentService() {
        pp = new PaymentPostgres();
    }

    public List<Payment> getAll(){
        return pp.getAll();
    }

    public int getIdByName(String name) {
//        return pp.getIdByName(name);
        return 0;
    }

    public List<Item> getItemsBelongingToUserId(int id) {
//        return pp.getByOwnerId(id);
        return null;
    }

    public void addUnownedItemForSale(Item item){
//        pp.add(item);
    }

    public void removeItemByName(String name){
//        for (Item item : pp.getAll()) {
//            if(name.equals(item.getName())){
//                pp.delete(item.getId());
//            }
//        }
    }
}
