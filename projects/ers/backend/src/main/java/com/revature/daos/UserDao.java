package com.revature.daos;

import java.io.IOException;
import java.sql.*;
// import java.util.ArrayList;
import java.util.List;

// import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao implements GenericDao<User>{

    
	/**
	 * Dao method to create an employee, assigns an employee id generated by the database before returning
	 * @param user object with all fields except for id
	 * @return a user id or -1
	 */
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
			e.printStackTrace();
		}
		
		return result;
	}

    /**
	 * Dao method to retrieve all employees
	 * @return a List of Employees or an empty list if none are found
	 */
    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(User t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        
    }

    public User getUserById(int id){
        return null;
    }

	/**
	 * Dao method to retrieve employees by a manager id
	 * @param id of the manager employee
	 * @return a List of Employees or an empty list if none are found
	 * @throws SQLException
	 */
	// public List<User> getEmployeesByManagerId(int id) throws SQLException {
		
	// 	List<User> users = new ArrayList<>();

	// 	try (Connection c = ConnectionUtil.getConnection()) {
	// 		String sql = "select * from employees where m_id = ?;";

	// 		PreparedStatement ps = c.prepareStatement(sql);
			
	// 		ps.setInt(1, id);
			
	// 		ResultSet rs = ps.executeQuery();

	// 		while (rs.next()) {
	// 			// Retrieving employee info, setting manager's value to another Employee dummy object with only its id
	// 			User e = new User(
	// 					rs.getInt("e_id"), 
	// 					rs.getString("e_name"), 
	// 					rs.getString("e_username"),
	// 					rs.getString("e_password"), 
	// 					Role.valueOf(rs.getString("e_role")),
	// 					new User(rs.getInt("m_id")));

	// 			// Adding user to userss list to be returned
	// 			users.add(e);
	// 		}
	// 	} catch (SQLDataException | IOException e) {
	// 		e.printStackTrace();
	// 	}

	// 	return users;
	// }
	
	/**
	 * Dao method to retrieve an employee by id
	 * @param id of the employee
	 * @return an employee or null if none is found
	 */

	// public User getEmployeeById(int id) {

	// 	User user = null;

	// 	try (Connection c = ConnectionUtil.getConnection()) {
	// 		String sql = "select * from users where e_id = ?;";

	// 		PreparedStatement ps = c.prepareStatement(sql);

	// 		ps.setInt(1, id);

	// 		ResultSet rs = ps.executeQuery();

	// 		if (rs.next()) {
	// 			user = new User(
	// 					rs.getInt("e_id"),
	// 					rs.getString("e_name"), 
	// 					rs.getString("e_username"),
	// 					rs.getString("e_password"), 
	// 					Role.valueOf(rs.getString("e_role")),
	// 					new User(rs.getInt("m_id")));
	// 		}
	// 	} catch (SQLException | IOException e) {
	// 		e.printStackTrace();
	// 	}

	// 	return user;
	// }

	/**
	 * Service method to retrieve an employee by its username
	 * @param username of the user
	 * @return an employee or null if none is found
	 */
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
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Dao method to update an employee
	 * @param employee object
	 * @return true if an employee was updated successfully, else false
	 */
	public boolean updateUser(User u) {
		String sql = "update users set e_name = ?, e_username = ?, e_password = ?, e_role = ?, m_id = ? "
				+ "where e_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getRole().toString());
			ps.setInt(6, u.getManager().getId());
			ps.setInt(7, u.getId());

			rowsChanged = ps.executeUpdate();

		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}

		if (rowsChanged > 0) {
			return true;
		}
		
		return false;
	}

	
}
