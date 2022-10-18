package Servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;


import Mainpackage.UserController;
import Mainpackage.Users;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserController ucont;
	public void init(ServletConfig config) throws ServletException {
		ucont = new UserController();
	}
	//Post method to check if user exist and what properties have
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("uname");
		String password= request.getParameter("pass");
		
		try {
			Object user=ucont.findUser(username, password);
			if(user!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);//store user session
				String capacity=((Users) user).getCapacity();
			
				switch(capacity) {
					case "Secretary":
						response.sendRedirect("SecretaryPage");//response with Secretary servlet
						break;
					case "Student":
						response.sendRedirect("StudentServlet");
						break;
					case "Professor":
						response.sendRedirect("ProfessorServlet");
						break;
				}
			}else {
				response.sendRedirect("index.html");
				
			}
			
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	
	


}
