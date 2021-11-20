package com.revature.models;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Role role;
	private User manager;
	
	public User() {
		super();
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username, String password, String email, String firstName, String lastName, Role role, User manager) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.manager = manager;
	}

	public User(int id, String username, String password, String firstName, String lastName, String email, int roleId, User manager){
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = setRoleFromId(roleId);
		this.manager = manager;
	}

	public User(int id, String username, String firstName, String lastName, String email, int roleId, User manager) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = setRoleFromId(roleId);
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role setRoleFromId(int roleId){
		if (roleId == 1) {
			setRole(Role.ADMIN);
			return Role.ADMIN;
		} else if (roleId == 2) {
			setRole(Role.MANAGER);
			return Role.MANAGER;
		} else if (roleId == 3) {
			setRole(Role.BASIC);
			return Role.BASIC;
		}
		return null;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

    public int getRoleId() {
        if (role == Role.ADMIN) return 1;
		else if (role == Role.MANAGER) return 2;
		else if (role == Role.BASIC) return 3;
		else return -1;
    }
}
