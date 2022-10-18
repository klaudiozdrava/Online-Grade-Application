package Mainpackage;

public  class Grades{
	
	private int finalgrade;
	private double examgrade;
	private double projectgrade;
	private double testgrade;
	
	
	public Grades(int FinalGrade,double examGrade,double projectGrade,double testGrade) {
		
		this.finalgrade=FinalGrade;
		this.examgrade=examGrade;
		this.projectgrade=projectGrade;
		this.testgrade=testGrade;
	}
	
	public double getExamgrade() {
		return examgrade;
	}

	public double getProjectgrade() {
		return projectgrade;
	}

	public double getTestgrade() {
		return testgrade;
	}

	public int getFinalgrade() {
		return finalgrade;
	}
	
	
   


	
}
