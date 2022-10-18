package Servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Mainpackage.Courses;
import Mainpackage.Grades;
import Mainpackage.SecretariesController;
import Mainpackage.StudentController;
import Mainpackage.Students;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	StudentController studCont;
	Set<String> UniqueSemesters ;
	boolean FirstTime=false;
	
	public void init(ServletConfig config) throws ServletException {
		studCont = new StudentController();
		
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		switch(action) {
			case "/Semester":
				String query=request.getQueryString();
				int index=query.indexOf( '=' );
				String semester =query.substring(index+1, query.length());
				ShowGradesBasedOnSemester(request,response,semester);
				break;
			case "/LogoutStudent":
				HttpSession session = request.getSession();
				session.removeAttribute("user");
				response.sendRedirect("index.html");
				break;
			default:
				GetGrades(request,response);
				break;
		}
	}
	private void ShowGradesBasedOnSemester(HttpServletRequest request, HttpServletResponse response,String semester) throws ServletException, IOException {
		
			try {
				studCont.BasedOnSemester(semester);
				List<Grades> StudentGrades=studCont.getGradesBasedOnSemester();
				List<Courses> StudentCourses=studCont.getCoursesBasedOnSemester();
				UniqueSemesters=studCont.getUniqueSemesters();
				request.setAttribute("semesters", UniqueSemesters);
				request.setAttribute("studC", StudentCourses);
	            request.setAttribute("grades", StudentGrades);
	            jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("StudentHome.jsp");
	            rd.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private void GetGrades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Students student =(Students) session.getAttribute("user");
			try {
				
				studCont.RetrieveGrades(student.getRegistrationNumber());
				List<Grades> StudentGrades=studCont.getAllGrades();
				List<Courses> StudentCourses=studCont.getAllCourses();
				UniqueSemesters=studCont.getUniqueSemesters();
				request.setAttribute("semesters", UniqueSemesters);
				request.setAttribute("studC", StudentCourses);
	            request.setAttribute("grades", StudentGrades);
	    
	            jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("StudentHome.jsp");
	            rd.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
