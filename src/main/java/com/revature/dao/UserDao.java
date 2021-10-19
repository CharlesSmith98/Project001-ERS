package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	// (C) Create
	boolean createUser(User user);
	
	// (R) Read
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByEmail(String userEmail);
	User getUserByUsername(String username);
	
	// (U) Update
	boolean updateUser(User user);
	
	// (D) Delete
	boolean deleteUser(User user);
	
}
