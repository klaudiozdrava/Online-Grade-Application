package Mainpackage;

public  class Users {
	
	private String username;
	private String name;
	private String surname;
	private String department;
	private String capacity;
	private String password;

	public static int usersCounter=0;
	
	public Users(String username,String name,String surname,String department,String capacity,String password) {
		
			this.username=username;
			this.name=name;
			this.department=department;
			this.surname=surname;
			this.capacity=capacity;
			this.password=password;
			usersCounter++;
		
	}
	
	public Users(String name,String surname) {
		this.name=name;
		this.surname=surname;
	}

	public Users(String nm,String sm,String dp) {
		this.name=nm;
		this.surname=sm;
		this.department=dp;
	}
	
	public Users(int id) {
		
	}
	
	
	
	
	
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getDepartment() {
		return department;
	}

	public String getCapacity() {
		return capacity;
	}

	 
	
	

}
