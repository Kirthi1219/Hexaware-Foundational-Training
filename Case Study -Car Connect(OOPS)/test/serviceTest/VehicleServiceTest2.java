package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.Vehicle;
import service.VehicleService;
import java.util.List;


public class VehicleServiceTest2 {

	private VehicleService vehicleService;
	
	@BeforeEach
    public void setUp() {
        vehicleService = new VehicleService();
    }
	
	@Test
	public void testGetAllVehicles() {
		List<Vehicle> allVehicles = vehicleService.getAllVehicles();
	
        assertNotNull(allVehicles, "Vehicle list should not be null");
        assertTrue(allVehicles.size() >= 0, "Vehicle list size should be 0 or more");
	}
	
	
}
