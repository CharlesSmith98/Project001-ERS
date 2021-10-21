package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbService {

	private ReimbDao rDao;
	
	public ReimbService(ReimbDao dao) {
		this.rDao = dao;
	}
	
	public boolean submitReimbursement(Reimbursement reimb) {
		boolean created = false;
		created = rDao.createReimbursement(reimb);
		return created;
	}
	
	public List<Reimbursement> viewPendingReimbursementsByUser(User user) {
		List<Reimbursement> reimbursements = rDao.getPendingReimbursementsBy(user);
		return reimbursements;
	}
	
	public List<Reimbursement> viewResolvedReimbursementsByUser(User user) {
		List<Reimbursement> reimbursements = rDao.getResolvedReimbursementsBy(user);
		return reimbursements;
	}
	
}
