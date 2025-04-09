package entity;

public class Patient {
	private int patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;
    
    //CONSTRUCTOR
    public Patient(int patientId,String firstName,String lastName,String dateOfBirth,String gender,String contactNumber,String address) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }
    
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId= patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth= dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender =gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber= contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }
    
    public String toString() {
        return "Patient Details:\n" +
               "Patient ID     : " + patientId + "\n" +
               "First Name     : " + firstName + "\n" +
               "Last Name      : " + lastName + "\n" +
               "Date of Birth  : " + dateOfBirth + "\n" +
               "Gender         : " + gender + "\n" +
               "Contact Number : " + contactNumber + "\n" +
               "Address        : " + address + "\n";
    }
    
    public void printDetails() {
        System.out.println(this.toString());
    }
    
}
