package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import entity.Vehicle;
import service.VehicleService;

public class VehicleServiceTest {
	private VehicleService vehicleService;
	
	@BeforeEach
    public void setUp() {
        vehicleService = new VehicleService();
    }
	
	 @Test
	 public void testGetAvailableVehiclesReturnsNonEmptyList() {
		 List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

	     assertNotNull(availableVehicles, "Available vehicles list should not be null");
	     assertFalse(availableVehicles.isEmpty(), "There should be at least one available vehicle");
	}
}
