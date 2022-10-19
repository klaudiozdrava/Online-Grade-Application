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
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
			case "signup":
				try {
					SignUp(request,response);
				} catch (NoSuchAlgorithmException | ServletException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
					break;
			default:
				try {
					LogIn(username,password,request,response);
				} catch (NoSuchAlgorithmException | SQLException | IOException e) {
					e.printStackTrace();
				}
					break;
		}

		
	}
	
	public  void LogIn (String username,String password,HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, SQLException, IOException {
		Object user=ucont.findUser(username, password);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);//store user session
			String capacity=((Users) user).getCapacity();
		    if("Secretary".equals(capacity)){response.sendRedirect("SecretaryPage");}//response with Secretary servlet
		    else if("Student".equals(capacity)) {response.sendRedirect("StudentServlet");}
		    else if("Professor".equals(capacity)) {response.sendRedirect("ProfessorServlet");}
		}
		else {
			response.sendRedirect("index.html");
		}
	}
	
	public void SignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
		
		String name = request.getParameter("name");
		String surname =request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String department = request.getParameter("department");
		boolean Inserted=ucont.InsertSecretary(name, surname, username, password, department);
		if(Inserted) {response.sendRedirect("index.html");}
		else {response.sendRedirect("Signup.html");}
		
	}
	
	

}
