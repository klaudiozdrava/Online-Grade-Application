package Mainpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecretariesController {
	
	CreateUsers cs= new CreateUsers();
	
	//Important queries to retrieve or insert data from database
	private static final String INSERT_USERS="INSERT INTO users" + " (name,surname,username,department,capacity,password,salts) VALUES "
			+ " (?, ?, ?, ? , ?, ?,?); ";
	
	private static final String SELECT_LAST_USER="SELECT usersCounter FROM users ORDER BY usersCounter desc limit 1 ;";
	
	private static final String INSERT_STUDENTS="INSERT INTO students" + " (registration_number,userID) VALUES "
			 + " (?, ? ); ";
	
	private static final String DELETE_STUDENT="DELETE * FROM students WHERE registration_number = ? ;";
	
	private static final String INSERT_PROFESSORS="INSERT INTO professors" + " (professor_id,about,achievements,users_id) VALUES "
			+ " (? , ? , ? , ?) ";
	
	private static final String INSERT_COURSE= "INSERT INTO courses " + "(id,name,project_factor,test_factor,exam_factor,direction,department,professor_id,"
			+ "about,books,semester) VALUES (? , ? , ? ,? , ? , ? , ? , ? , ? , ? , ? ); ";
	
	
	private static final String SELECT_COURSES="SELECT * FROM courses;";
	
	private static final String SELECT_PROFESSOR="SELECT professor_id,about,achievements,name,surname,department FROM professors INNER JOIN users "
			+ "ON professors.users_id=users.usersCounter WHERE professor_id = ? ;";
	
	private static String ALL_PROFESSORS="SELECT professor_id,about,achievements,name,surname,department FROM professors INNER JOIN users "
			+ "ON professors.users_id=users.usersCounter ;";
	
	private static String ASSIGN_COURSE="UPDATE courses SET professor_id = ? WHERE id = ?;";
	
	private static String SELECT_STUDENT="SELECT * from students where registration_number = ?; ";
	
	private static String SELECT_USER="SELECT name FROM users where username = ?;";
	
	private static String FIND_COURSE="SELECT id FROM courses WHERE id = ?;";
	
	private List<Professors> professors= new ArrayList<>();//ArrayList to store all the professors that teaching courses
	
	//Insert professor to database
	public void insertProfessors(Professors professor,String salt) throws SQLException {
		
		int LastUserId;
		
		
		try(Connection connection = cs.getConnection(); //Make a connection with database
				PreparedStatement preparedStatementtoInsertUsers = connection.prepareStatement(INSERT_USERS);
				PreparedStatement InsertProfessor=connection.prepareStatement(INSERT_PROFESSORS)) {
			
			preparedStatementtoInsertUsers.setString(1,professor.getName());
			preparedStatementtoInsertUsers.setString(2,professor.getSurname());
			preparedStatementtoInsertUsers.setString(3,professor.getUsername());
			preparedStatementtoInsertUsers.setString(4,professor.getDepartment());
			preparedStatementtoInsertUsers.setString(5,professor.getCapacity());
			preparedStatementtoInsertUsers.setString(6,professor.getPassword());
			preparedStatementtoInsertUsers.setString(7,salt);
			preparedStatementtoInsertUsers.executeUpdate();
			
			//Get the last user id as foreign key to professor table
			try (Statement stmt = connection.createStatement()) {
				 ResultSet rs = stmt.executeQuery(SELECT_LAST_USER);
				 while(rs.next()) {
					 LastUserId=rs.getInt("usersCounter");
					 InsertProfessor.setInt(1, professor.getId());
					 InsertProfessor.setString(2, professor.getAbout());
					 InsertProfessor.setString(3,professor.getAchievements());
					 InsertProfessor.setInt(4, LastUserId);
					 InsertProfessor.executeUpdate();
				 }
			}	

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertStudent(Students student,String salt) throws SQLException {
		int LastUser;
		try(Connection connection = cs.getConnection(); 
				PreparedStatement preparedStatementtoInsertUsers = connection.prepareStatement(INSERT_USERS);
				PreparedStatement InsertStud=connection.prepareStatement(INSERT_STUDENTS)){
			
			preparedStatementtoInsertUsers.setString(1,student.getName());
			preparedStatementtoInsertUsers.setString(2,student.getSurname());
			preparedStatementtoInsertUsers.setString(3,student.getUsername());
			preparedStatementtoInsertUsers.setString(4,student.getDepartment());
			preparedStatementtoInsertUsers.setString(5,student.getCapacity());
			preparedStatementtoInsertUsers.setString(6,student.getPassword());
			preparedStatementtoInsertUsers.setString(7, salt);
			preparedStatementtoInsertUsers.executeUpdate();
			
			try (Statement stmt = connection.createStatement()) {
				 ResultSet rs = stmt.executeQuery(SELECT_LAST_USER);
				 while(rs.next()){
					 LastUser = rs.getInt("usersCounter");
					 InsertStud.setInt(1, student.getRegistrationNumber());
					 InsertStud.setInt(2, LastUser);
					 InsertStud.executeUpdate();
				 }
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Return professor based on id
	public Professors FindProfessor(int id) throws SQLException {
		
		Professors prof = null;
		try(Connection conn = cs.getConnection();  
			PreparedStatement findProf = conn.prepareStatement(SELECT_PROFESSOR);	) {
			
			findProf.setInt(1,id);
			ResultSet rs = findProf.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt("professor_id");
				String prof_about = rs.getString("about");
				String achiev =  rs.getString("achievements");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String dept =rs.getString("department");
				prof = new Professors(name,surname,pid,prof_about,achiev,dept);
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return prof;
		
	}
	
	public boolean FindUser(String username) throws SQLException {
		try(Connection conn = cs.getConnection();  
				PreparedStatement user = conn.prepareStatement(SELECT_USER); ){
			
			user.setString(1, username);
			ResultSet rs = user.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}
	}
	
	public boolean FindCourse(String id) throws SQLException {
		try(Connection conn = cs.getConnection();  
				PreparedStatement course = conn.prepareStatement(FIND_COURSE);	) {
			
			course.setString(1, id);
			ResultSet rs = course.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}
	}
	
	public boolean FindStudent(int id) throws SQLException {
		try(Connection conn = cs.getConnection();  
				PreparedStatement student = conn.prepareStatement(SELECT_STUDENT);	) {
			
			student.setInt(1, id);
			ResultSet rs = student.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}
				
	}
	
	//Retrieve all courses from the database 
	public List<Courses> displayCourses() throws SQLException {
		
		List<Courses> courses= new ArrayList<>();
		try (Connection connection = cs.getConnection();
			PreparedStatement allCourses = connection.prepareStatement(SELECT_COURSES); ){
			
			ResultSet results= allCourses.executeQuery();
			
			while(results.next()) {
				String id = results.getString("id");
				String name = results.getString("name");
				double projectFactor=results.getDouble("project_factor");
				double testFactor=results.getDouble("test_factor");
				double examfactor=results.getDouble("exam_factor");
				String direction = results.getString("direction");
				String department =results.getString("department");
				
				int idProf = results.getInt("professor_id");
				String semester =results.getString("semester");
				String about = results.getString("about");
				String books = results.getString("books");
				if(about==null) {about=" ";}
				if(books==null) {books=" ";}
				if(semester==null) {semester=" ";}
				courses.add(new Courses(id,name,projectFactor,testFactor,examfactor,direction,department,about,books,idProf,semester));
    			professors.add(FindProfessor(idProf));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public void deleteStudent(int id) throws SQLException {
		
		try(Connection con = cs.getConnection();
				PreparedStatement deleteS = con.prepareStatement(DELETE_STUDENT);){
			
			deleteS.setInt(1, id);
			deleteS.executeUpdate();
		}
	}
	
	
	public void insertCourses(Courses course) throws SQLException {
		
		try(Connection connection = cs.getConnection(); 
				PreparedStatement insertCourse = connection.prepareStatement(INSERT_COURSE);) {
			
			insertCourse.setString(1, course.getCid());
			insertCourse.setString(2, course.getName());
			insertCourse.setDouble(3, course.getProjectFactor());
			insertCourse.setDouble(4, course.getTestFactor());
			insertCourse.setDouble(5, course.getExamFactor());
			insertCourse.setString(6, course.getDirection());
			insertCourse.setString(7, course.getDepartment());
			insertCourse.setInt(8, course.getProfessorId());
			insertCourse.setString(9, course.getAbout());
			insertCourse.setString(10, course.getBooks());
			insertCourse.setString(11, course.getSemester());
			insertCourse.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void AssignCourse(String id,Professors pf) throws SQLException{
		
		try(Connection connection = cs.getConnection(); 
				PreparedStatement updateCourse = connection.prepareStatement(ASSIGN_COURSE);){
			
			updateCourse.setInt(1, pf.getId());
			updateCourse.setString(2, id);
			
			updateCourse.executeUpdate();
			professors.clear();
			
		}
		
	}
	
	//Retrieve all professors from table professors
	public List<Professors> AllProfessors() throws SQLException {
		
		List<Professors> AllProfessors= new ArrayList<>();
		try (Connection connection = cs.getConnection();
			PreparedStatement allCourses = connection.prepareStatement(ALL_PROFESSORS); ){
			
			ResultSet results= allCourses.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("professor_id");
				String about = results.getString("about");
				String achievements = results.getString("achievements");
				String name =results.getString("name");
				String surname =results.getString("surname");
				String department = results.getString("department");

				AllProfessors.add(new Professors(name,surname,id,about,achievements,department));
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return AllProfessors;
	}
	
	

	public List<Professors> getProfessors() {
		return professors;
	}
	
	

}
