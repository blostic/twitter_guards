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
import ui.login.UserUtils;

public class LoginTest extends TestCase {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties;
	private User user;

	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		DbsManager.getDatastore();
		properties.load(getClass().getClassLoader().getResourceAsStream(
				"config.properties"));

		System.setProperty("webdriver.chrome.driver",
				properties.getProperty("chrome_installation_path"));

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		user = UserDao.get().getByEmail("mail@test.com");
		if (user == null) {
			user = new User();
			user.setName("name");
			user.setSurname("surname");
			user.setEmail("mail@test.com");
			String salt = UserUtils.getRandomSalt();
			user.setPasswordHash(UserUtils.getPasswordHash("password", salt));
			user.setSalt(salt);
			UserDao.get().save(user);
		}
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

		WebElement element = retryUntilAttached("login-window-login");
		element.sendKeys(user.getName());
		
		element = retryUntilAttached("login-window-password");
		element.sendKeys("password");
		
		element = retryUntilAttached("login-window-login-button");
		element.click();

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
