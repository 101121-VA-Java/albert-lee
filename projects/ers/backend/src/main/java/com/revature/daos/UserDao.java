package com.revature.daos;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;

public class UserDao implements GenericDao<User>{
	@Override
	public int add(User u) {
		int result = -1;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id, ers_manager_id)" + 
			"values (?,?,?,?,?,?,?) returning ers_users_id;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRoleId());
			ps.setInt(7, u.getManager().getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("ers_users_id");
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in UserDao");
			e.printStackTrace();
		}
		return result;
	}

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_users;";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				User u = new User(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"),
						new User(rs.getInt("ers_users_id")));
				users.add(u);
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in UserDao");
			e.printStackTrace();
		}
		return users;
    }

    @Override
    public int update(User u) {
        int result = 0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "UPDATE ers_users SET ers_username = ?, ers_password = ?, user_email = ?, user_first_name = ?, user_last_name = ?, user_role_id = ?, ers_manager_id = ? WHERE ers_users_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getFirstName());
            ps.setString(5, u.getLastName());
            ps.setInt(6, u.getRoleId());
			ps.setInt(7, u.getManager().getId());
			ps.setInt(8, u.getId());
			result = ps.executeUpdate();
			if (result > 0) return result;
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in UserDao");
			e.printStackTrace();
		}
		return result;
    }

    @Override
    public void delete(int id) {
    }

    public User getUserById(int id){
        User u = null;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "select * from ers_users where ers_users_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u = new User(
					rs.getInt("ers_users_id"), 
					rs.getString("ers_username"), 
					rs.getString("user_first_name"), 
					rs.getString("user_last_name"), 
					rs.getString("user_email"), 
					rs.getInt("user_role_id"), 
					new User(rs.getInt("ers_manager_id")));
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in UserDao");
			e.printStackTrace();
		}
		return u;
    }

	public User getByUsername(String username) {
		User user = null;
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_users where ers_username = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"), 
						rs.getString("ers_password"), 
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"),
						new User (rs.getInt("ers_manager_id")));
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in UserDao");
			e.printStackTrace();
		}
		return user;
	}
}
