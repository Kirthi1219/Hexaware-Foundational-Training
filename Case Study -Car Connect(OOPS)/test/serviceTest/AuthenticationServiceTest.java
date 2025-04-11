package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.BeforeEach;
import service.AuthenticationService;

public class AuthenticationServiceTest {
	private AuthenticationService authService;

	@BeforeEach
	public void setUp() {
	    authService=new AuthenticationService();
	}
	
	@Test
    public void testLoginWithValidCredentials() {
        boolean result =authService.login("kirthidevi", "Abc@1234");
        assertTrue(result, "Login should succeed for valid credentials");
    }

	@Test
    public void testLoginWithInvalidUsername() {
        // Try logging in with a non-existent user
        boolean result=authService.login("priya", "secure@99");
        assertFalse(result, "Login should fail for an invalid username");
    }
	
	 @Test
	 public void testLoginWithWrongPassword() {
		 // Assuming "testuser" exists but with a different password
	     boolean result=authService.login("kirthidevi", "Abc@234");
	     assertFalse(result, "Login should fail for a wrong password");
	  }
	 

	 @Test
	 public void testLoginWithEmptyCredentials() {
	     boolean result=authService.login("", "");
	     assertFalse(result, "Login should fail with empty credentials");
	 }
}
