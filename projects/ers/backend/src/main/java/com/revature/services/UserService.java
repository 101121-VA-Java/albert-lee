package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.daos.DaoFactory;
import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.models.Role;

public class UserService {

	private UserDao ud;

	// Retrieving an instance of UserDao
	public UserService() {
		ud = DaoFactory.getDAOFactory().getUserDao();
	}

	/**
	 * Service method to retrieve all employees, sets the employee passwords to null before returning them
	 * @return a List of Employees or an empty list if none are found
	 */
	public List<User> getUsers(){
		/*
		 *  Java Streams, allows for quick processing of collections of objects (filter, map...)
		 *  	- here each Employee element of the employees List has their password set to null and are returned using the map method
		 *  	- the results are then collected back into a List
		 *  
		 *  for more information: https://www.baeldung.com/java-8-streams
		 */
		List<User> users = ud.getAll().stream()
				.map(e -> {
					e.setPassword(null); 
					return e;})
				.collect(Collectors.toList());
		return users;
		}
	
	/**
	 * Service method to retrieve employees by a manager id, sets the employee passwords to null before returning them
	 * @param id of the manager employee
	 * @return a List of Employees or an empty list if none are found
	 */
	// public List<Employee> getEmployeesByManager(int id){
	// 	List<Employee> employees = ed.getEmployeesByManagerId(id).stream()
	// 			.map(e -> {
	// 				e.setPassword(null); 
	// 				return e;})
	// 			.collect(Collectors.toList());
		
	// 	return employees;
	// }
	
	/**
	 * Service method to retrieve an employee by id, sets the employee password to null before returning
	 * @param id of the employee
	 * @return an employee or null if none is found
	 */
	public User getUserById(int id){
		User u = ud.getUserById(id);
		if (u != null) {
			u.setPassword(null);
		}
		return u;
	}
	
	/**
	 * Service method to retrieve an employee by its username, sets the employee password to null before returning
	 * @param username of the employee
	 * @return an employee or null if none is found
	 */
	public User getByUsername(String username){
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(null);
		}
		
		return u;
	}
	
	/**
	 * Service method to create an employee, sets to a default manager and default role. The employee id assigned before returning it
	 * @param employee object, requires name, username, password 
	 * @return an employee with an id or null if none is created
	 */
	public int addUser(User u) {
		// assigning a default manager/role
		u.setManager(new User(1));
		u.setRole(Role.BASIC);

		return ud.add(u);
	}
	
	/**
	 * Service method to update an employee
	 * @param employee object, requires an id and valid field values
	 * @return true if an employee was updated, else false
	 */
	// public boolean updateEmployee(Employee e) {
	// 	return ed.updateEmployee(e);	
	// }
}
