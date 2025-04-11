package exception;

public class AuthenticationException extends Exception {
	//Constructor with a custom error message
	public AuthenticationException(String message) {
		super(message); /// Calls the constructor of the parent class (Exception)
	}
}
