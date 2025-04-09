package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	 private static Connection connection = null;
	 public static Connection getConnection() {
		 if(connection==null) {
			 try {
				 Properties props = PropertyUtil.getDbProperties();
				 String url = props.getProperty("url");
	             String username = props.getProperty("username");
	             String password = props.getProperty("password");
	             
	             //load the driver
	             Class.forName("com.mysql.cj.jdbc.Driver");
	             
	             //Create connection
	             connection = DriverManager.getConnection(url, username, password);
	             System.out.println("✅ Database connection established successfully.");
	             
			 }
			 catch (ClassNotFoundException e) {
				System.out.println("Driver not found: " + e.getMessage());
			 }
			 catch (SQLException e) {
	            System.out.println("Exception: " + e.getMessage());
	         }
		 }
	 return connection;
	 }
}
