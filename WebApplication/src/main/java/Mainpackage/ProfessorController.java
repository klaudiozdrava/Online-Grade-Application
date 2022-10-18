package Mainpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
	
	
	CreateUsers database= new CreateUsers();
	List<Students> students;
	List<Courses> courses;
	List<Grades> grades;
	
	private static String GET_PROFESSOR_COURSES="SELECT id,name,department,semester FROM courses WHERE professor_id = ? ; ";
	
	private static String GET_STUDENTS_GRADES="SELECT finalgrade,examgrade,projectgrade,testgrade,courses.name AS csname,"
			+ "courses.department AS csdepartment,\r\n"
			+ "semester,registration_number,users.name user_name,surname\r\n"
			+ " FROM (((grades INNER JOIN courses ON grades.courseId=courses.id)\r\n"
			+ " INNER JOIN students ON grades.stud_id=students.registration_number)\r\n"
			+ " INNER JOIN users ON students.userID=users.usersCounter)\r\n"
			+ " WHERE  courses.id = ? ;" ;	
	
	private static String  GET_STUDENTS_IDS="SELECT registration_number from students;";
	
	private static String GET_COURSE="SELECT project_factor,test_factor,exam_factor FROM courses WHERE id = ? ;";
	
	private static final String INSERT_GRADES="INSERT INTO grades" + " (finalgrade,examgrade,projectgrade,testgrade,stud_id,courseId) VALUES "
			+ " (?, ?, ?, ? , ?, ?); ";
	
	
	public List<Courses> GetProfessorCourses(int ProfessorId) throws SQLException {
		
		List<Courses> ProfessorCourses = new ArrayList<>();
		
		try (Connection connection = database.getConnection();
				PreparedStatement Professor_Courses = connection.prepareStatement(GET_PROFESSOR_COURSES); ){
				Professor_Courses.setInt(1,ProfessorId);
				ResultSet results= Professor_Courses.executeQuery();
				
				while(results.next()) {
					
					String id = results.getString("id");
					String name = results.getString("name");
					String department = results.getString("department");
					String semester = results.getString("semester");
					ProfessorCourses.add(new Courses(name,department,semester,id));
				}
				
				return ProfessorCourses;
		}
	}
	
	public double[] GetCourseFactors(String id) throws SQLException {
		double courseFactors[] = new double[3];
		try (Connection connection = database.getConnection();
				PreparedStatement Course = connection.prepareStatement(GET_COURSE); ){
				Course.setString(1,id);
				ResultSet results= Course.executeQuery();
				
				while(results.next()) {
					
					double test=results.getDouble("test_factor");
					double project=results.getDouble("project_factor");
					double exam=results.getDouble("exam_factor");
					courseFactors[0]=test;
					courseFactors[1]=project;
					courseFactors[2]=exam;
				}
			return courseFactors;
		}
	}
	
	public void InserGrades(double test,double project,double exam,String Courseid,int StudentId) throws SQLException {
		double[] factors=GetCourseFactors(Courseid);
		test=test*factors[0];
		project=project*factors[1];
		exam=exam*factors[2];
		int Final = (int) Math.round(test+project+exam);
		try (Connection connection = database.getConnection();
				PreparedStatement assign = connection.prepareStatement(INSERT_GRADES); ){
			assign.setInt(1,Final);
			assign.setDouble(2, exam);
			assign.setDouble(3, project);
			assign.setDouble(4, test);
			assign.setInt(5, StudentId);
			assign.setString(6, Courseid);
			assign.executeUpdate();
		}
		
	}

	
	public void GetStudentsGrades(String course) throws SQLException {
		
		students = new ArrayList<>();
		grades = new ArrayList<>();
		courses = new ArrayList<>();
		
		try (Connection connection = database.getConnection();
				PreparedStatement Students_Grades = connection.prepareStatement(GET_STUDENTS_GRADES); ){
				Students_Grades.setString(1,course);
				ResultSet results= Students_Grades.executeQuery();
				
				while(results.next()) {
					
					ArrayList<Object> temp = new ArrayList<>(); 
					
					int Grade = results.getInt("finalgrade");
					double Exam= results.getDouble("examgrade");
					double Project = results.getDouble("projectgrade");
					double Test = results.getDouble("testgrade");
					String Course =results.getString("csname");
					String department =results.getString("csdepartment");
					String semester =results.getString("semester");
					String name =results.getString("user_name");
					String surname =results.getString("surname");
					int id = results.getInt("registration_number");
					
					students.add(new Students(id,name,surname));
					grades.add(new Grades(Grade,Exam,Project,Test));
					courses.add(new Courses(Course,department,semester,course));
					
				}
		}
		
	}
	
	public List<Integer> GetStudentsIds() throws SQLException{
		
		List<Integer> ids = new ArrayList<>();
		try (Connection connection = database.getConnection();
				PreparedStatement studsIds = connection.prepareStatement(GET_STUDENTS_IDS); ){
			ResultSet results= studsIds.executeQuery();
			
			while(results.next()) {
				ids.add(results.getInt("registration_number"));
			}
			
			return ids;
			
		}
	}



	public List<Students> getStudents() {
		return students;
	}



	public List<Courses> getCourses() {
		return courses;
	}



	public List<Grades> getGrades() {
		return grades;
	}

}
