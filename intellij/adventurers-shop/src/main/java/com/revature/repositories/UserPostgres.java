package com.revature.repositories;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPostgres implements GenericDao<User>{
    @Override
    public int add(User user) {
        int resultId = -1;
        String sql = "insert into users (user_name, user_password, user_role) "
                + "values (?, ?, ?) returning user_id;";

        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
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
        String sql = "select * from users;";
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String password = rs.getString("user_password");
                String role = rs.getString("user_role");
                User usr = new User(id, username, password, role);
                users.add(usr);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        String sql = "select * from users where user_id = ? ";
        User usr = null;

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String password = rs.getString("user_password");
                String role = rs.getString("user_role");
                usr = new User(userId, username, password, role);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return usr;
    }

    @Override
    public int update(User user) {
        String sql = "update users " +
                "set user_password = ? " +
                "where user_id = ? ";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getPassword());
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
        String sql = "delete from users " +
                "where user_id > ?";

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
