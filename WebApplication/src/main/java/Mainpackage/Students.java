package Mainpackage;

public class Students extends Users {
	
	
	private final int registrationNumber;
	
	

	public Students(String username,String name,String surname,String department, int registrationNumber,
			String capacity,String password) {
		super(username,name,surname,department,capacity,password);
		this.registrationNumber=registrationNumber;
		
		}
	

	public Students(int id,String name, String surname) {
		super(name,surname);
		this.registrationNumber=id;
	}
	
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}





	//Εμφανιζει τα μαθηματα του φοιτητη με τις βαθμολογιες
	public void GradesShow() {
		System.out.println("Below are courses and grades of Student "+super.getName()+" "+super.getSurname());
	}
	
	
	
}
