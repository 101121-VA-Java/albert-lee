package com.revature.services;

import com.revature.daos.DaoFactory;
import com.revature.daos.RequirementDao;
import com.revature.models.Requirement;

public class RequirementService {

	private RequirementDao rqd;

	public RequirementService() {
		rqd = DaoFactory.getDAOFactory().getRequirementDao();
	}

	public int updateReq(Requirement r) {
		return rqd.update(r);	
	}

    public Requirement getById(int id) {
        return rqd.getById(id);
    }
}
