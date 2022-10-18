package Mainpackage;

public  class Secretaries extends Users {

	
	
	public Secretaries(String username, String name, String surname, String department,String capacity,String password) {
		super(username, name, surname, department,capacity,password);
		
	}
	public void CreateStudents(){
		//Δημιουργει εγγραφες φοιτητων
		System.out.println("Student registration created successfully");
	}
	public void CreateProfessors(){
		//Δημιουργει εγγραφες καθηγητων
		System.out.println("Professor registration created successfully");

	}
	public void CreateCourses(){
		//Δημιουργει εγγραφες μαθηματων
		System.out.println("Course registration created successfully");

	}
	public void ProfessorsAssignedToCourses(){
		//Αναθετει καθηγητες σε μαθηματα
		System.out.println("Professor assigned to course successfully");

	}
	public void StudentsGradeLists(){
		//Δημιουργεί τις σχετικές λίστες των φοιτητών προς βαθμολόγηση
		System.out.println("Grade lists created successfully");
	}

}
