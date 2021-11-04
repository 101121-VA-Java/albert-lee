package com.revature.repositories;

import com.revature.models.Offer;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferPostgres implements GenericDao<Offer>{
    @Override
    public int add(Offer offer) {
        int resultId = -1;
        String sql = "insert into offers (bid_price, bidder_id, item_id) "
                + "values (?, ?, ?) returning offer_id;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, offer.getPrice());
            ps.setInt(2, offer.getOwnerId());
            ps.setInt(3, offer.getItemId());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                resultId = rs.getInt("offer_id");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return resultId;
    }

    @Override
    public List<Offer> getAll() {
        String sql = "select * from offers;";
        List<Offer> offers = new ArrayList<>();

        return offers;
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

    public int getOfferPriceByItemName(String name) {
        String sql = "select max(bid_price) as bid_price from offers join items on offers.item_id = items.item_id where item_name = ?;";
        int currentBid = -1;
        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                currentBid = rs.getInt("bid_price");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return currentBid;
    }

    public void deleteLowerOffers(int itemId) {
        String sql = "delete from offers where item_id = ?";
        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
