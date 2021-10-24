package com.revature.tests.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.ReimbDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbService;

public class ReimbServiceTest {
	
	@InjectMocks
	public ReimbService rServ;
	
	@Mock
	public ReimbDao rDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void testDelim() {
		System.out.println("----------");
	}
	
	@Test
	public void resolveReimbursementTest() {
		User ulrich = new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1);
		Reimbursement reimb = new Reimbursement(200.00, ulrich.getId(), 3);
		reimb.setResolverId(1);
		reimb.setStatusId(2);
		reimb.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		
		when(rDao.updateReimbursement((Reimbursement)any(Reimbursement.class))).thenReturn(true);
		boolean resolved = rServ.resolveReimbursement(reimb);
		if(resolved) {
			System.out.println("Reimbursement successfully resolved!");
		} else {
			System.out.println("Failed to resolve reimbursement.");
		}
		assertTrue(resolved);
	}
	
	@Test
	public void submitReimbursementTest() {
		User ulrich = new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1);
		Reimbursement reimb = new Reimbursement(200.00, ulrich.getId(), 3);
		
		when(rDao.createReimbursement((Reimbursement) any(Reimbursement.class))).thenReturn(true);
		
		boolean created = rServ.submitReimbursement(reimb);
		if(created) {
			System.out.println("Reimbursement successfully submitted!");
		} else {
			System.out.println("Failed to submit reimbursement.");
		}
		assertTrue(created);
	}
	
	@Test
	public void viewAllPendingReimbursementsTest() {
		List<Reimbursement> expectedReimbs = new ArrayList<>();
		expectedReimbs.add(new Reimbursement(4, 300, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 1, 2));
		
		when(rDao.getPendingReimbursements()).thenReturn(expectedReimbs);
		System.out.println("Results of viewAllPendingReimbursements:");
		List<Reimbursement> reimbursements = rServ.viewAllPendingReimbursements();
		for(Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertFalse(reimbursements.size() == 0);
	}
	
	@Test
	public void viewAllReimbursementsByTest() {
		User yumi = new User(3, "YIshiama", "japan", "Yumi", "Ishiama", "yishiama@mail.com", 1);
		
		ArrayList<Reimbursement> expectedReimbs = new ArrayList<>();
		expectedReimbs.add(new Reimbursement(4, 300, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 1, 2));
		
		when(rDao.getAllReimbursementsBy((User) any(User.class))).thenReturn(expectedReimbs);
		
		System.out.println("Results of viewAllReimbursementsBy:");
		List<Reimbursement> reimbursements = rServ.viewAllReimbursementsBy(yumi);
		for(Reimbursement reimb : reimbursements) {
			System.out.println(reimb);
		}
		assertFalse(reimbursements.size() == 0);
	}
	
	@Test
	public void viewAllResolvedReimbursementsTest() {
		List<Reimbursement> expectedReimbs = new ArrayList<>();
		expectedReimbs.add(new Reimbursement(4, 300, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 2, 2));
		
		when(rDao.getResolvedReimbursements()).thenReturn(expectedReimbs);
		System.out.println("Results of viewAllResolvedReimbursements:");
		List<Reimbursement> reimbursements = rServ.viewAllResolvedReimbursements();
		for(Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertFalse(reimbursements.size() == 0);
	}
	
	@Test
	public void viewPendingReimbursementsByUserTest() {
		User yumi = new User(3, "YIshiama", "japan", "Yumi", "Ishiama", "yishiama@mail.com", 1);
		
		ArrayList<Reimbursement> expectedReimbs = new ArrayList<>();
		expectedReimbs.add(new Reimbursement(4, 300, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 1, 2));
		
		when(rDao.getPendingReimbursementsBy((User) any(User.class))).thenReturn(expectedReimbs);
		
		System.out.println("Results of viewPendingReimbursementsByUser:");
		List<Reimbursement> reimbursements = rServ.viewPendingReimbursementsByUser(yumi);
		for(Reimbursement reimb : reimbursements) {
			System.out.println(reimb);
		}
		assertFalse(reimbursements.size() == 0);
	}
	
	@Test
	public void viewResolvedReimbursementsByUserTest() {
		User yumi = new User(3, "YIshiama", "japan", "Yumi", "Ishiama", "yishiama@mail.com", 1);
		
		ArrayList<Reimbursement> expectedReimbs = new ArrayList<>();
		expectedReimbs.add(new Reimbursement(2, 250, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 2, 4));
		expectedReimbs.add(new Reimbursement(3, 200, new Timestamp(System.currentTimeMillis()), null, null, null, 3, 0, 3, 3));
		
		when(rDao.getResolvedReimbursementsBy((User) any(User.class))).thenReturn(expectedReimbs);
		
		System.out.println("Results of viewResolvedReimbursementsByUser:");
		List<Reimbursement> reimbursements = rServ.viewResolvedReimbursementsByUser(yumi);
		for(Reimbursement reimb : reimbursements) {
			System.out.println(reimb);
		}
		assertFalse(reimbursements.size() == 0);
	}
	
}
