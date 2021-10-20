package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
	}
	
}
