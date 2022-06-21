package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {

	//Employeeservice
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	//object mapper (for front end)
	private static ObjectMapper om = new ObjectMapper();
	
	//What does this method do?
	//It extracts the parameters from a request (username and password) from the UI
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee e = eserv.confirmLogin(username, password);
		
		if (e.getId() > 0) {
			
			//grab the session
			HttpSession session = request.getSession();
			
			// add the user to the session
			
			session.setAttribute("the-user", e);
			
			// print out the users data with the print writer
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome" + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in!</h3>");
			
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
			
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			//response.setStatus(204);
			
		}
		
		
		
	}
	
}
