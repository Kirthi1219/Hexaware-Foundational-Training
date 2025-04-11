package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import exception.DuplicateEnrollmentException;
import exception.InvalidStudentDataException;

public class Student {

	 //Instance variables
	 private int studentId; 
	 private String firstName;
	 private String lastName;
	 private String dateOfBirth;
	 private String email;
	 private String phoneNumber;
	 private List<Payment> payments;
	 
	 //Constructor
	 public Student(int studentId, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber){
		    this.studentId = studentId;
		    this.firstName = firstName;
		    this.lastName = lastName;
		    this.dateOfBirth = dateOfBirth;
		    this.email = email;
		    this.phoneNumber = phoneNumber;
	        this.payments = new ArrayList<>();
		}
	 
	 //Methods
	 
	 //Method to check valid email format
	 private boolean isValidEmail(String email) {
	        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	        return Pattern.matches(emailRegex, email);
	    }
	 
	 public int getStudentId() {
		 return studentId;
	 }
	 
	 public String getFirstName() {
		 return firstName;
	 }
	 
	 public String getLastName() {
		 return lastName;
	 }
	 
	 
	 //Enrolls the student in a course
	 public void enrollInCourse(Course course){
		 try {
	            for (Enrollment enrollment : Enrollment.getAllEnrollments()) {
	                if (enrollment.getStudent().equals(this) && enrollment.getCourse().equals(course)) {
	                    throw new DuplicateEnrollmentException("Student " + firstName + " " + lastName + " is already enrolled in " + course.getCourseName());
	                }
	            }
	            Enrollment enrollment = new Enrollment(Enrollment.getAllEnrollments().size() + 1, this, course, new Date());
	            System.out.println(firstName + " " + lastName + " successfully enrolled in " + course.getCourseName());
	        } catch (DuplicateEnrollmentException e) {
	            System.out.println("ERROR: " + e.getMessage());
	        }
	 }
	 
	 //update student information
	 public void updateStudentInfo(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber){
		 try {
	            if (!isValidEmail(email)) {
	                throw new InvalidStudentDataException("Invalid email format for student: " + firstName + " " + lastName);
	            }
	            this.firstName = firstName;
	            this.lastName = lastName;
	            this.dateOfBirth = dateOfBirth;
	            this.email = email;
	            this.phoneNumber = phoneNumber;
	            System.out.println("Student information updated successfully for " + firstName + " " + lastName);
	        } catch (InvalidStudentDataException e) {
	            System.out.println("ERROR: " + e.getMessage());
	        }
		 	
	    }
	 
	 //Records a payment made by the student.
	 public void makePayment(double amount, Date paymentDate){
		 	Payment payment = new Payment(payments.size() + 1, this, amount, paymentDate);
	        payments.add(payment);
	        System.out.println("Payment of Rs" + amount + " recorded for " + firstName + " " + lastName);
	    }
	 
	// Displays detailed information about the student
	 public void displayStudentInfo() {
	        System.out.println("Student ID: " + studentId);
	        System.out.println("Name: " + firstName + " " + lastName);
	        System.out.println("Date of Birth: " + dateOfBirth);
	        System.out.println("Email: " + email);
	        System.out.println("Phone Number: " + phoneNumber);
	    }
	 
	 
	 //Get list of enrolled courses
	 public void getEnrolledCourses() {
	        List<Course> enrolledCourses = new ArrayList<>();

	        // Find courses where this student is enrolled
	        for (Enrollment enrollment : Enrollment.getAllEnrollments()) {
	            if (enrollment.getStudent().equals(this)) {
	                enrolledCourses.add(enrollment.getCourse());
	            }
	        }

	        // Display enrolled courses
	        if (enrolledCourses.isEmpty()) {
	            System.out.println(firstName + " " + lastName + " is not enrolled in any courses.");
	        } else {
	            System.out.println("Courses enrolled by " + firstName + " " + lastName + ":");
	            for (Course course : enrolledCourses) {
	                System.out.println("Course ID: " + course.getCourseId() + 
	                                   ", Course Name: " + course.getCourseName());
	            }
	        }
	    }
	    
	 //Get payment history 
	 public void getPaymentHistory() {
     List<Payment> paymentHistory = new ArrayList<>();

     // Find payments made by this student
     for (Payment payment : Payment.getAllPayments()) {
         if (payment.getStudent().equals(this)) {
             paymentHistory.add(payment);
         }
     }

     // Display payment history
     if (paymentHistory.isEmpty()) {
         System.out.println(firstName + " " + lastName + " has no payment records.");
     } else {
         System.out.println("Payment history for " + firstName + " " + lastName + ":");
         for (Payment payment : paymentHistory) {
             System.out.println("Payment ID: " + payment.getPaymentId() + 
                                ", Amount: $" + payment.getPaymentAmount() + 
                                ", Date: " + payment.getPaymentDate());
         }
     	}
	 }

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@Override
	public String toString() {
	    return "Student Details:\n"+
	           "Student ID   : " + studentId + "\n" +
	           "Name         : " + firstName + " " + lastName + "\n" +
	           "Date of Birth: " + dateOfBirth + "\n" +
	           "Email        : " + email + "\n" +
	           "Phone Number : " + phoneNumber;
	}

	public String getStudentName() {
		return firstName+lastName;
	}

	
	  
}
