package main;

import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {
	public static void main(String[] args) {
	        try (Connection conn = DatabaseConnection.getConnection()) {
	            if (conn != null) {
	                System.out.println("✅ Connection Successful!");
	            }
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	       }
	 }
}
