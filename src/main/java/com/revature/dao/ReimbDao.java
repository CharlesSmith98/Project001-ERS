package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbDao {

	// (C) CREATE
	boolean createReimbursement(Reimbursement reimb);
	
	// (R) READ
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getPendingReimbursements();
	List<Reimbursement> getResolvedReimbursements();
	List<Reimbursement> getAllReimbursementsBy(User user);
	List<Reimbursement> getPendingReimbursementsBy(User user);
	List<Reimbursement> getResolvedReimbursementsBy(User user);
	Reimbursement getReimbursementById(int reimbId);
	
	// (U) UPDATE
	boolean updateReimbursement(Reimbursement reimb);
	
	// (D) DELETE
	boolean deleteReimbursement(Reimbursement reimb);
	
}
