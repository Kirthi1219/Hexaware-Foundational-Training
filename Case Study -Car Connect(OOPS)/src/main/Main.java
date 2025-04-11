package main;

import dao.DatabaseContext;
import entity.Admin;
import entity.Customer;
import entity.Reservation;
import entity.Vehicle;
import service.AdminService;
import service.AuthenticationService;
import service.CustomerService;
import service.ReportGenerator;
import service.ReservationService;
import service.VehicleService;

public class Main {

	public static void main(String[] args) {
		DatabaseContext.getConnection();
		Customer customer1 = new Customer(
	            "John", 
	            "Doe", 
	            "john.doe@example.com", 
	            "9876543210", 
	            "123 Main Street, New York, NY", 
	            "john_doe", 
//	            "secure123",
	            "securePass123", 
	            "2025-04-03"
	        );
		CustomerService cs=new CustomerService();
//		cs.registerCustomer(customer1);
//		cs.getCustomerById(1);
//		cs.getCustomerByUsername("manukarthick");
//		cs.updateCustomer(customer1);
//		cs.deleteCustomer(6);
		
		Reservation res1=new Reservation(3,2,"2025-04-03","2025-04-06",0.0,"pending");
		ReservationService rs=new ReservationService();
//		rs.getReservationById(1);
//		rs.getReservationsByCustomerId(2);
//		rs.createReservation(res1);
//		rs.updateReservation(res1);
//		rs.cancelReservation(6);
		
		Admin a1=new Admin("senthil","kumar","senthil25@gmail.com","9876768612","kumar25","Kksen@2325","Manager","2025-04-05");
		Admin a2=new Admin("Vasanth","Vairam","vasanthvv00@gmail.com","9176708612","vasavairam01","Vavaram@123$0$","Admin","2025-04-11");
		AdminService as=new AdminService();
//		as.getAdminById(6);
//		as.getAdminByUsername("amitsharma");
//		as.registerAdmin(a2);
//		as.updateAdmin(a2);
//		as.deleteAdmin(5);
	
		Vehicle v1=new Vehicle("kia","hyndai",2024,"black","KN057J123",false,100.00);
		VehicleService vs=new VehicleService();
//		vs.getAvailableVehicles();
//		vs.getVehicleById(1);
//		vs.addVehicle(v1);
//		vs.updateVehicle(v1);
//		vs.removeVehicle(6);
		
		//AUTHENTICATION SERVICE
//		AuthenticationService a=new AuthenticationService();
//		a.login("kirthidevi", "Abc@1234");
//		a.login("priyakani", "secure@99");
//		a.loginAdmin("rahulverma", "Rahul@123");
//		a.register(customer1);
//		Admin a1=new Admin(5,"kavin","kumar","kavin1125@gmail.com","9876768612","kevin25","Kksen@2325","Super admin","2025-04-05");
//		a.registerAdmin(a1);
		
		//REPORT GENERATOR
		ReportGenerator rg=new ReportGenerator();
//		rg.generateAvailableVehiclesReport();
//		rg.generateReservationReport();
	}

}
