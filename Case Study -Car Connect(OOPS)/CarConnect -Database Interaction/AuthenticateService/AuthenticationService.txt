package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.DatabaseContext;
import entity.Admin;
import entity.Customer;
import exception.AuthenticationException;

public class AuthenticationService {

    // Customer Login Method
    public boolean login(String username, String password) {
        String query="SELECT customerID, password FROM customer WHERE username = ?";
        
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful for customer: " + username);
                    return true;
                } else {
                    throw new AuthenticationException("Invalid password.");
                }
            } else {
                throw new AuthenticationException("User not found.");
            }

        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
        }
        return false;
    }

    //Admin Login Method
    public boolean loginAdmin(String username, String password) {
        String query="SELECT adminID, password FROM admin WHERE username = ?";
        
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful for Admin: " + username);
                    return true;
                } else {
                    throw new AuthenticationException("Invalid password.");
                }
            } else {
                throw new AuthenticationException("User not found.");
            }

        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
        }
        return false;
    }
    
    
    // Customer Registration Method
    public boolean register(Customer customer) {
        String checkQuery= "SELECT customerID FROM customer WHERE username = ?"; // SQL query checks if a username already exists
        String insertQuery= "INSERT INTO customer (firstName, lastName, email, phoneNumber, address, username, password, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; //inserts a new customer record

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            // Check if username exists
            checkStmt.setString(1, customer.getUsername());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new AuthenticationException("Username already taken.");
            }
            // Insert new customer
            insertStmt.setString(1, customer.getFirstName());
            insertStmt.setString(2, customer.getLastName());
            insertStmt.setString(3, customer.getEmail());
            insertStmt.setString(4, customer.getPhoneNumber());
            insertStmt.setString(5, customer.getAddress());
            insertStmt.setString(6, customer.getUsername());
            insertStmt.setString(7, customer.getPassword()); // Storing plain password
            insertStmt.setString(8, customer.getRegistrationDate());

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer registered successfully.");
                return true;
            } else {
                System.out.println("Customer registration failed.");
            }

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
        }
        return false;
    }
    
    //Admin Registration Method
    public boolean registerAdmin(Admin admin) {
        String checkQuery ="SELECT adminID FROM admin WHERE username = ?"; // Check if username already exists
        String insertQuery ="INSERT INTO admin (firstName, lastName, email, phoneNumber, username, password, role, joinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Insert new admin

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            // Check if username exists
            checkStmt.setString(1, admin.getUsername());
            ResultSet rs =checkStmt.executeQuery();
            if (rs.next()) {
                throw new AuthenticationException("Username already taken.");
            }

            // Insert new admin
            insertStmt.setString(1, admin.getFirstName());
            insertStmt.setString(2, admin.getLastName());
            insertStmt.setString(3, admin.getEmail());
            insertStmt.setString(4, admin.getPhoneNumber());
            insertStmt.setString(5, admin.getUsername());
            insertStmt.setString(6, admin.getPassword()); // Plain password
            insertStmt.setString(7, admin.getRole());
            insertStmt.setString(8, admin.getJoinDate());

            int rowsInserted =insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin registered successfully.");
                return true;
            } else {
                System.out.println("Admin registration failed.");
            }

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
        }
        return false;
    }
}
