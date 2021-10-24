package com.revature.tests.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {
	
	@InjectMocks
	public UserService uServ;
	
	@Mock
	public UserDao uDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void testDelim() {
		System.out.println("----------");
	}
	
	@Test
	public void signInTest() {
		User ulrich = new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(ulrich);
		
		User ret = uServ.signIn("UStern", "katana");
		
		if(ret.getLastName().equals(ulrich.getLastName())) {
			System.out.println("User logged in successfully: " + ulrich.getFirstName() + " " + ulrich.getLastName());
		}
		assertTrue(ret.getLastName().equals(ulrich.getLastName()));
	}
	
	@Test
	public void viewAccountInfoTest() {
		User ulrich = new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1);
		
		when(uDao.getUserById(anyInt())).thenReturn(ulrich);
		
		User ret = uServ.viewAccountInfo(ulrich.getId());
		
		System.out.println(ret);
		assertTrue(ulrich.getPassword().equals(ret.getPassword()));
	}
	
	@Test
	public void viewAllEmployeesTest() {
		List<User> employees = new ArrayList<>();
		employees.add(new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1));
		
		when(uDao.getAllEmployees()).thenReturn(employees);
		System.out.println("Results of viewAllEmployees");
		
		List<User> expectedUsers = uServ.viewAllEmployees();
		for (User u : expectedUsers) {
			System.out.println(u);
		}
		assertFalse(expectedUsers.size() == 0);
	}
	
	@Test
	public void updateAccountInfoTest() {
		User ulrich = new User(2, "UStern", "katana", "Ulrich", "Stern", "ustern@mail.com", 1);
		
		System.out.println(ulrich.getFirstName() + "'s old password: " + ulrich.getPassword());
		
		ulrich.setPassword("iloveyumi");
		when(uDao.updateUser((User)any(User.class))).thenReturn(true);
		
		User ret = uServ.updateAccountInfo(ulrich);
		
		System.out.println(ulrich.getFirstName() + "'s new password: " + ulrich.getPassword());
		assertTrue(ulrich.getPassword().equals(ret.getPassword()));
	}
	
}
