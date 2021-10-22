package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.SubmitReimbController;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Project001/api/login":
			LoginController.login(req, res);
			break;
		case "/Project001/api/logout":
			LogoutController.logout(req, res);
			break;
		case "/Project001/api/request":
			SubmitReimbController.submitRequest(req, res);
			break;
		}
	}
	
}
