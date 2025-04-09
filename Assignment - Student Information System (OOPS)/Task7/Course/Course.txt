package entity;

import exception.CourseNotFoundException;
import exception.InvalidCourseDataException;
import exception.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	//Instance Variables
	private int courseId;
    private String courseName;
    private String courseCode;
    private Teacher assignedTeacher; //This stores a reference to a Teacher object, which contains more details. We can later retrieve more details about the assigned teacher.
    private static List<Course> allCourses = new ArrayList<>();
    
    //Constructor
    public Course(int courseId, String courseName, String courseCode, Teacher assignedTeacher){
    	this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.assignedTeacher = assignedTeacher;
        allCourses.add(this);
    }
    
//METHODS
    
    //Static method to return all courses
    public static List<Course> getAllCourses() {
        return allCourses;
    }
    
    //Getter for CourseID
    public int getCourseId() {
        return courseId;
    }
    
    //Getter for Course Name
    public String getCourseName() {
        return courseName;
    }
    
    //Returns the teacher assigned from the course
    public Teacher teacher() {
    	return assignedTeacher;
    }
    
    //Assigns a teacher to the course
    public void assignTeacher(Teacher teacher) {
    	try {
            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher cannot be assigned as they do not exist.");
            }
            this.assignedTeacher = teacher;
            System.out.println("Teacher " + teacher.getFirstName() + " assigned to course " + courseName);
        } catch (TeacherNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Updates course information.
    public void updateCourseInfo(String courseCode, String courseName, Teacher assignedTeacher) {
    	try {
            if (courseName == null || courseName.isEmpty() || courseCode == null || courseCode.isEmpty()) {
                throw new InvalidCourseDataException("Invalid course details provided.");
            }
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.assignedTeacher = assignedTeacher;
            System.out.println("Course details updated successfully!");
        } catch (InvalidCourseDataException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    //Displays detailed information about the course
    public void displayCourseInfo() {
    	try {
            if (!allCourses.contains(this)) {
                throw new CourseNotFoundException("Course does not exist.");
            }
            System.out.println("Course ID: " + courseId);
            System.out.println("Course Name: " + courseName);
            System.out.println("Course Code: " + courseCode);
            System.out.println("Instructor: " + (assignedTeacher != null ? assignedTeacher.getFirstName() + " " + assignedTeacher.getLastName() : "No teacher assigned"));
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Retrieves a list of student enrollments for the course
    public void getEnrollments() {
        List<Student> enrolledStudents = Enrollment.getEnrollmentsByCourse(this);
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("Enrolled Students:");
            for (Student student : enrolledStudents) {
                System.out.println("Student ID: " + student.getStudentId() + 
                                   ", Name: " + student.getFirstName() + " " + student.getLastName());
            }
        }
    }
    
    
    // Retrieves the assigned teacher for the course.
    public void getTeacher() {
    	try {
            if (assignedTeacher == null) {
                throw new TeacherNotFoundException("No teacher assigned to this course.");
            }
            System.out.println("Assigned Teacher: " + assignedTeacher.getFirstName() + " " + assignedTeacher.getLastName());
        } catch (TeacherNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int getTeacherId() {
        return (assignedTeacher != null) ? assignedTeacher.getTeacherId() : -1;
    }

	public String getCourseCode() {
		return courseCode;
	}
	
	@Override
	public String toString() {
	    return "Course Details:\n" +
	           "Course ID     : " + courseId + "\n" +
	           "Course Name   : " + courseName + "\n" +
	           "Course Code   : " + courseCode + "\n" +
	           "Assigned Teacher: " + (assignedTeacher != null 
	                                    ? assignedTeacher.getFirstName() + " " + assignedTeacher.getLastName() + 
	                                      " (ID: " + assignedTeacher.getTeacherId() + ")" 
	                                    : "No teacher assigned") ;
	}

	
}
