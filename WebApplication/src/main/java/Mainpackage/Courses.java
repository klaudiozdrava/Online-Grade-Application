package Mainpackage;

public  class Courses {

	private String cid;//Κωδικος μαθηματος
	private String name;//Ονομα μαθήματος
	private double projectFactor; //Συντελεστής βαθμού της τελικης εργασιας
	private double testFactor;//συντελεστης βαθμου μικρων εργασιων
	private double examFactor;//συντελεστης βαθμου της γραπτης εξετασης
	private String direction;//κατευθηνση του μαθηματος
    private String semester;
	private String department;
	private String about;
	private String books;
	private int professorId;
	
	public Courses(String id,String name, double projectFactor,double testFactor,double examFactor,
			String direction,String department ,String About,String books ,int Pid,String semester) {
		
			this.projectFactor=projectFactor;
			this.testFactor=testFactor;
			this.examFactor=examFactor;
			this.cid=id;
			this.name=name;
			this.direction=direction;
			this.department=department;
			this.about=About;
			this.books=books;
			this.professorId=Pid;
			this.semester=semester;	
	}
	
	public Courses(String name,String semester) {
		this.name=name;
		this.semester=semester;
	}
	
	public Courses(String name,String department,String semester,String Id) {
		this.name=name;
		this.department=department;
		this.cid=Id;
		this.semester=semester;
	}
	
	public String getSemester() {
		return semester;
	}


	public String getDepartment() {
		return department;
	}


	public String getAbout() {
		return about;
	}


	public String getBooks() {
		return books;
	}


	public int getProfessorId() {
		return professorId;
	}


	//Get id
	

	//get name
	public String getName() {
		return name;
	}

	public String getCid() {
		return cid;
	}


	//get projectFactor
	public double getProjectFactor() {
		return projectFactor;
	}


	public double getTestFactor() {
		return testFactor;
	}


	public double getExamFactor() {
		return examFactor;
	}


	public String getDirection() {
		return direction;
	}


	//Ελέγχει έαν οι συντελεστες έχουν άθροισμα 1
	private void CalculateFactors(double projectFactor,double testFactor,double examFactor) {
		if((projectFactor+testFactor+examFactor)!=1) {
			 throw new ArithmeticException();
		}
	}
	
	
	
	
	
}
