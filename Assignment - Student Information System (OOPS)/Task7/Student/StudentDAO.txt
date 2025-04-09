package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Student;

public class StudentDAO {
	private Connection connection;

    public StudentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
 // Insert new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getDateOfBirth());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Error adding student: " + e.getMessage(), e);
        }
    }
    
 // Get student by ID
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Error retrieving student: " + e.getMessage(), e);
        }
        return null;
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        return students;
    }
    
    // Update student details
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ? WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getDateOfBirth());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhoneNumber());
            stmt.setInt(6, student.getStudentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
        	 throw new RuntimeException("Error updating student: " + e.getMessage(), e);
        }
    }
    
    // Delete student
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Error deleting student: " + e.getMessage(), e);
        }
    }
}
