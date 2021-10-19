import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbDaoTest {

	private static ReimbDao rDao;
	private static UserDao uDao;
	private static User yumi;
	
	@BeforeClass
	public static void testSetup() {
		rDao = new ReimbDaoDB();
		uDao = new UserDaoDB();
		yumi = uDao.getUserById(3);
	}
	
	@Before
	public void testDelim() {
		System.out.println("----------");
	}
	
	@Test
	public void createReimbursementTest() {
		Reimbursement reimb = new Reimbursement(200, 2, 1);
		boolean success = rDao.createReimbursement(reimb);
		if(success) {
			System.out.println("Reimbursement was successfully added to the database!");
		} else {
			System.out.println("Failed to add reimbursement to the database.");
		}
		assertTrue(success);
	}
	
	@Test
	public void getAllReimbursementsTest() {
		List<Reimbursement> reimbursements = rDao.getAllReimbursements();
		System.out.println("Results of getAllReimbursements: ");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertFalse(reimbursements.size() == 1);
	}
	
	@Test
	public void getPendingReimbursementsTest() {
		List<Reimbursement> reimbursements = rDao.getPendingReimbursements();
		System.out.println("Results of getPendingReimbursements: ");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertTrue(reimbursements.size() != 1);
	}
	
	@Test
	public void getResolvedReimbursementsTest() {
		List<Reimbursement> reimbursements = rDao.getResolvedReimbursements();
		System.out.println("Results of getResolvedReimbursements: ");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertTrue(reimbursements.size() != 1);
	}
	
	@Test
	public void getAllReimbursementsByTest() {
		List<Reimbursement> reimbursements = rDao.getAllReimbursementsBy(yumi);
		System.out.println("Results of getAllReimbursementsBy:");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertTrue(reimbursements.size() != 1);
	}
	
	@Test
	public void getPendingReimbursementsByTest() {
		List<Reimbursement> reimbursements = rDao.getPendingReimbursementsBy(yumi);
		System.out.println("Results of getPendingReimbursementsBy:");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertFalse(reimbursements.size() != 1);
	}
	
	@Test
	public void getResolvedReimbursementsByTest() {
		List<Reimbursement> reimbursements = rDao.getResolvedReimbursementsBy(yumi);
		System.out.println("Results of getResolvedReimbursementsBy:");
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}
		assertTrue(reimbursements.size() != 1);
	}
	
	@Test
	public void getReimbursementByIdTest() {
		Reimbursement reimb = new Reimbursement(1, 100, null, null, null, null, 2, 0, 1, 1);
		Reimbursement ret = rDao.getReimbursementById(1);
		boolean success = (ret.getAmount() == reimb.getAmount());
		System.out.println("Result of getReimbursementById: " + ret);
		assertTrue(success);
	}
	
	@Test
	public void updateReimbursementTest() {
		Reimbursement reimb = rDao.getReimbursementById(1);
		Timestamp initial = reimb.getTimeResolved();
		reimb.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		reimb.setResolverId(1);
		System.out.println("Initial TimeStamp: " + initial);
		rDao.updateReimbursement(reimb);
		reimb = rDao.getReimbursementById(1);
		System.out.println("New TimeStamp: " + reimb.getTimeResolved());
		assertFalse(reimb.getTimeResolved().equals(initial));
	}
	
	@Test
	public void deleteReimbursementTest() {
		Reimbursement reimb = rDao.getReimbursementById(5);
		boolean success = rDao.deleteReimbursement(reimb);
		if(success) {
			System.out.println("Reimbursement was successfully deleted from the database!");
		} else {
			System.out.println("Failed to remove reimbursement from the database.");
		}
		assertTrue(success);
	}
	
}
