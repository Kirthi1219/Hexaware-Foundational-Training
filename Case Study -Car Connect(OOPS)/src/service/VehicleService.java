package service;
import entity.Vehicle;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;

import java.util.ArrayList;
import java.util.List;

import dao.VehicleDAO;

public class VehicleService implements IVehicleService{
	
	 private VehicleDAO vehicleDAO = new VehicleDAO();
	
	    //Finds the vehicle with vehicleId
	    @Override
	    public Vehicle getVehicleById(int vehicleId) {	    	
	    	try {
	    		if (vehicleId <= 0) {
	                throw new InvalidInputException("Invalid vehicle ID: " + vehicleId);
	            }
	            Vehicle vehicle = vehicleDAO.getVehicleById(vehicleId);
	            if (vehicle == null) {
	                throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
	            }
	            System.out.println("Vehicle found:\n" + vehicle);
	            return vehicle;
	        } catch (VehicleNotFoundException e) {
				e.printStackTrace();
				return  null;
			} catch (InvalidInputException e) {
				e.printStackTrace();
				return null;
			}
	    }

	    //Returns a list of available vehicles
	    @Override
	    public List<Vehicle> getAvailableVehicles() {
	    	try {
	        List<Vehicle> availableVehicles = vehicleDAO.getAvailableVehicles();
	        if (availableVehicles.isEmpty()) {
	        	throw new VehicleNotFoundException("No vehicles available.");
	        } else {
	            System.out.println("Available Vehicles:");
	            availableVehicles.forEach(System.out::println);
	        }
	        return availableVehicles;
	    }catch (VehicleNotFoundException e) {
			e.printStackTrace();
			return  null;
		}
	   }

	    //Add a vehicle to the list
	    @Override
	    public boolean addVehicle(Vehicle vehicle) {
	        boolean added = vehicleDAO.addVehicle(vehicle);
	        if (!added) {
                throw new RuntimeException("Vehicle registration failed.");
            }
            System.out.println("Vehicle registered successfully: " + vehicle);
            return added;
	    }
	    
	    //updates an existing vehicle
	    @Override
	    public boolean updateVehicle(Vehicle vehicle) {
	    	try {
	        boolean updated = vehicleDAO.updateVehicle(vehicle);
	        if (!updated) {
                throw new VehicleNotFoundException("Vehicle update failed. ID not found: " + vehicle.getVehicleID());
            }
            System.out.println("Vehicle updated successfully.");
            return updated;
	    	}
	    	catch (VehicleNotFoundException e) {
				e.printStackTrace();
				return false;
			}
	    }

	    //Deletes a vehicle by id
	    @Override
	    public boolean removeVehicle(int vehicleId) {
	    	try {
	            boolean removed = vehicleDAO.removeVehicle(vehicleId);
	            if (!removed) {
	                throw new VehicleNotFoundException("Vehicle removal failed. ID not found: " + vehicleId);
	            }
	            System.out.println("Vehicle removed successfully.");
	            return removed;
	        } 
	    	catch (VehicleNotFoundException e) {
				e.printStackTrace();
				return false;
			}
	    }
	    
	    
	 // Returns a list of all vehicles (irrespective of availability)
	    public List<Vehicle> getAllVehicles() {
	        try {
	            List<Vehicle> allVehicles = vehicleDAO.getAllVehicles();
	            if (allVehicles.isEmpty()) {
	                throw new VehicleNotFoundException("No vehicles found in the system.");
	            } else {
	                System.out.println("All Vehicles:");
	                allVehicles.forEach(System.out::println); //Loops through all vehicle and Prints each one
	            }
	            return allVehicles;
	        } catch (VehicleNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	   
}
