package com.revature.controllers;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {
    private static ReimbursementService rs = new ReimbursementService();
	private static AuthService as = new AuthService();

    public static void add(Context ctx) {
		Reimbursement r = new Reimbursement();
		ctx.formParamMap();
		r.setAmount(Integer.parseInt(ctx.formParam("amount")));
		r.setDescription(ctx.formParam("description"));
		r.setTypeId(Integer.parseInt(ctx.formParam("typeId")));
		r.setAuthorId(Integer.parseInt(ctx.formParam("authorId")));
		ctx.uploadedFiles().forEach(file -> r.setImage(file.getContent()));
        int reimbId = rs.add(r);
		if (reimbId == -1) ctx.status(HttpCode.BAD_REQUEST);
		else ctx.status(HttpCode.CREATED);
    }

	public static void get(Context ctx) {
		String token = ctx.header("Authorization");				
		if(as.checkPermission(token, Role.ADMIN, Role.MANAGER)) {
			List<Reimbursement> rmbs = null;
			if (ctx.queryParam("author-id") == null) rmbs = rs.getReimbursements();
			else {
				int authorId = Integer.parseInt(ctx.queryParam("author-id"));
				if(authorId > 0) rmbs = rs.getReimbursementsByEmployeeId(authorId);
			}
			if(rmbs == null) ctx.status(HttpCode.BAD_REQUEST);
			else ctx.json(rmbs); ctx.status(HttpCode.CREATED);
		} else if(as.checkPermission(token, Role.BASIC)) {
			String[] info = token.split(":"); 
			int empId = Integer.parseInt(info[0]);
			List<Reimbursement> rmbs = rs.getReimbursementsByEmployeeId(empId);
			if(rmbs == null) ctx.status(HttpCode.BAD_REQUEST);
			else ctx.json(rmbs); ctx.status(HttpCode.CREATED);
		} else {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
	}

	public static void update(Context ctx) {
		String token = ctx.header("Authorization");
		if(!as.checkPermission(token, Role.MANAGER, Role.ADMIN)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		int id = Integer.parseInt(ctx.pathParam("id"));
		String str = ctx.body();
		int statusId = Integer.parseInt(str);
		Reimbursement r = rs.getById(id);
		r.setStatusId(statusId);
		String[] info = token.split(":"); 
		int managerId = Integer.parseInt(info[0]);
		r.setResolverId(managerId);
		if (rs.update(r) > 0) ctx.status(HttpCode.OK);
		else ctx.status(400);
    }
}
