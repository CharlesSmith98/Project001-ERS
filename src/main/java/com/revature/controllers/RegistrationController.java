package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;

public class RegistrationController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uService = new UserService(uDao);
	
	public static void signUp(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(data);
		
		String username = node.get("username").asText();
		String password = node.get("password").asText();
		String firstName = node.get("name").asText();
		String lastName = node.get("surname").asText();
		String email = node.get("email").asText();
		String role = node.get("role").asText();
		
		int roleId = (role.equals("manager") ? 2 : 1);
		User user = new User(username, password, firstName, lastName, email, roleId);
		
		user = uService.registerUser(user);
		
		if(user != null) {
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		} else {
			//String[] result = new String[2];
			//result[0] = "message";
			//User temp = uDao.getUserByUsername(username);
			//System.out.println(temp);
			if(uDao.getUserByUsername(username).getId() != 0) {
				res.setStatus(409);
				System.out.println("Username");
				res.getWriter().write(new ObjectMapper().writeValueAsString("That Username is taken!!!"));
			} else if (uDao.getUserByEmail(email).getId() != 0) {
				res.setStatus(409);
				System.out.println("Email");
				res.getWriter().write(new ObjectMapper().writeValueAsString("That Email is already in use!!!"));
			} else {
				res.setStatus(409);
				res.getWriter().write(new ObjectMapper().writeValueAsString("Unknown SQL Error!!!"));
			}
		}
	}
	
}
