package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.Enrollment;
import entity.Student;

public class EnrollmentDAO {
    private Connection connection;

    public EnrollmentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Add an enrollment
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (enrollment_id, student_id, course_id, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enrollment.getEnrollmentId());
            stmt.setInt(2, enrollment.getStudent().getStudentId());
            stmt.setInt(3, enrollment.getCourse().getCourseId());
            stmt.setDate(4, new java.sql.Date(enrollment.getEnrollmentDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding enrollment: " + e.getMessage(), e);
        }
    }

    // Check if a student is already enrolled in a course
    public boolean isAlreadyEnrolled(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking enrollment: " + e.getMessage(), e);
        }
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollment_id");
                int studentId = rs.getInt("student_id");
                int courseId = rs.getInt("course_id");
                Date enrollmentDate = rs.getDate("enrollment_date");
                Student student = new StudentDAO().getStudentById(studentId);
                Course course = new CourseDAO().getCourseById(courseId);
                enrollments.add(new Enrollment(enrollmentId, student, course, enrollmentDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving enrollments: " + e.getMessage(), e);
        }
        return enrollments;
    }

    // Get students enrolled in a course
    public List<Student> getStudentsByCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id FROM enrollments WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                StudentDAO studentDAO = new StudentDAO();
                while (rs.next()) {
                    students.add(studentDAO.getStudentById(rs.getInt("student_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving students by course: " + e.getMessage(), e);
        }
        return students;
    }

    // Remove an enrollment
    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enrollmentId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No enrollment found with ID: " + enrollmentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting enrollment: " + e.getMessage(), e);
        }
    }
}
