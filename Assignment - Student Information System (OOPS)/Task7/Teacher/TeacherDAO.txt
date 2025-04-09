package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Teacher;

public class TeacherDAO {
	private Connection connection;

    public TeacherDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
 // Insert a new teacher
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teacher (first_name, last_name, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Error adding teacher: " + e.getMessage(), e);
        }
    }
    
    // Get teacher by ID
    public Teacher getTeacherById(int teacherId) {
        String sql = "SELECT * FROM teacher WHERE teacher_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Error retrieving teacher: " + e.getMessage(), e);
        }
        return null;
    }
    
    // Get all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teachers.add(new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Error retrieving teachers: " + e.getMessage(), e);
        }
        return teachers;
    }
    
    // Update teacher details
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE teacher SET first_name = ?, last_name = ?, email = ? WHERE teacher_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setInt(4, teacher.getTeacherId());
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Error updating teacher: " + e.getMessage(), e);
        }
    }
    
    // Delete teacher
    public void deleteTeacher(int teacherId) {
        String sql = "DELETE FROM teacher WHERE teacher_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	 throw new RuntimeException("Error deleting teacher: " + e.getMessage(), e);
        }
    }
}
