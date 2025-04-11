package service;

import java.util.List;

import dao.StudentDAO;
import entity.Student;
import exception.InvalidStudentDataException;
import exception.StudentNotFoundException;

public class StudentService {

	private StudentDAO studentdao;
	
	public StudentService(){
		this.studentdao=new StudentDAO();  //every time a StudentService object is created, a new StudentDAO object is also instantiated.
	}
	
	// Insert a student
    public void addStudent(Student student) {
        try {
            if (student == null || student.getFirstName() == null || student.getLastName() == null || 
                student.getDateOfBirth() == null || student.getEmail() == null || student.getPhoneNumber() == null) {
                throw new InvalidStudentDataException("Invalid student data! All fields must be provided.");
            }
            studentdao.addStudent(student);
            System.out.println("Student added successfully.");
        } catch (InvalidStudentDataException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding student: " + e.getMessage());
        }
    }
    
    // Get student by ID
    public void getStudentById(int studentId) {
        try {
            Student student = studentdao.getStudentById(studentId);
            if (student == null) {
                throw new StudentNotFoundException("❌ Student with ID " + studentId + " not found.");
            }
            System.out.println("✅ Student Details Retrieved Successfully:\n" +student);

        } catch (StudentNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while retrieving student: " + e.getMessage());
        }
    }

    // Get all students
    public void getAllStudents() {
        try {
            List<Student> students = studentdao.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                System.out.println("Students list retrieved successfully:");
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        } catch (Exception e) {
            System.err.println("Unexpected error while retrieving students: " + e.getMessage());
        }
    }
    
 // Update student details
    public void updateStudent(Student student) {
        try {
            if (student == null || student.getFirstName() == null || student.getLastName() == null || 
                student.getDateOfBirth() == null || student.getEmail() == null || student.getPhoneNumber() == null) {
                throw new InvalidStudentDataException("Invalid student data! All fields must be provided.");
            }

            if (studentdao.getStudentById(student.getStudentId()) == null) {
                throw new StudentNotFoundException("Cannot update.Student with ID " + student.getStudentId() + " not found.");
            }
            studentdao.updateStudent(student);
            System.out.println("Student updated successfully.");
        } catch (InvalidStudentDataException | StudentNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while updating student: " + e.getMessage());
        }
    }
    
    // Delete student
    public void deleteStudent(int studentId) {
        try {
            if (studentdao.getStudentById(studentId) == null) {
                throw new StudentNotFoundException("❌ Cannot delete. Student with ID " + studentId + " not found.");
            }
            studentdao.deleteStudent(studentId);
            System.out.println("Student with ID " + studentId + " deleted successfully.");
        } catch (StudentNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while deleting student: " + e.getMessage());
        }
    }
}
