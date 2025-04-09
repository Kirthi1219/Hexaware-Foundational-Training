package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import exception.PatientNumberNotFoundException;
import util.DBConnection;
	
public class HospitalServiceImpl implements IHospitalService{
	
	private Connection conn;
	
	public HospitalServiceImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	//1
	public Appointment getAppointmentById(int appointmentId) {
		Appointment appointment = null;
		try {
			String query= "select * from appointment where appointmentId = ?";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, appointmentId);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
                appointment=new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                );
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return appointment;
	}
	
	//2
	public List<Appointment> getAppointmentsForPatient(int patientId){
		List<Appointment> list=new ArrayList<>();
		try {
			String query ="select * from appointment where patientId = ?";
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment =new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                );
                list.add(appointment);
            }
            if (list.isEmpty()) {
                throw new PatientNumberNotFoundException("No appointments found for Patient ID: " + patientId);
            }
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (PatientNumberNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//3
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> list= new ArrayList<>();
        try {
            String query="select * from appointment where doctorId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment =new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                );
                list.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	//4
	public boolean scheduleAppointment(Appointment appointment) {
        boolean result=false;
        try {
            String query ="insert into appointment (appointmentId, patientId, doctorId, appointmentDate, description) values (?, ?, ?, ?, ?)";
            PreparedStatement ps =conn.prepareStatement(query);
            ps.setInt(1,appointment.getAppointmentId());
            ps.setInt(2,appointment.getPatientId());
            ps.setInt(3,appointment.getDoctorId());
            ps.setString(4,appointment.getAppointmentDate());
            ps.setString(5,appointment.getDescription());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public boolean updateAppointment(Appointment appointment) {
        boolean result= false;
        try {
            String query="update appointment set patientId =?, doctorId =?, appointmentDate=?, description= ? WHERE appointmentId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,appointment.getPatientId());
            ps.setInt(2,appointment.getDoctorId());
            ps.setString(3,appointment.getAppointmentDate());
            ps.setString(4,appointment.getDescription());
            ps.setInt(5,appointment.getAppointmentId());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public boolean cancelAppointment(int appointmentId) {
        boolean result=false;
        try {
            String query= "delete from appointment where appointmentId =?";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1, appointmentId);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
