package Servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;

import Mainpackage.Courses;
import Mainpackage.Professors;
import Mainpackage.SecretariesController;
import Mainpackage.Students;
import Mainpackage.Users;


public class Secretary extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    SecretariesController secCont;
    Users userb;
    Professors professor;
    Students student;
    int professor_id;
    private String id_Course;
    
	public void init(ServletConfig config) throws ServletException {
		
		secCont = new SecretariesController();	
	}

	//Manage users requests like creating professor , assigning  course to a professor etc
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionPage = request.getServletPath();
		
		switch(actionPage) {
			case "/Assign":
				String query=request.getQueryString();
				int index=query.indexOf( '=' );
				this.id_Course=query.substring(index+1, query.length());
				Assign(request,response);
				break;
			case "/NewProfessor":
				 response.sendRedirect("newProfessor.jsp");
				break;
			case "/CreateProfessor":
				try {
					ProfessorRegistration(request,response);
				} catch (SQLException | NoSuchAlgorithmException e) {
					e.printStackTrace();
				}finally {
					AllCourses(request,response);
				}
				break;
			case "/NewStudent":
				response.sendRedirect("NewStudent.jsp");
				break;
			case "/CreateStudent":
				try {
					StudentRegistration(request,response);
				} catch (SQLException | NoSuchAlgorithmException e) {
					e.printStackTrace();
				}finally {
					AllCourses(request,response);
				}break;
			case "/NewCourse":
				response.sendRedirect("NewCourse.jsp");
				break;
			case "/CreateCourse":
				try {
					CourseRegistration(request,response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					AllCourses(request,response);
				}
				break;
			case "/Logout":
				HttpSession session = request.getSession();
				session.removeAttribute("user");
				response.sendRedirect("index.html");
				break;
			case "/Assigned":
				String query2=request.getQueryString();
				int index2=query2.indexOf( '=' );
				this.professor_id=Integer.parseInt(query2.substring(index2+1, query2.length()));
				Assigned(request,response);
				break;
			default:
				AllCourses(request,response);
			    break;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		doGet(request, response);
	}
	
	//Retrieve all courses and professors that are associated with these courses from database
	private void AllCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				List<Courses> allcourses=secCont.displayCourses();
				List<Professors> all_professors_that_have_course=secCont.getProfessors();
				request.setAttribute("courses", allcourses);
	            request.setAttribute("professors", all_professors_that_have_course);
	           
	            jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	            rd.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	//Retrieve all professors from database and display the assign page 
	private void Assign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    try {
				 List<Professors> all_profs=secCont.AllProfessors();
				 request.setAttribute("all_professors", all_profs);
				 jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("assign.jsp");
		         rd.forward(request, response);
		    }catch (Exception e) {
		    	e.printStackTrace();
	  }
	}
	//Asssign the course to professor	
	private void Assigned(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    try {
		    	  Professors professor=secCont.FindProfessor(professor_id);
				  secCont.AssignCourse(id_Course, professor); 		
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				AllCourses(request,response);
			}
		
	}
	//Create Professor to database
	private void ProfessorRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException{
		
		int id =Integer.parseInt(request.getParameter("id"));
		String name =request.getParameter("name");
		String surname=request.getParameter("surname");
		String username=request.getParameter("username");
		
		if(secCont.FindUser(username) || secCont.FindProfessor(id)!=null) {
			response.sendRedirect("newProfessor.jsp");
		}
		String department = request.getParameter("department");
		String about = request.getParameter("about");
		String achieves = request.getParameter("achievements");
		String salt = getSalt();//Generate salt for hash password
		String password =get_SHA_256_SecurePassword(request.getParameter("password"),salt);
		
		professor = new Professors(username,name,surname,department,"Professor",password,id,about,achieves);
		secCont.insertProfessors(professor,salt);	
	}
	//Create student to database
	private void StudentRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException{
		
		int id =Integer.parseInt(request.getParameter("id"));
		String name =request.getParameter("name");
		String surname=request.getParameter("surname");
		String username=request.getParameter("username");
		String department = request.getParameter("department");

		if(secCont.FindUser(username) || secCont.FindStudent(id)) {
			response.sendRedirect("NewStudent.jsp");
		}
		String salt = getSalt();
		String password =get_SHA_256_SecurePassword(request.getParameter("password"),salt);
		student = new Students(username,name,surname,department,id,
			"Student",password);
		
        secCont.insertStudent(student,salt);
	}
 
	private void CourseRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		
		String id =request.getParameter("id");
		if(secCont.FindCourse(id)) {
			response.sendRedirect("NewCourse.jsp");
		}
		String name =request.getParameter("name");
		double test=Double.parseDouble(request.getParameter("test_factor"));
		double exam=Double.parseDouble(request.getParameter("exam_factor"));
		double project=Double.parseDouble(request.getParameter("project_factor"));
		String direction =request.getParameter("direction");
		String department =request.getParameter("department");
		String semester =request.getParameter("semester");
		String about =request.getParameter("about");
		String books = request.getParameter("books");
		test=test/100;
		project=project/100;
		exam=exam/100;
		secCont.insertCourses(new Courses(id,name, project,test,exam,
			direction,department ,about,books ,13,semester));

	}
	
	private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
	 private static String getSalt() throws NoSuchAlgorithmException {
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt.toString();
	    }


	

}
