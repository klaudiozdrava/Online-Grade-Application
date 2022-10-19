package Mainpackage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.*;

//Connection with database
public class CreateUsers {

	private String jdbcURL="jdbc:mysql://localhost:3306/Webproject?useSSL=false";
	private String jdbcUsername="INSERT YOUR USERNAME";//username of sql
	private String jdbcPassword="INSERT YOUR PASSWORD";//password 
	private String jdbcDriver="com.mysql.cj.jdbc.Driver";//Driver that is used (jdbc)
	
	public Connection getConnection() {
		
		Connection conn=null;
		try {
			Class.forName(jdbcDriver);
			conn=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
			
	}
	
	
		
	
}
