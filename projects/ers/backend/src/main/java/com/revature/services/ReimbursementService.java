package com.revature.services;

import com.revature.daos.DaoFactory;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao rd;

	public ReimbursementService() {
		rd = DaoFactory.getDAOFactory().getReimbursementDao();
	}
    
    public int add(Reimbursement r) {
		return rd.add(r);
    }
    
}
