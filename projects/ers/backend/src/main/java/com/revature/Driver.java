package com.revature;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		Javalin app = Javalin.create( (config) -> {
				config.enableCorsForAllOrigins();
				config.defaultContentType = "application/json";
			}).start();
		
		app.before(ctx -> {
			ctx.header("Access-Control-Allow-Headers", "Authorization");
			ctx.header("Access-Control-Expose-Headers", "Authorization");
		});

		app.routes(() -> {
			path("users", ()->{
				get(UserController::getUsers);
				post(UserController::registerUser);
				path("{id}", () -> {
					put(UserController::updateUser);
				});
			});

			path("reimbursements", () -> {
				post(ReimbursementController::add);
				get(ReimbursementController::get);
				path("{id}", () -> {
					put(ReimbursementController::update);
				});
			});

			path("auth", () -> {
				post(AuthController::login);
			});
			
		});
	}
}
