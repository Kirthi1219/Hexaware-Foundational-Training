package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.InsufficientFundsException;

public class Payment {

	//Instance Variables
	 private int paymentId;
	 private Student student;
	 private double amount;
	 private Date paymentDate;
	 private static List<Payment> allPayments = new ArrayList<>();
	 
//	 public Payment(int paymentId, Student student, double amount, Date paymentDate) throws InsufficientFundsException {
//		    this.paymentId = paymentId;
//		    this.student = student;
//		    this.amount = amount;
//		    this.paymentDate = paymentDate;
//		    allPayments.add(this);
//		}
	 
	 public Payment(int paymentId, Student student, double amount, Date paymentDate) {
		    try {
		        if (amount <= 0) {
		            throw new InsufficientFundsException("Amount must be greater than zero.");
		        }
		        this.paymentId = paymentId;
		        this.student = student;
		        this.amount = amount;
		        this.paymentDate = paymentDate;
		        allPayments.add(this);
		    } catch (InsufficientFundsException e) {
		        System.out.println("ERROR: " + e.getMessage());
		    }
		}

	//Methods
	 
	 public static List<Payment> getAllPayments() {
		    return allPayments;
		}
	 
	 public int getPaymentId() {
		 return paymentId;
	 }
	 
	// Retrieves the student associated with the payment
	 public Student getStudent() {
	        return student;
	    }
	 
	// Retrieves the payment amount
	 public double getPaymentAmount() {
	        return amount;
	    }
	
	// Retrieves the payment date
	 public Date getPaymentDate() {
	        return paymentDate;
	    }     
	 
	// Retrieves all payments made by a specific student
	 public static List<Payment> getPaymentsByStudent(Student student) {
	     List<Payment> studentPayments = new ArrayList<>();
	     for (Payment payment : allPayments) {
	         if (payment.getStudent().equals(student)) {
	             studentPayments.add(payment);
	         }
	     }
	     return studentPayments;
	 }
	 
	 @Override
	    public String toString() {
	        return "Payment ID: " + paymentId + ", Student: " + student.getFirstName() + " " + student.getLastName() +
	                ", Amount: Rs " + amount + ", Date: " + paymentDate;
	    }
	  
}
