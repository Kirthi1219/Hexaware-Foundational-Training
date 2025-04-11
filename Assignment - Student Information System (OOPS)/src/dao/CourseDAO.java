package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.Teacher;

public class CourseDAO {
	private Connection connection;

    public CourseDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    // Add a course to the database
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, course_code, teacher_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            
            int teacherId = course.getTeacherId();
            if (teacherId == -1) { 
                stmt.setNull(3, Types.INTEGER); // No teacher assigned
            } else {
                stmt.setInt(3, teacherId);
            }
            
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Error adding course: " + e.getMessage());
        }
    }

    // Get all courses from the database
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        	 while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String courseCode = rs.getString("course_code");
                int teacherId = rs.getInt("teacher_id");

                // ✅ Create a Course object and add it to the list
                TeacherDAO teacherDAO = new TeacherDAO();
				Teacher teacher = teacherDAO.getTeacherById(teacherId);
                Course course = new Course(courseId, courseName, courseCode, teacher);
                courses.add(course);
            } 
        } catch (SQLException e) {
        	throw new RuntimeException("Error retrieving courses: " + e.getMessage());
        }
		return courses;
    }
    
    // Get a course by ID
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String courseCode = rs.getString("course_code");
                int teacherId = rs.getInt("teacher_id");
                // Fetch teacher details
				  TeacherDAO teacherDAO = new TeacherDAO();
				  Teacher teacher = teacherDAO.getTeacherById(teacherId);
                return new Course(id, courseName, courseCode, teacher);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Error retrieving course: " + e.getMessage());
        }
        return null; // Return null if course not found
    }
    
    

	// Update course information
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, course_code = ?, teacher_id = ? WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getTeacherId());
            stmt.setInt(4, course.getCourseId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("No course found with ID: " + course.getCourseId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating course: " + e.getMessage());
        }
    }
    
    // Delete a course by ID
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RuntimeException("No course found with ID: " + courseId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course: " + e.getMessage());
        }
    }

}
