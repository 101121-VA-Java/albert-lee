package com.revature.controllers;

import java.io.IOException;
import java.io.InputStream;

import com.revature.services.ReceiptService;


import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReceiptController {
	private static ReceiptService rs = new ReceiptService();
	public static void get(Context ctx) throws IOException {
		InputStream receiptImage = rs.getById(Integer.parseInt(ctx.pathParam("id")));
        if(receiptImage == null) ctx.status(HttpCode.BAD_REQUEST);
		else ctx.result(receiptImage);
	}
}