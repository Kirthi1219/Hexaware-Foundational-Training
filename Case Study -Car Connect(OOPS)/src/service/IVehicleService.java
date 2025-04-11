package service;

import entity.Vehicle;
import java.util.List;

public interface IVehicleService {
	Vehicle getVehicleById(int vehicleId);
    List<Vehicle> getAvailableVehicles();
    boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean removeVehicle(int vehicleId);
}
