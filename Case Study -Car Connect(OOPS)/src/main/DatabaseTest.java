package main;

import dao.DatabaseContext;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {
	public Connection dbconn;
	public void connectDatabase() {
		try (Connection conn = DatabaseContext.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connection Successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
