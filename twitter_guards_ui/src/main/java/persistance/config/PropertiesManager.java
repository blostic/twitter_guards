package persistance.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

	private static Properties properties = new Properties();
	private static String propFileName = "config.properties";
	
	private static InputStream inputStream;

	static {		
		try {
			new PropertiesManager();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private PropertiesManager() throws IOException {
		inputStream = getClass().getClassLoader().getResourceAsStream(getPropFileName());
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + getPropFileName() + "' not found in the classpath");
		}
	}
	
	public static String getPropFileName() {
		return propFileName;
	}
	
	public static String getProperty(String propertyKey) {
		return (String)properties.get(propertyKey);
	}
	
	public void setPropFileName(String propFileName) {
		PropertiesManager.propFileName = propFileName;
		PropertiesManager.inputStream = getClass().getClassLoader().getResourceAsStream(getPropFileName());
	}

}
