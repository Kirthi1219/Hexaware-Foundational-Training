/*This is like the blueprint or model of a customer. It defines who a customer is – their ID, name, email, phone number, etc. 
It has a toString() method to describe itself and an authenticate() method to verify the password.*/

package entity;

public class Customer {
	
	//INSTANCE VARIABLES
	private int customerID;
	private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    private String registrationDate;
    
    //CONSTRUCTOR
    
    public Customer() {
       
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address, String username, String password, String registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }
    
    public Customer(int customerID,String firstName, String lastName, String email, String phoneNumber, String address, String username, String password, String registrationDate) {
        this.customerID=customerID;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }
    
    // GETTERS and SETTERS
    public int getCustomerID() {return customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    
    public String getRegistrationDate() {return registrationDate;}
    public void setRegistrationDate(String registrationDate) {this.registrationDate = registrationDate;}
    
    //METHODS
    public boolean authenticate(String inputPassword) {
    	return this.password.equals(inputPassword);
    }
    
    @Override
    //The toString() method is used to provide a string representation of an object.
    public String toString() {
        return 
               "\n"+"Customer ID: " + customerID + "\n" +
               "First Name: " + firstName + "\n" +
               "Last Name: " + lastName + "\n" +
               "Email: " + email + "\n" +
               "Phone Number: " + phoneNumber + "\n" +
               "Address: " + address + "\n" +
               "Username: " + username + "\n" +
               "Password: " + password + "\n" +
               "Registration Date: " + registrationDate;
    }
}
