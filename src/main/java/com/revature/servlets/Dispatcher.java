package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.SubmitReimbController;
import com.revature.controllers.UpdateAccountController;
import com.revature.controllers.ViewAccountController;
import com.revature.controllers.ViewPendingController;
import com.revature.controllers.ViewResolvedController;

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
		case "/Project001/api/mypending":
			ViewPendingController.viewPendingRequests(req, res);
			break;
		case "/Project001/api/myresolved":
			ViewResolvedController.viewResolvedRequests(req, res);
			break;
		case "/Project001/api/viewacct":
			ViewAccountController.viewAccount(req, res);
			break;
		case "/Project001/api/updateacct":
			UpdateAccountController.updateAccount(req, res);
			break;
		}
	}
	
}
