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

import persistance.DbsManager;
import persistance.user.dao.UserDao;
import persistance.user.entity.User;

public class UserCreationTest extends TestCase {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties;

	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		DbsManager.getDatastore();
		properties.load(getClass().getClassLoader().getResourceAsStream(
				"config.properties"));

		System.setProperty("webdriver.chrome.driver",
				properties.getProperty("chrome_installation_path"));

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	private WebElement retryUntilAttached(String id) {
		try {
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			e.printStackTrace();
			return retryUntilAttached(id);
		}
	}

	@Test
	public void testSearchForMain() throws Exception {

		driver.get(properties.getProperty("twitter_view_address"));
		driver.manage().window().maximize();		
		
		Thread.sleep(1000);
		retryUntilAttached("user-management-icon").click();
		WebElement element = null;

		element = retryUntilAttached("user-name-text-field");
		assertNotNull(element);
		element.sendKeys("Poldek");

		element = retryUntilAttached("user-surname-text-field");
		assertNotNull(element);
		element.sendKeys("Banan");

		element = retryUntilAttached("user-email-text-field");
		element.sendKeys("poldek.banan@gmail.com");
		
		element = retryUntilAttached("user-password-text-field");
		element.sendKeys("ala123");
		
		element = retryUntilAttached("user-retyped-text-field");
		element.sendKeys("ala123");
		
		retryUntilAttached("user-save-button").click();

		boolean userCreated = false;
		for (User user: UserDao.get().getAll()) {
			if (user.getEmail().equals("poldek.banan@gmail.com")) {
				userCreated = true;
				break;
			}
		}
		
		assertTrue("New campaign created", userCreated);
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
