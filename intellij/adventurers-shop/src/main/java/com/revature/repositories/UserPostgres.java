package com.revature.repositories;

import com.revature.models.users.User;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPostgres implements GenericDao<User>{
    @Override
    public int add(User user) {
        int resultId = -1;
        String sql = "insert into users (user_name, user_password, user_role, cash_on_hand) "
                + "values (?, ?, ?, ?) returning user_id;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setInt(4, 100);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                resultId = rs.getInt("user_id");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return resultId;
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from employees;";
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("e_id");
                String e_username = rs.getString("e_username");
                String e_password = rs.getString("e_password");
                String e_role = rs.getString("e_role");
                int e_cashOnHand = rs.getInt("e_cash_on_hand");
                User newEmp = new User(id, e_username, e_password, e_role, e_cashOnHand);
                users.add(newEmp);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        String sql = "select * from users where e_id = ? ";
        User usr = null;

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int e_id = rs.getInt("e_id");
                String e_username = rs.getString("e_username");
                String e_password = rs.getString("e_password");
                String role = rs.getString("e_role");
                int cashOnHand = rs.getInt("e_cash_on_hand");
                usr = new User(e_id, e_username, e_password, role, cashOnHand);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return usr;
    }

    @Override
    public int update(User user) {
        String sql = "update users " +
                "set cash_on_hand = ? " +
                "where e_id = ? ";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, user.getCashOnHand());
            ps.setInt(2, user.getId());

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

    }
}
