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
import java.util.ArrayList;
import java.util.List;

import Mainpackage.Courses;
import Mainpackage.Grades;
import Mainpackage.ProfessorController;
import Mainpackage.Professors;
import Mainpackage.Students;
import Mainpackage.UserController;
import Mainpackage.Users;


public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	ProfessorController pf ;
	List<Courses> courses;
	List<Integer> StudentsIds;
	String AssignId;
	
	public void init(ServletConfig config) throws ServletException {
		pf = new ProfessorController();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch(action) {
			case "/Course":
				String query=request.getQueryString();
				int index=query.indexOf( '=' );
				String CourseId =query.substring(index+1, query.length());
				try {
					GetGrades(request,response,CourseId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "/AssignGrade":
				String courseToAssigned=request.getQueryString();
				System.out.println(courseToAssigned);
				int indexCourse=courseToAssigned.indexOf( '=' );
			    AssignId =courseToAssigned.substring(indexCourse+1, courseToAssigned.length());
			    System.out.println(AssignId);
				try {
					AssignGrades(request,response,AssignId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "/Successfully":
				try {
					AssignGrades(request,response,AssignId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "/ProfessorLogout":
				HttpSession session = request.getSession();
				session.removeAttribute("user");
				response.sendRedirect("index.html");
				break;
			default:
				try {
					StudentsIds=pf.GetStudentsIds();
					defaultPage(request,response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
					break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void GetGrades(HttpServletRequest request, HttpServletResponse response,String Id) throws ServletException, IOException, SQLException {
		
		pf.GetStudentsGrades(Id);
		List<Students> studs =pf.getStudents();
		List<Courses> coursesData = pf.getCourses();
		List<Grades> grades = pf.getGrades();
		request.setAttribute("grades", grades);
		request.setAttribute("courseData", coursesData);
		request.setAttribute("students", studs);
		request.setAttribute("courses", courses);
		jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("ProfessorHome.jsp");
		rd.forward(request, response);


	}
	
	private void defaultPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		
		Professors professor =(Professors) session.getAttribute("user");
		int id = professor.getId();
		courses=pf.GetProfessorCourses(id);
		request.setAttribute("courses", courses);
		jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("ProfessorHome.jsp");
		rd.forward(request, response);
	}
	
	private void AssignGrades(HttpServletRequest request, HttpServletResponse response,String CourseId) throws ServletException, IOException, SQLException {
		
		request.setAttribute("Id", CourseId);
		request.setAttribute("StudentsIds",StudentsIds);
		
		int StId = 0;
		if (request.getParameter("StudentId")!=null) {
			StId=Integer.parseInt(request.getParameter("StudentId"));
		}
		if(StId!=0) {
			if(StudentsIds.indexOf(StId) == -1) {
				jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("ErrorAssignGrades.jsp");
				rd.forward(request, response);
			}
		}

		if(request.getParameter("TestGrade")==null) {
			request.setAttribute("courses2", courses);
			jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("AssignGrades.jsp");
			rd.forward(request, response);
		}
		else {
			double test = Double.parseDouble(request.getParameter("TestGrade"));
			double project = Double.parseDouble(request.getParameter("ProjectGrade"));
			double exam = Double.parseDouble(request.getParameter("ExamGrade"));
			pf.InserGrades(test, project, exam, CourseId, StId);
			jakarta.servlet.RequestDispatcher rd = request.getRequestDispatcher("AssignGrades.jsp");
			rd.forward(request, response);
		}
	}
	
	
}
