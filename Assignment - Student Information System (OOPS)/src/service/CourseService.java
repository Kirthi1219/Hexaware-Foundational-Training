package service;

import java.util.List;

import dao.CourseDAO;
import entity.Course;
import entity.Teacher;
import exception.CourseNotFoundException;
import exception.InvalidCourseDataException;
import exception.TeacherNotFoundException;

public class CourseService {
	private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }
    
    // Add a course
    public void addCourse(Course course) {
        try {
            if (course.getCourseName() == null || course.getCourseName().isEmpty() ||
                course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
                throw new InvalidCourseDataException("Course name or code cannot be empty.");
            }
            courseDAO.addCourse(course);
            System.out.println("Course added successfully!");
        } catch (InvalidCourseDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Retrieve all courses
    public void getAllCourses() {
        try {
            List<Course> courses = courseDAO.getAllCourses();
            if (courses.isEmpty()) {
                throw new CourseNotFoundException("No courses found.");
            }
            for (Course course : courses) {
                System.out.println(course);
            }
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Retrieve a course by ID
    public void getCourseById(int courseId) {
        try {
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                throw new CourseNotFoundException("Course not found.");
            }
            System.out.println(course);
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Update course information
    public void updateCourse(Course course) {
        try {
            if (course.getCourseName() == null || course.getCourseName().isEmpty() ||
                course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
                throw new InvalidCourseDataException("Invalid course details provided.");
            }
            courseDAO.updateCourse(course);
            System.out.println("Course updated successfully!");
        } catch (InvalidCourseDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Assign a teacher to a course
    public void assignTeacherToCourse(int courseId, Teacher teacher) {
        try {
            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher cannot be assigned as they do not exist.");
            }
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
            }
            course.assignTeacher(teacher);
            courseDAO.updateCourse(course);
            System.out.println("Teacher assigned successfully!");
        } catch (TeacherNotFoundException | CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Delete a course
    public void deleteCourse(int courseId) {
        try {
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                throw new CourseNotFoundException("Course not found.");
            }
            courseDAO.deleteCourse(courseId);
            System.out.println("Course deleted successfully!");
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
