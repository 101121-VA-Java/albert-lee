package com.revature.services;

import java.io.InputStream;

import com.revature.daos.DaoFactory;
import com.revature.daos.ReceiptDao;

public class ReceiptService {
	private ReceiptDao rcd;

	public ReceiptService() {
		rcd = DaoFactory.getDAOFactory().getReceiptDao();
	}

	public InputStream getById(int id){
		return rcd.getById(id);
	}
}
