import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;

public class UserDaoTest {

	private static UserDao uDao;
	
	@BeforeClass
	public static void testSetUp() {
		uDao = new UserDaoDB();
	}
	
	@Before
	public void testDelim() {
		System.out.println("----------");
	}
	
	@Test
	public void createUserTest() {
		User odd = new User("ODRobbia", "Kiwi", "Odd", "Della Robbia", "odellarobbia@mail.com", 1);
		boolean success = false;
		success = uDao.createUser(odd);
		if(success) {
			System.out.println("User: " + odd.getFirstName() + " " 
					+ odd.getLastName() + " was successfully added to the database!");
		} else {
			System.out.println("Failed to add user to the database");
		}
		assertTrue(success);
	}
	
	@Test
	public void getAllUsersTest() {
		List<User> users = uDao.getAllUsers();
		System.out.println("Results of getAllUsers:");
		for(User u : users) {
			System.out.println(u);
		}
		assertFalse(users.size() == 1);
	}
	
	@Test
	public void getUserByIdTest() {
		User jeremy = new User(1, "JBelpois", "einstein", "Jeremy", "Belpois", "jbelpois@mail.com", 2);
		User ret = uDao.getUserById(1);
		boolean success = (jeremy.getUsername().equals(ret.getUsername()));
		System.out.println("Result of getUserById: " + ret);
		assertTrue(success);
	}
	
	@Test
	public void getUserByUsernameTest() {
		User jeremy = new User(1, "JBelpois", "einstein", "Jeremy", "Belpois", "jbelpois@mail.com", 2);
		User ret = uDao.getUserByUsername("JBelpois");
		boolean success = (jeremy.getId() == ret.getId());
		System.out.println("Result of getUserByUsername: " + ret);
		assertTrue(success);
	}
	
	@Test
	public void getUserByEmailTest() {
		User jeremy = new User(1, "JBelpois", "einstein", "Jeremy", "Belpois", "jbelpois@mail.com", 2);
		User ret = uDao.getUserByEmail("jbelpois@mail.com");
		boolean success = (jeremy.getId() == ret.getId());
		System.out.println("Result of getUserByEmail: " + ret);
		assertTrue(success);
	}
	
	@Test
	public void updateUserTest() {
		User aelita = uDao.getUserByEmail("astones@mail.com");
		String initUsername = aelita.getUsername();
		aelita.setUsername("Angel");
		if(uDao.updateUser(aelita)) {
			System.out.println("Initial Username: " + initUsername);
			System.out.println("New Username: " + aelita.getUsername());
		} else {
			System.out.println("Failed to update user in the database.");
		}
		aelita = uDao.getUserByEmail("astones@mail.com");
		assertFalse(aelita.getUsername().equals(initUsername));
	}
	
	@Test
	public void deleteUserTest() {
		User william = uDao.getUserByEmail("wdunbar@mail.com");
		boolean success = uDao.deleteUser(william);
		if (success) {
			System.out.println("User: " + william.getFirstName() + " " + william.getLastName() 
				+ " was successfully deleted from the database!");
		} else {
			System.out.println("Failed to delete user from the database.");
		}
		assertTrue(success);
		
	}
	
}
