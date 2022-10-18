package Mainpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
	
	
	public CreateUsers cs= new CreateUsers();
	
	public int StudentId(int id) throws SQLException {
		int Id = 0;
		try (Connection conn = cs.getConnection();){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT registration_number FROM students WHERE userID = '" + id +"'; ");
			while(rs.next()) {
			   Id = rs.getInt("registration_number");
			}
		}
		return Id;
	}
	
	public int ProfessorId(int id)  throws SQLException {
		int Id = 0;
		try (Connection conn = cs.getConnection();){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT professor_id FROM professors WHERE users_id = '" + id +"'; ");
			while(rs.next()) {
			   Id = rs.getInt("professor_id");
			}
		}
		return Id;
	}
	
	public Object findUser(String uname,String upass) throws SQLException, NoSuchAlgorithmException {
		Users user= null;
		try (Connection conn = cs.getConnection();){
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + uname +"' ;");
			
			while (rs.next()) {
				String name =rs.getString("name");
				String surname=rs.getString("surname");
				String username=rs.getString("username");
				String department=rs.getString("department");
				String capacity=rs.getString("capacity");
				String password=rs.getString("password");
				String salts = rs.getString("salts");
				
				String hashedPassword=get_SHA_256_SecurePassword(upass,salts);
				if(!(hashedPassword.equals(password))) {
					return user;
				}
				int userId=rs.getInt("usersCounter");
				
				if(capacity.equals("Student")) {
					int studentid=StudentId(userId);
					Students student = new Students(username,name,surname,department, studentid ,
							capacity,password);
					return student;
				}
				else if(capacity.equals("Professor")) {
					int professorId=ProfessorId(userId);
					Professors professor = new Professors(username, name, surname, department,capacity, password,
							professorId,null, null);
					return professor;
				}
				else if(capacity.equals("Secretary")) {
					Secretaries secretary = new Secretaries(username, name, surname, department,capacity, password);
					return secretary;
				}
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
		
	
	

}
