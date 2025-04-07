package service;
import java.util.List;

import dao.ReservationDAO;
import entity.Reservation;
import exception.DatabaseConnectionException;
import exception.InvalidInputException;
import exception.ReservationException;


public class ReservationService implements IReservationService {
	private ReservationDAO reservationDAO = new ReservationDAO();
	
	@Override
	public Reservation getReservationById(int reservationId) {
        try {
        	 if (reservationId <= 0) {
                 throw new InvalidInputException("Invalid reservation ID: " + reservationId);
             }
            Reservation reservation = reservationDAO.getReservationById(reservationId);
            if (reservation == null) {
            	throw new ReservationException("No reservation found with ID: " + reservationId);
            } 
            System.out.println("Reservation retrieved successfully: " + reservation);
            return reservation;
        } catch (DatabaseConnectionException e) {
            System.out.println("Error retrieving reservation: " + e.getMessage());
            return null;
        } catch (InvalidInputException e) {
			e.printStackTrace();
			return null;
		} catch (ReservationException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	 @Override
	 public List<Reservation> getReservationsByCustomerId(int customerId) {
	        try {
	            List<Reservation> reservations = reservationDAO.getReservationsByCustomerId(customerId);
	            if (reservations.isEmpty()) {
	            	throw new ReservationException("No reservations found for customer ID: " + customerId);
	            } else {
	                System.out.println("Reservations retrieved successfully for customer ID: " + customerId);
	                for (Reservation reservation : reservations) {
	                    System.out.println(reservation);
	                    System.out.println("--------------------------------");
	                }
	            }
	            return reservations;
	        } catch (DatabaseConnectionException e) {
	            System.out.println("Error retrieving reservations: " + e.getMessage());
	            return null;
	        } catch (ReservationException e) {
				e.printStackTrace();
				return null;
			}
	    }
	 
	 @Override
	 public boolean createReservation(Reservation reservation) {
	        try {
	            double dailyRate = reservationDAO.getDailyRate(reservation.getVehicleID());
	            double totalCost = reservation.calculateTotalCost(dailyRate);
	            reservation.setTotalCost(totalCost);

	            // Insert reservation into the database
	            boolean success = reservationDAO.createReservation(reservation);
	            
	            // Inform the user about the reservation status
	            if (success) {
	                System.out.println("Reservation created successfully! ID: " + reservation.getReservationID());
	            } else {
	            	throw new RuntimeException("Failed to create reservation.");
	            }
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.out.println("Error creating reservation: " + e.getMessage());
	            return false;
	        }
	    }
	 
	 @Override
	 public boolean updateReservation(Reservation reservation) {
	        try {
	            boolean success = reservationDAO.updateReservation(reservation);
	            if (!success) {
	                throw new ReservationException("Failed to update reservation. ID not found: " + reservation.getReservationID());
	            }
	            System.out.println("Reservation updated successfully for ID: " + reservation.getReservationID());
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.out.println("Error updating reservation: " + e.getMessage());
	            return false;
	        } catch (ReservationException e) {
				e.printStackTrace();
				return false;
			}
	    }
	 
	 
	 @Override
	 public boolean cancelReservation(int reservationId) {
	        try {
	            boolean success = reservationDAO.deleteReservation(reservationId);
	            if (!success) {
	                throw new ReservationException("Failed to cancel reservation. ID not found: " + reservationId);
	            }
	            System.out.println("Reservation cancelled successfully for ID: " + reservationId);
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.out.println("Error cancelling reservation: " + e.getMessage());
	            return false;
	        } catch (ReservationException e) {
				e.printStackTrace();
				return false;
			}
	    }
}
