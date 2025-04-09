package dao;

import java.util.List;
import entity.Appointment;

public interface IHospitalService {

	//1. get appointment by id 
	Appointment getAppointmentById (int appointmentId);
	
	//2. get list of appointments made by a patient
	List<Appointment> getAppointmentsForPatient(int patientId);
	
	//3. get list of appointments for a doctor
	List<Appointment> getAppointmentsForDoctor(int doctorId);
	
	//4. schedule an appointment
	boolean scheduleAppointment(Appointment appointment);
	
	//5. update an appointment
	boolean updateAppointment(Appointment appointment);
	
	//6. cancel an appointment by id
	boolean cancelAppointment(int appointmentId);
}
