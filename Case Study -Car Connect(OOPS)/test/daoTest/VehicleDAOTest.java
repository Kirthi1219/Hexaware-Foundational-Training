package daoTest;

import dao.VehicleDAO;
import entity.Vehicle;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleDAOTest {

	private static VehicleDAO vehicleDAO;
    private static int testVehicleId;
    
    @BeforeAll
    public static void setUp() {
        vehicleDAO=new VehicleDAO();
    }
    
    @Test
    public void testAddVehicle() {
        Vehicle testVehicle = new Vehicle();
        testVehicle.setVehicleID(999);
        testVehicle.setModel("City");
        testVehicle.setMake("Honda");
        testVehicle.setYear(2022);
        testVehicle.setColor("Red");
        testVehicle.setRegistrationNumber("TN99ZZ9999");
        testVehicle.setAvailability(true);
        testVehicle.setDailyRate(1500.0);

        boolean added=vehicleDAO.addVehicle(testVehicle);
        assertTrue(added,"Failed to add vehicle");
        
        testVehicleId = testVehicle.getVehicleID();
        System.out.println("Test vehicle inserted with ID: " + testVehicleId);
    
    }
	
    @Test
    public void testUpdateVehicleDetails() {
        // Fetch the vehicle
        Vehicle vehicle= vehicleDAO.getVehicleById(testVehicleId);
        assertNotNull(vehicle,"Vehicle not found");

        // Update vehicle details
        vehicle.setColor("Blue");
        vehicle.setDailyRate(65.0);

        boolean updated= vehicleDAO.updateVehicle(vehicle);
        assertTrue(updated,"Failed to update vehicle");

        // Fetch again and verify
        Vehicle updatedVehicle =vehicleDAO.getVehicleById(testVehicleId);
        assertEquals("Blue", updatedVehicle.getColor());
        assertEquals(65.0, updatedVehicle.getDailyRate());
        System.out.println("Vehicle details updated successfully");
    }
    
    @AfterAll
    public static void tearDown() {
        boolean deleted=vehicleDAO.removeVehicle(testVehicleId);
        assertTrue(deleted,"Failed to delete test vehicle");
        System.out.println("Test vehicle deleted with ID:" + testVehicleId);
    }
}
