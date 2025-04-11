package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Payment;
import entity.Student;

public class PaymentDAO {
	private Connection connection;

    public PaymentDAO() {
        this.connection =DatabaseConnection.getConnection();
    }
    
    // Add a Payment
    public boolean addPayment(Payment payment) {
        String sql="INSERT INTO payments (payment_id, student_id, amount, payment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt=connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getPaymentId());
            stmt.setInt(2, payment.getStudent().getStudentId());
            stmt.setDouble(3, payment.getPaymentAmount());
            stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
            return false;
        }
    }
    
    
    // Get a Payment by ID
    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                        rs.getInt("payment_id"),
                        new StudentDAO().getStudentById(rs.getInt("student_id")),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error retrieving payment: " + e.getMessage());
        }
        return null;
    }
    
    // Get all Payments
    public List<Payment> getAllPayments() {
        List<Payment> payments=new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (PreparedStatement stmt =connection.prepareStatement(sql);
             ResultSet rs=stmt.executeQuery()) {

            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("payment_id"),
                    new StudentDAO().getStudentById(rs.getInt("student_id")),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all payments: " + e.getMessage());
        }
        return payments;
    }
    
    // Get Payments by Student
    public List<Payment> getPaymentsByStudent(Student student) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, student.getStudentId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    payments.add(new Payment(
                        rs.getInt("payment_id"),
                        student,
                        rs.getDouble("amount"),
                        rs.getDate("payment_date")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving payments for student: " + e.getMessage());
        }
        return payments;
    }
    
    // Delete a Payment by ID
    public void deletePayment(int paymentId) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }
}
