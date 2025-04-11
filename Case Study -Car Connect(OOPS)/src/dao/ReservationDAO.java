package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Reservation;
import exception.DatabaseConnectionException;

public class ReservationDAO {
	 private Connection connection = DatabaseContext.getConnection();
	 
	 	// Fetches the daily rate for a specific vehicle
	    public double getDailyRate(int vehicleID) throws DatabaseConnectionException {
	        String query = "SELECT dailyRate FROM vehicle WHERE vehicleID = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, vehicleID);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getDouble("dailyRate"); 
	            } else {
	                throw new DatabaseConnectionException("No rate found for vehicle ID: " + vehicleID);
	            }
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error fetching vehicle rate: " + e.getMessage());
	        }
	    }

	 
	 	// Get reservation by ID
	    public Reservation getReservationById(int reservationId) throws DatabaseConnectionException {
	        String query = "SELECT * FROM reservation WHERE reservationID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, reservationId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return mapResultSetToReservation(rs);
	            }
	            return null;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error retrieving reservation by ID: " + e.getMessage());
	        }
	    }
	    
	    //get all
	    public List<Reservation> getAllReservations() throws DatabaseConnectionException {
	        List<Reservation> reservations = new ArrayList<>();
	        String query = "SELECT * FROM reservation";
	        try (PreparedStatement stmt = connection.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                reservations.add(mapResultSetToReservation(rs));
	            }
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error retrieving all reservations: " + e.getMessage());
	        }
	        return reservations;
	    }

	    
	    //Get reservations by customer ID
	    public List<Reservation> getReservationsByCustomerId(int customerId) throws DatabaseConnectionException {
	        List<Reservation> reservations = new ArrayList<>();
	        String query = "SELECT * FROM reservation WHERE customerID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, customerId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                reservations.add(mapResultSetToReservation(rs));
	            }
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error retrieving reservations by customer ID: " + e.getMessage());
	        }
	        return reservations;
	    }
	    
	 // Insert a new reservation
	    public boolean createReservation(Reservation reservation) throws DatabaseConnectionException {
	        String query = "INSERT INTO reservation(customerID, vehicleID, startDate, endDate, totalCost, status) VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, reservation.getCustomerID());
	            stmt.setInt(2, reservation.getVehicleID());
	            stmt.setString(3, reservation.getStartDate());
	            stmt.setString(4, reservation.getEndDate());
	            stmt.setDouble(5, reservation.getTotalCost());
	            stmt.setString(6, reservation.getStatus());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error creating reservation: " + e.getMessage());
	        }
	    }
	    
	 // Update reservation
	    public boolean updateReservation(Reservation reservation) throws DatabaseConnectionException {
	        String query = "UPDATE reservation SET customerID=?, vehicleID=?, startDate=?, endDate=?, totalCost=?, status=? WHERE reservationID=?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, reservation.getCustomerID());
	            stmt.setInt(2, reservation.getVehicleID());
	            stmt.setString(3, reservation.getStartDate());
	            stmt.setString(4, reservation.getEndDate());
	            stmt.setDouble(5, reservation.getTotalCost());
	            stmt.setString(6, reservation.getStatus());
	            stmt.setInt(7, reservation.getReservationID());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error updating reservation: " + e.getMessage());
	        }
	    }
	    
	 // Delete reservation
	    public boolean deleteReservation(int reservationId) throws DatabaseConnectionException {
	        String query = "DELETE FROM reservation WHERE reservationID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, reservationId);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error deleting reservation: " + e.getMessage());
	        }
	    }
	    
	    // Helper method to map ResultSet to Reservation object
	    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
	        return new Reservation(
	        		rs.getInt("reservationID"),
	                rs.getInt("customerID"),
	                rs.getInt("vehicleID"),
	                rs.getString("startDate"),
	                rs.getString("endDate"),
	                rs.getDouble("totalCost"),
	                rs.getString("status")
	        );
	    }
	 
}
