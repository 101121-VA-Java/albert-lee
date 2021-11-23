package com.revature.controllers;

import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class UserController {

	private static UserService us = new UserService();
	private static AuthService as = new AuthService();

	public static void getUsers(Context ctx) {
		String token = ctx.header("Authorization");				
		if(as.checkPermission(token, Role.ADMIN, Role.MANAGER)) {
			List<User> users = us.getUsers();
			ctx.json(users);
			ctx.status(HttpCode.OK);
		} else if(as.checkPermission(token, Role.BASIC)) {
			getUserById(ctx);
		} else {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
	}

	public static void registerUser(Context ctx) {
		/*
		 * add employee requires an Employee Object with a name, username, password
		 * 
		 * HTTP request - version - headers - body - need to have name, username,
		 * password - url - localhost:8080/employees - http verb/method... - POST
		 */

		/*
		 * Object mapper converts JSON object to Java Object mapped to the Employee
		 * class - fields the the JSON object that match fields in the Employee java
		 * class will be mapped accordingly
		 */
		int userId = us.addUser(ctx.bodyAsClass(User.class)); // should return new employee id																			// successful, or null otherwise
		if (userId == -1) {
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.CREATED);
		}

	}

	public static void getUserById(Context ctx) {
		int id = Integer.parseInt(ctx.queryParam("id"));
		User u = us.getUserById(id);
		if (u != null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void getByUsername(Context ctx) {
		String body = ctx.body();
		User u = us.getByUsername(body);
		if (u != null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
			ctx.json("fail");
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void updateUser(Context ctx) {
		User updated = ctx.bodyAsClass(User.class);
		System.out.println(updated.getId());
		User stale = us.getUserById(updated.getId());
		updated.setManager(stale.getManager());
		if(us.updateUser(updated) <= 0){
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.OK);
		}
	}
}
