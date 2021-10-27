package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.FindAuthorController;
import com.revature.controllers.FindResolverController;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.ResolveReimbursementController;
import com.revature.controllers.SubmitReimbController;
import com.revature.controllers.UpdateAccountController;
import com.revature.controllers.ViewAccountController;
import com.revature.controllers.ViewAllPendingController;
import com.revature.controllers.ViewAllResolvedController;
import com.revature.controllers.ViewEmployeesController;
import com.revature.controllers.ViewPendingController;
import com.revature.controllers.ViewResolvedController;
import com.revature.controllers.ViewUserReimbsController;

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
		case "/Project001/api/resolve":
			ResolveReimbursementController.resolveReimbursement(req, res);
			break;
		case "/Project001/api/allpending":
			ViewAllPendingController.viewAllPendingRequests(req, res);
			break;
		case "/Project001/api/allresolved":
			ViewAllResolvedController.viewAllResolvedRequests(req, res);
			break;
		case "/Project001/api/userrequests":
			ViewUserReimbsController.viewUserRequests(req, res);
			break;
		case "/Project001/api/employees":
			ViewEmployeesController.viewAllEmployees(req, res);
			break;
		case "/Project001/api/author":
			FindAuthorController.retrieveUsername(req, res);
			break;
		case "/Project001/api/resolver":
			FindResolverController.retrieveUsername(req, res);
			break;
		}
	}
	
}
