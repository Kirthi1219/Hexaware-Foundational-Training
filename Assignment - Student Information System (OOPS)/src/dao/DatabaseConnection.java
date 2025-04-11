package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
	private static Connection connection;

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sisdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database Connected Successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("❌ Database Connection Failed!", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
