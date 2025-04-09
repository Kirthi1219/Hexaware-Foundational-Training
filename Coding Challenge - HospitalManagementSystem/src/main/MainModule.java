package main;

import java.util.List;

import dao.HospitalServiceImpl;
import entity.Appointment;

public class MainModule {
	public static void main(String[] args) {
		
		HospitalServiceImpl service = new HospitalServiceImpl();
		
		//fetch an apoointment by id
//		System.out.println("---- Fetch Appointment By ID ----");
//        Appointment apptById=service.getAppointmentById(301);
//        if (apptById != null) {
//            System.out.println(apptById);
//        } else {
//            System.out.println("No appointment found with the given ID.");
//        }
        
        
		//to fetch the appointments for a patient 
//        System.out.println("---- Fetch Appointments For Patient ----");
//        List<Appointment>patientAppointments =service.getAppointmentsForPatient(101);
//        for (Appointment a:patientAppointments) {
//            System.out.println(a);
//        }

		  //to fetch appointemnets for a doctor
//		  System.out.println("---- Fetch Appointments For Doctor ----");
//		  List<Appointment>doctorAppointments=service.getAppointmentsForDoctor(203);
//		  for (Appointment a:doctorAppointments) {
//            System.out.println(a);
//		  }
		
		 //to schedule a new appointment
//		 System.out.println("---- Schedule New Appointment ----");
//	     Appointment a1=new Appointment(306,101,201, "2025-04-20","Follow-up consultation");
//	     boolean scheduled = service.scheduleAppointment(a1);
//	     System.out.println("Appointment Scheduled:" + scheduled);
		
	     //to update an appointment
//		 System.out.println("---- Update Appointment ----");
//	     a1.setDescription("Follow-up with new symptoms");
//	     boolean updated = service.updateAppointment(a1);
//	     System.out.println("Appointment Updated:" + updated);
	     
//	     List<Appointment>patientAppointments =service.getAppointmentsForPatient(101);
//	     for (Appointment a:patientAppointments) {
//           System.out.println(a);
//	     }
	     
	     //cancel an appointment
//	     System.out.println("---- Cancel Appointment ----");
//	     boolean cancelled = service.cancelAppointment(306);
//	     System.out.println("Appointment Cancelled:" + cancelled);
		
	     
	}
}
