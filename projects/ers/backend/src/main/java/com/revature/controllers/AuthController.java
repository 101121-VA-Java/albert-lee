package com.revature.controllers;

import com.revature.services.AuthService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthController {
	private static AuthService as = new AuthService();
	public static void login(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		String token = null;
		if(username != null && password != null) token = as.login(username, password);
		if(token != null) {
			ctx.header("Authorization", token);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
	
}