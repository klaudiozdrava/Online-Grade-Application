package Mainpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentController {
	
	CreateUsers cs= new CreateUsers();
	private List<Grades> AllGrades;
	private List<Courses> AllCourses ;
	private List<Courses> CoursesBasedOnSemester = new ArrayList<>();
	private List<Grades> GradesBasedOnSemester = new ArrayList<>();
	private Set<String> UniqueSemesters = new HashSet<String>();
	
	
	String GETAllGRADES="SELECT * FROM grades INNER JOIN courses ON grades.courseId=courses.id WHERE grades.stud_id = ?; ";
	
	public void RetrieveGrades(int Id) throws SQLException {
		AllGrades= new ArrayList<>();
		AllCourses = new ArrayList<>();
		try (Connection connection = cs.getConnection();
				PreparedStatement allgrades = connection.prepareStatement(GETAllGRADES); ){
				allgrades.setInt(1,Id);
				ResultSet results= allgrades.executeQuery();
				
				while(results.next()) {
					int FinalGrade=results.getInt("finalgrade");
					double ExamGrade=results.getDouble("examgrade");
					double ProjectGrade=results.getDouble("projectgrade");
					double TestGrade=results.getDouble("testgrade");
					
					String Name = results.getString("name");
					String Semester = results.getString("semester");
					UniqueSemesters.add(Semester);
					
					AllCourses.add(new Courses(Name,Semester));
					AllGrades.add(new Grades(FinalGrade,ExamGrade,ProjectGrade,TestGrade));
				}
		}
	}
	
	public void BasedOnSemester(String semester) throws SQLException {
		CoursesBasedOnSemester.clear();
		GradesBasedOnSemester.clear();
		
		for (int i = 0; i < AllCourses.size(); i++) {
			 	if(semester.equals(AllCourses.get(i).getSemester())) {
			 		CoursesBasedOnSemester.add(AllCourses.get(i));
			 		GradesBasedOnSemester.add(AllGrades.get(i));
			 	}
	      }
		
	}

	public List<Grades> getAllGrades() {
		return AllGrades;
	}

	public List<Courses> getAllCourses() {
		return AllCourses;
	}

	public List<Courses> getCoursesBasedOnSemester() {
		return CoursesBasedOnSemester;
	}

	public List<Grades> getGradesBasedOnSemester() {
		return GradesBasedOnSemester;
	}

	public Set<String> getUniqueSemesters() {
		return UniqueSemesters;
	}

}
