package service;

import java.sql.Date;
import java.util.List;

import dao.PaymentDAO;
import entity.Payment;
import entity.Student;
import exception.InsufficientFundsException;

public class PaymentService {
	private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }
    
    // Add a Payment using Payment object
    public void addPayment(Payment payment) {
        try {
            if (payment.getPaymentAmount() <=0) {
                throw new InsufficientFundsException("Amount must be greater than zero.");
            }

            boolean isAdded =paymentDAO.addPayment(payment);
            if (isAdded) {
                System.out.println("Payment added successfully:\n" + payment);
            } else {
                System.out.println("Failed to add payment. Make sure the student exists.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Payment failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while adding payment: " + e.getMessage());
        }
    }
    
    
    // Get payment by ID
    public void getPaymentById(int paymentId) {
        Payment payment = paymentDAO.getPaymentById(paymentId);
        if (payment != null) {
            System.out.println("Payment found:\n" + payment);
        } else {
            System.out.println("No payment found with ID: " + paymentId);
        }
    }
    
    // Get all payments
    public void getAllPayments() {
        List<Payment> payments = paymentDAO.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found in the system.");
        } else {
            System.out.println("All Payments:");
            for (Payment p : payments) {
                System.out.println(p);
            }
        }
    }
    
    // Get payments for a specific student
    public void getPaymentsByStudent(Student student) {
        List<Payment> payments= paymentDAO.getPaymentsByStudent(student);
        if (payments.isEmpty()) {
            System.out.println("No payments found for student: " + student.getStudentName());
        } else {
            System.out.println("Payments for " + student.getStudentName() + ":");
            for (Payment p : payments) {
                System.out.println(p);
            }
        }
    }
    
    // Delete a payment
    public void deletePayment(int paymentId) {
        paymentDAO.deletePayment(paymentId);
        System.out.println("Payment with ID " + paymentId + " deleted successfully.");
    }
}
