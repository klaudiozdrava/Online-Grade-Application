package Mainpackage;

public class Professors extends Users {
	
	
	
	private final int id;
	private String about;
	private String achievements;
	
	public Professors(String username, String name, String surname, String department,String capacity,String password,
			int id,String about, String achievs) {
		
		super(username, name, surname, department,capacity,password);
		this.id = id;
		this.about=about;
		this.achievements=achievs;
		
	}
	
	public Professors(String name, String surname, int id, String about, String achievements,String department) {
		super(name,surname,department);
		this.id=id;
		this.about=about;
		this.achievements=achievements;
	}
	
	public Professors(int id) {
		super(id);
		this.id=id;
	}
	
	
	 public int getId() {
		return id;
	}


	 public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public String getAchievements() {
		return achievements;
	}


	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	

	@Override
	public String toString() {
		return "Professors [id=" + id + ", about=" + about + ", achievements=" + achievements + ", getPassword()="
				+ getPassword() + ", getUsername()=" + getUsername() + ", getName()=" + getName() + ", getSurname()="
				+ getSurname() + ", getDepartment()=" + getDepartment() + ", getCapacity()=" + getCapacity() + "]";
	}
	 
	 

}
