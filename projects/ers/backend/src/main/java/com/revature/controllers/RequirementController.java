package com.revature.controllers;

import com.revature.models.Requirement;
import com.revature.services.RequirementService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class RequirementController {
    private static RequirementService rs = new RequirementService();
    public static void update(Context ctx){
        int reqId = Integer.parseInt(ctx.pathParam("id"));
        Requirement r = rs.getById(reqId);
        if(r.getStatus() == 0) r.setStatus(1);
        else if(r.getStatus() == 1) r.setStatus(0);
        if(rs.updateReq(r) > 0) ctx.status(HttpCode.OK);
        else ctx.status(400);
    }
}
