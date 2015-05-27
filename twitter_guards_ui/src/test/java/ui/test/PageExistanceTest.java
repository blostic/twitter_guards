package ui.test;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageExistanceTest extends TestCase {
 
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties;
	
	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		
		System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome_installation_path"));
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	private WebElement retryUntilAttached(String id) {
	    try {
	        return driver.findElement(By.id(id));
	    } catch (Exception e) {
	        return retryUntilAttached(id);
	    }
	}
	
	@Test
	public void testSearchForMain() throws Exception {
		driver.get(properties.getProperty("twitter_view_address"));
		assertNotNull(retryUntilAttached("twitter_guards_main_view"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
}
