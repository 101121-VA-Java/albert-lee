package com.revature.daos;

public class DaoFactory {

	private static DaoFactory df;
	private UserDao ud;
	private ReimbursementDao rd;
	private ReceiptDao rcd;

	private DaoFactory() {
	}

	public static DaoFactory getDAOFactory() {
		if (df == null) {
			df = new DaoFactory();
		}
		return df;
	}
	
	public UserDao getUserDao() {
		if(ud == null) {
			ud = new UserDao();
		}
		return ud;
	}

    public ReimbursementDao getReimbursementDao() {
        if(rd == null) {
			rd = new ReimbursementDao();
		}
		return rd;
    }

	public ReceiptDao getReceiptDao() {
        if(rcd == null) {
			rcd = new ReceiptDao();
		}
		return rcd;
    }
}
