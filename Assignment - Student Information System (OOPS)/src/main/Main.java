package main;
import java.util.Date;
import java.util.List;

import dao.DatabaseConnection;
import entity.*;
import service.CourseService;
import service.EnrollmentService;
import service.PaymentService;
import service.StudentService;
import service.TeacherService;


public class Main {
	public static void main(String[] args) {
		DatabaseConnection.getConnection();
		Teacher raj=new Teacher(1,"Raj","Mohan","raj1982@gmail.com");
		Teacher gayathri=new Teacher(2,"Gayathri","Devi","gayu1111@gmail.com");
		Teacher kayal=new Teacher(11,"Kayal","Kumar","kayals1919@gmail.com");

		Course java = new Course(01, "Java Programming", "JS101",raj);
		Course dataStructures = new Course(02, "Data Structures and Algorithms", "CS102",gayathri);
		Course cloudComputing = new Course(03, "cloud Computing", "CS103", raj);
		Course networks=new Course(04,"Networks","CS104",kayal);
		
		
//		Student s1_John=new Student(101, "John", "Doe", "2002-04-25", "john.doe@gmail.com", "9876543210");
		Student s1_John=new Student(14, "John", "Doe", "2002-04-25", "johndoe@gmail.com", "9876443211");
		Student s2_shruthiy=new Student(2, "Shruthiy", "Sara", "2003-06-18", "shru08@gmail.com", "9123456789");
		//Student s3_manu=new Student(103,"Manu","Karthick","2004-02-03","karthick123@gmail.com","9876543210");
		Student s3_manu=new Student(7,"Manu","Karthick","2004-02-03","karthick123@gmail.com","9876543210");
		Student s4_kumar=new Student(11,"kumar","kandan","2004-02-28","kandhan80@gmail.com","9876543110");
		
//		s1_John.enrollInCourse(networks);
//		s1_John.enrollInCourse(networks);
//		s1_John.updateStudentInfo("John", "Doe", "2002-04-25", "doe123", "9876543210");
		
//		java.assignTeacher(null);
//		java.updateCourseInfo("CS101", "Java Programming",null);
//		java.getTeacher();
//		java.updateCourseInfo(null, "Java Programming", kayal);
		
//		kayal.updateTeacherInfo("Kayal", "Senthil","kayal123");
		
//		Enrollment e1=new Enrollment(22,s4_kumar,java,new Date());
//		Enrollment e2=new Enrollment(2,s2_shruthiy,java,new Date());
		
//		Payment p1=new Payment(22,s3_manu,12000,new Date());
		Payment p2=new Payment(23,s4_kumar,15000,new Date());
//		s2_shruthiy.getPaymentHistory();
		
		CourseService cs=new CourseService();
//		cs.addCourse(java);
//		cs.getAllCourses();
//		cs.getCourseById(1);
//		Course java = new Course(01, "Java Programming", "JS101",raj);
//		cs.updateCourse(java);
//		cs.updateCourse(networks);
//		cs.assignTeacherToCourse(1, gayathri);
//		cs.deleteCourse(11);
		
		TeacherService ts=new TeacherService();
//		ts.addTeacher(kayal);
//		ts.getAllTeachers();
//		ts.getTeacherById(9);
//		ts.updateTeacher(kayal);
//		ts.deleteTeacher(11);
		
		StudentService ss=new StudentService();
//		ss.addStudent(s3_manu);
//		ss.getAllStudents();
//		ss.getStudentById(8);
//		ss.updateStudent(s3_manu);
//		ss.updateStudent(s4_kumar);
//		ss.deleteStudent(14);
		
		EnrollmentService es=new EnrollmentService();
//		es.enrollStudent(e1);
//		es.getAllEnrollments();
//		es.isStudentEnrolled(11, 2);
//		es.isStudentEnrolled(3, 7);
//		es.getStudentsByCourse(3);
//		es.removeEnrollment(22);
//		StudentService ss=new StudentService();
//		Student s1_John=new Student(14, "John", "Doe", "2002-04-25", "john.doe@gmail.com", "9876543210");
//		ss.addStudent(s1_John);		
		
		PaymentService ps=new PaymentService();
//		ps.addPayment(p2);
//		ps.getAllPayments();
//		ps.getPaymentById(11);
//		ps.getPaymentsByStudent(s4_kumar);
//		ps.deletePayment(21);
		
		//TASK 8
		Course math101 = new Course(11, "Mathematics 101", "MA201", gayathri);
		Course introProgramming = new Course(12, "Introduction to Programming", "PG101", raj);
//		cs.addCourse(introProgramming);
//		cs.addCourse(math101);
		Student johnDoe = new Student(14, "John", "Doe", "1995-08-15", "john.doe@example.com", "123-456-7890");
//		ss.addStudent(johnDoe);
		Enrollment enrollment1 = new Enrollment(22, johnDoe, introProgramming, new Date());
		Enrollment enrollment2 = new Enrollment(23, johnDoe, math101, new Date());
//		es.enrollStudent(enrollment1);
//		es.enrollStudent(enrollment2);
		
		//TASK9
		Teacher sarahSmith = new Teacher(11, "Sarah", "Smith", "sarah.smith@example.com");
		Course advDbms = new Course(13, "Advanced Database Management", "CS302", null);
		
//		ts.addTeacher(sarahSmith);
//		cs.addCourse(advDbms);
		
//		Assign Sarah Smith as the instructor for the course using service
//		cs.assignTeacherToCourse(13, sarahSmith);
//		cs.getCourseById(13);
		
		//TASK 10
		Student janeJohnson = new Student(15, "Jane", "Johnson", "2004-09-12", "jane.johnson@example.com", "9998887777");
//		ss.addStudent(janeJohnson);
//		ss.getStudentById(15);
		Payment janePayment = new Payment(24, janeJohnson, 500.00, new Date(123, 3, 10));
//		ps.addPayment(janePayment);
		 
		//TASK 11
		Teacher csTeacher = new Teacher(12, "Ravi", "Kumar", "ravi01kumar@example.com");
//		ts.addTeacher(csTeacher);
		Course cs101= new Course(14, "Computer Science", "CS101", csTeacher);
//		cs.addCourse(cs101);
		Enrollment cse1= new Enrollment(24, s4_kumar, cs101, new Date());
		Enrollment cse2= new Enrollment(25, s2_shruthiy, cs101, new Date());
//		es.enrollStudent(cse1);
//		es.enrollStudent(cse2);
		
		List<Student> enrolledStudents = es.getStudentsByCourse(cs101.getCourseId());
		StringBuilder report = new StringBuilder();
		report.append("=== Enrollment Report: " + cs101.getCourseName() + " ===\n");

		if (enrolledStudents.isEmpty()) {
		    report.append("No students are currently enrolled in this course.\n");
		} else {
		    for (Student student : enrolledStudents) {
		        report.append("Student ID: " + student.getStudentId())
		              .append(", Name: " + student.getFirstName()).append(" ").append(student.getLastName())
		              .append(", Email: " + student.getEmail()).append("\n");
		    }
		}

		report.append("=== End of Report ===");
		System.out.println(report.toString());
		

	}
}
