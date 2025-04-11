//package dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseContext {
//	 private static final String URL = "jdbc:mysql://127.0.0.1:3306/vehiclereservationdb";
//	 private static final String USER = "root";
//	 private static final String PASSWORD = "root";
//	 
//	 public static Connection getConnection() throws SQLException {
//	      return DriverManager.getConnection(URL, USER, PASSWORD);
//	 }
//}
//
//

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {
    private static Connection connection;  //it belongs to the class itself, not to any instance.

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/vehiclereservationdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

//    static {
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD); //created once
//            System.out.println("✅ Database Connected Successfully!");
//        } catch (SQLException e) {
//            throw new RuntimeException("❌ Database Connection Failed!", e);
//        }
//    }
//
//    public static Connection getConnection() {
//        return connection; //always returns the same connection
//    }
    
    public static Connection getConnection() { //returns a new database connection when called
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //dynamically loads the MySQL JDBC driver class
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
        	System.out.println("❌ Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}
