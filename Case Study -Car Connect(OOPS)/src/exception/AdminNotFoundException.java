package exception;

public class AdminNotFoundException extends Exception{
	
	//Constructor
	public AdminNotFoundException(String message) {
		super(message); //calls the parent class’s constructor (Exception) and passes the message to it
	}
}
