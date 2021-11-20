package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {
    private static ReimbursementService rs = new ReimbursementService();

    public static void add(Context ctx) {
        int reimbId = rs.add(ctx.bodyAsClass(Reimbursement.class));																	// successful, or null otherwise
		if (reimbId == -1) {
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.CREATED);
		}
    }
}
