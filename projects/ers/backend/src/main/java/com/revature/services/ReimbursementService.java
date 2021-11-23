package com.revature.services;

import java.util.List;

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

	public List<Reimbursement> getReimbursements(){
		return rd.getAll();
	}

    public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
        return rd.getByEmployeeId(id);
	}

	public Reimbursement getById(int id){
		return rd.getById(id);
	}

	public int update(Reimbursement r) {
		return rd.update(r);
	}
}
