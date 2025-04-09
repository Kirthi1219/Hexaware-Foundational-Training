package entity;

public class Doctor {
	 private int doctorId;
	 private String firstName;
	 private String lastName;
	 private String specialization;
	 private String contactNumber;
	 
	 //CONSTRUCTOR
	 public Doctor(int doctorId, String firstName, String lastName, String specialization, String contactNumber) {
	        this.doctorId = doctorId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.specialization = specialization;
	        this.contactNumber = contactNumber;
	    }
	 
	 public int getDoctorId() {
	        return doctorId;
	    }

	    public void setDoctorId(int doctorId) {
	        this.doctorId= doctorId;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName= firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName= lastName;
	    }

	    public String getSpecialization() {
	        return specialization;
	    }

	    public void setSpecialization(String specialization) {
	        this.specialization= specialization;
	    }

	    public String getContactNumber() {
	        return contactNumber;
	    }

	    public void setContactNumber(String contactNumber) {
	        this.contactNumber= contactNumber;
	    }

	    public String toString() {
	        return "Doctor Details:\n" +
	               "Doctor ID      : " + doctorId + "\n" +
	               "First Name     : " + firstName + "\n" +
	               "Last Name      : " + lastName + "\n" +
	               "Specialization : " + specialization + "\n" +
	               "Contact Number : " + contactNumber + "\n";
	    }
	    
	    public void printDetails() {
	        System.out.println(this.toString());
	    }
}
