package com.revature.repositories;

import com.revature.models.Offer;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
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

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int bidPrice = rs.getInt("bid_price");
                int bidderId = rs.getInt("bidder_id");
                int itemId = rs.getInt("item_id");
                Offer newOffer = new Offer(bidPrice, bidderId, itemId);
                offers.add(newOffer);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
        String sql = "select max(bid_price) as bid_price from offers join items on offers.item_id = items.item_id where item_name like ?;";
        int currentBid = -1;
        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                currentBid = rs.getInt("bid_price");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return currentBid;
    }

    public void deleteOffers(int itemId) {
        String sql = "delete from offers where item_id = ?";
        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getHighestBidderId(String itemName) {
        String sql = "select bidder_id from offers join items on offers.item_id = items.item_id where item_name = ?;";
        int bidderId = -1;
        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                bidderId = rs.getInt("bidder_id");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return bidderId;
    }
}
