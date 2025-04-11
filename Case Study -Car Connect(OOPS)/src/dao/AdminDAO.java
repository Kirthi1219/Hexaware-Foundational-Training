package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;
import exception.DatabaseConnectionException;

public class AdminDAO {
	 private Connection connection = DatabaseContext.getConnection();
	 
	 // Get admin by id 
	 public Admin getAdminById(int adminId) throws DatabaseConnectionException {
	        String query = "SELECT * FROM Admin WHERE adminID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {  //Get this SQL query ready for execution.Make it precompiled and returns a PreparedStatement object, which lets you plug in real values for the placeholders and run the query.
	            stmt.setInt(1, adminId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return mapResultSetToAdmin(rs);
	            }
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error retrieving admin by ID: " + e.getMessage());
	        }
	        return null;
	    }
	 
	 //Get admin by username
	 public Admin getAdminByUsername(String username) throws DatabaseConnectionException {
	        String query = "SELECT * FROM Admin WHERE username = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, username);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return mapResultSetToAdmin(rs);
	            }
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error retrieving admin by username: " + e.getMessage());
	        }
	        return null;
	    }
	 
	 //Register a new admin
	 public boolean registerAdmin(Admin admin) throws DatabaseConnectionException {
	        String query = "INSERT INTO Admin (firstName, lastName, email, phoneNumber, username, password, role, joinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            setPreparedStatement(admin, stmt);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error registering admin: " + e.getMessage());
	        }
	    }
	 
	 //update a admin
	 public boolean updateAdmin(Admin admin) throws DatabaseConnectionException {
	        String query = "UPDATE Admin SET firstName=?, lastName=?, email=?, phoneNumber=?, username=?, password=?, role=?, joinDate=? WHERE adminID=?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            setPreparedStatement(admin, stmt);
	            stmt.setInt(9, admin.getAdminID());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error updating admin: " + e.getMessage());
	        }
	    }
	 
	 //delete a admin
	 public boolean deleteAdmin(int adminId) throws DatabaseConnectionException {
	        String query = "DELETE FROM Admin WHERE adminID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, adminId);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error deleting admin: " + e.getMessage());
	        }
	    }
	 
	 //Helper Method
	 //This method creates and returns an Admin object using the values fetched from the database.
	 private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
	        return new Admin(
	        		rs.getInt("adminID"),
	                rs.getString("firstName"),
	                rs.getString("lastName"),
	                rs.getString("email"),
	                rs.getString("phoneNumber"),
	                rs.getString("username"),
	                rs.getString("password"),
	                rs.getString("role"),
	                rs.getString("joinDate")
	        );
	    }
	 
	 private void setPreparedStatement(Admin admin, PreparedStatement stmt) throws SQLException {
	        stmt.setString(1, admin.getFirstName());
	        stmt.setString(2, admin.getLastName());
	        stmt.setString(3, admin.getEmail());
	        stmt.setString(4, admin.getPhoneNumber());
	        stmt.setString(5, admin.getUsername());
	        stmt.setString(6, admin.getPassword());
	        stmt.setString(7, admin.getRole());
	        stmt.setString(8, admin.getJoinDate());
	    }
}
