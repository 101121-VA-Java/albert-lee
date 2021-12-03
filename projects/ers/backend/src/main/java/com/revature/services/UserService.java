package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.daos.DaoFactory;
import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.models.Role;

public class UserService {

	private UserDao ud;

	public UserService() {
		ud = DaoFactory.getDAOFactory().getUserDao();
	}

	public List<User> getUsers(){
		List<User> users = ud.getAll().stream()
				.map(e -> {
					e.setPassword(null); 
					return e;})
				.collect(Collectors.toList());
		return users;
		}

	public User getUserById(int id){
		User u = ud.getUserById(id);
		if (u != null) {
			u.setPassword(null);
		}
		return u;
	}
	
	public User getByUsername(String username){
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(null);
		}
		
		return u;
	}
	
	public int addUser(User u) {
		u.setManager(new User(1));
		u.setRole(Role.BASIC);
		return ud.add(u);
	}
	
	public int updateUser(User u) {
		return ud.update(u);	
	}
}
