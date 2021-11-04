package com.revature.repositories;

import com.revature.models.Item;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPostgres implements GenericDao<Item> {
    public int add(Item item) {
        int resultId = -1;
        String sql = "insert into items (item_name, item_price, owner_id) "
                + "values (?, ?, null) returning item_id;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
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
                Item usr = new Item(id, name, price, ownerId);
                items.add(usr);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getById(int id) {
        return null;
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
                Item result = new Item(itemId, name, price, ownerId);
                items.add(result);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public int update(Item item) {
        String sql = "update items " +
                "set owner_id = ? " +
                "where item_id = ? ";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, item.getOwnerId());
            ps.setInt(2, item.getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return 1;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
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
}
