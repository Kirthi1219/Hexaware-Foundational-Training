package service;

import dao.ReservationDAO;
import dao.VehicleDAO;
import entity.Reservation;
import entity.Vehicle;
import exception.DatabaseConnectionException;

import java.util.List;

public class ReportGenerator {
    private final ReservationDAO reservationDAO;
    private final VehicleDAO vehicleDAO;

    public ReportGenerator() {
        this.reservationDAO=new ReservationDAO();
        this.vehicleDAO=new VehicleDAO();
    }

    // Report for available vehicles
    public void generateAvailableVehiclesReport() {
        List<Vehicle> availableVehicles=vehicleDAO.getAvailableVehicles();
        System.out.println("\nAvailable Vehicles Report:");
        if (availableVehicles.isEmpty()) {
            System.out.println("No available vehicles.");
        } else {
            for (Vehicle vehicle:availableVehicles) {
                System.out.println(vehicle);
            }
        }
    }


    // Reservation Report
    public void generateReservationReport() {
        try {
            List<Reservation> reservations= reservationDAO.getAllReservations(); // Fetch all reservations
            System.out.println("\nReservation Report:");
            if (reservations.isEmpty()) {
                System.out.println("No active reservations.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error fetching reservations: " + e.getMessage());
        }
    }
}
