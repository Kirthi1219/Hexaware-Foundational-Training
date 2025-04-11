package daoTest;

import dao.CustomerDAO;
import entity.Customer;
import exception.DatabaseConnectionException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

	 private static CustomerDAO customerDAO;
	 private static int testCustomerId;
	 
	@BeforeAll
    public static void setUp() throws DatabaseConnectionException {
        customerDAO=new CustomerDAO();

        // Create a test customer
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("Test");
        testCustomer.setLastName("User");
        testCustomer.setEmail("testuser@example.com");
        testCustomer.setPhoneNumber("1234567890");
        testCustomer.setAddress("123 Test St");
        testCustomer.setUsername("testuser123");
        testCustomer.setPassword("password");
        testCustomer.setRegistrationDate("2024-04-07");

        boolean created = customerDAO.createCustomer(testCustomer);
        assertTrue(created, "Failed to insert test customer");

        // Fetch the inserted customer to get the generated ID
        Customer inserted = customerDAO.getCustomerByUsername("testuser123");
        assertNotNull(inserted);
        testCustomerId = inserted.getCustomerID();

        System.out.println("Test customer inserted with ID:" + testCustomerId);
    }
	
	
	@Test
    public void testUpdateCustomerInformation() throws DatabaseConnectionException {
        // Fetch the customer
        Customer customer=customerDAO.getCustomerById(testCustomerId);
        assertNotNull(customer);

        // Update customer details
        customer.setEmail("updatedemail@example.com");
        customer.setPhoneNumber("9876543210");
        boolean updated = customerDAO.updateCustomer(customer);
        assertTrue(updated, "Update operation failed");
        System.out.println("Customer updated successfully!");

        // Fetch again and verify
        Customer updatedCustomer=customerDAO.getCustomerById(testCustomerId);
        assertEquals("updatedemail@example.com", updatedCustomer.getEmail());
        assertEquals("9876543210", updatedCustomer.getPhoneNumber());
    }
	
	@AfterAll
    public static void tearDown() throws DatabaseConnectionException {
        boolean deleted = customerDAO.deleteCustomer(testCustomerId);
        assertTrue(deleted, "Failed to delete test customer");
        System.out.println("Test customer deleted successfully!");
    }
	
}
