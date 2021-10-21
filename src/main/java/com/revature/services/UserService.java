package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class UserService {

	private UserDao uDao;
	
	public UserService(UserDao dao) {
		this.uDao = dao;
	}
	
	public User signIn(String username, String password) {
		User u = uDao.getUserByUsername(username);
		if(u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
	
	public User viewAccountInfo(User user) {
		User u = uDao.getUserById(user.getId());
		return u;
	}
	
	public User updateAccountInfo(User user) {
		if(uDao.updateUser(user)) {
			return user;
		}
		return null;
	}
}
