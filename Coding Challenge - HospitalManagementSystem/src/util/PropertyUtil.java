package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties; // a built-in java class that stores key-value pairs from property files.

public class PropertyUtil {
	
	public static Properties getDbProperties() {
		Properties props = new Properties();
		 try {
	            FileInputStream fis = new FileInputStream("resources/db.properties");
	            props.load(fis);
	        } catch (IOException e) {
	            System.out.println("Error reading properties file: " + e.getMessage());
	        }
	        return props;
	}
}
