package service;

import java.util.List;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.CourseNotFoundException;
import exception.DuplicateEnrollmentException;
import exception.StudentNotFoundException;

public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    public EnrollmentService() {
        this.enrollmentDAO = new EnrollmentDAO();
        this.studentDAO = new StudentDAO();
        this.courseDAO = new CourseDAO();
    }

    // Add an enrollment
    public void enrollStudent(Enrollment enrollment) {
        try {
            int studentId = enrollment.getStudent().getStudentId();
            int courseId = enrollment.getCourse().getCourseId();

            // Check if the student exists
            if (studentDAO.getStudentById(studentId) == null) {
                throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
            }

            // Check if the course exists
            if (courseDAO.getCourseById(courseId) == null) {
                throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
            }

            // Check if the student is already enrolled
            if (enrollmentDAO.isAlreadyEnrolled(studentId, courseId)) {
                throw new DuplicateEnrollmentException("Student with ID " + studentId + " is already enrolled in Course ID " + courseId);
            }

            enrollmentDAO.addEnrollment(enrollment);
            System.out.println("Student with ID " + studentId + " successfully enrolled in Course ID " + courseId);

        } catch (DuplicateEnrollmentException | StudentNotFoundException | CourseNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Check if a student is enrolled in a course
    public boolean isStudentEnrolled(int studentId, int courseId) {
        try {
            boolean enrolled = enrollmentDAO.isAlreadyEnrolled(studentId, courseId);
            System.out.println(enrolled
                ? "Student with ID " + studentId + " is enrolled in Course ID " + courseId
                : "Student with ID " + studentId + " is NOT enrolled in Course ID " + courseId);
            return enrolled;
        } catch (Exception e) {
            throw new RuntimeException("❌ Error checking enrollment: " + e.getMessage());
        }
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        try {
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found.");
            } else {
                System.out.println("Enrollments retrieved successfully:");
                for (Enrollment enrollment : enrollments) {
                    System.out.println(enrollment);
                }
            }
            return enrollments;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving enrollments: " + e.getMessage());
        }
    }

    // Remove an enrollment
    public void removeEnrollment(int enrollmentId) {
        try {
            enrollmentDAO.deleteEnrollment(enrollmentId);
            System.out.println("Enrollment with ID " + enrollmentId + " removed successfully.");
        } catch (Exception e) {
            throw new RuntimeException("❌ Error removing enrollment: " + e.getMessage());
        }
    }

    // Get students by course
    public List<Student> getStudentsByCourse(int courseId) {
        try {
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                throw new CourseNotFoundException("Course with ID " + courseId + " does not exist.");
            }

            List<Student> students = enrollmentDAO.getStudentsByCourse(courseId);
            if (students.isEmpty()) {
                System.out.println("No students enrolled in Course '" + course.getCourseName() + "' (ID: " + courseId + ").");
            } else {
                System.out.println("Students Enrolled in Course '" + course.getCourseName() + "' (ID: " + courseId + "):");
                for (Student s : students) {
                    System.out.println("Student ID: " + s.getStudentId() + ", Name: " + s.getFirstName() + " " + s.getLastName());
                }
            }
            return students;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving students: " + e.getMessage());
        }
    }
}
