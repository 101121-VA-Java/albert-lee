package com.revature.repositories;

import com.revature.models.items.Item;
import com.revature.models.items.RiskyItem;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPostgres implements GenericDao<Item> {
    @Override
    public int add(Item item) {
        int resultId = -1;
        String sql = "insert into items (item_name, item_price, item_type, owner_id) "
                + "values (?, ?, ?, null) returning item_id;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, item.getName().replace("risky ", ""));
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getType());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                resultId = rs.getInt("item_id");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return resultId;
    }

    @Override
    public List<Item> getAll() {
        String sql = "select * from items where owner_id is null;";
        List<Item> items = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int ownerId = rs.getInt("owner_id");
                String type = rs.getString("item_type");
                if(type.equals("BORING")) items.add(new Item(id, name, price, ownerId, type));
                else if(type.equals("RISKY")) items.add(new RiskyItem(id, name, price, ownerId, type));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getById(int id) {

        String sql = "select * from items where item_id = ? ";
        Item result = new Item();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int itemId = rs.getInt("item_id");
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int ownerId = rs.getInt("owner_id");
                String itemType = rs.getString("item_type");
                result = new Item(itemId, name, price, ownerId, itemType);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Item item) {
        int itemId = 0;
        String sql = "update items " +
                "set owner_id = ? " +
                "where item_id = ? ";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, item.getOwnerId());
            ps.setInt(2, item.getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                itemId = rs.getInt("item_id");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return itemId;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from items where item_id = ?";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public Item getByName(String name){

        String sql = "select * from items where item_name like ? ";
        Item result = new Item();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int itemId = rs.getInt("item_id");
                String item_name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int ownerId = rs.getInt("owner_id");
                String itemType = rs.getString("item_type");
                result = new Item(itemId, item_name, price, ownerId, itemType);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getByOwnerId(int id) {
        String sql = "select * from items where owner_id = ? ";
        List<Item> items = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int itemId = rs.getInt("item_id");
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int ownerId = rs.getInt("owner_id");
                String itemType = rs.getString(("item_type"));
                Item result = new Item(itemId, name, price, ownerId, itemType);
                items.add(result);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public int getIdByName(String name){
        int resultId = -1;
        String sql = "select * from items where item_name like ? limit 1;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                resultId = rs.getInt("item_id");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return resultId;
    }


    public void purchaseItem(int highestBidderId, int itemId) {
        String sql = "update items set owner_id = ? where item_id = ?";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, highestBidderId);
            ps.setInt(2, itemId);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
