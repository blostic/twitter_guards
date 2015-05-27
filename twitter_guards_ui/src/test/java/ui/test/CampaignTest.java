package ui.test;

import java.util.Date;
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
import persistance.campaign.dao.CampaignDao;
import persistance.campaign.entity.Campaign;
import persistance.user.dao.UserDao;
import persistance.user.entity.User;
import ui.login.UserUtils;

public class CampaignTest extends TestCase {

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

		element = retryUntilAttached("new-campaign-component");
		assertNotNull(element);
		element.click();

		element = retryUntilAttached("campaign-title-text-field");
		assertNotNull(element);
		element.sendKeys("Kampania-testowa");

		element = retryUntilAttached("campaign-description-text-field");
		assertNotNull(element);
		element.sendKeys("Kampania-testowa-opis");

		retryUntilAttached("campaign-next-button").click();

		element = retryUntilAttached("google-map");
		assertNotNull(element);

		retryUntilAttached("campaign-next-button").click();

		retryUntilAttached("keyword-specification-text-field").sendKeys("ala");
		retryUntilAttached("add-keyword-to-list-button").click();
		retryUntilAttached("keyword-specification-text-field")
				.sendKeys("Wania");
		retryUntilAttached("add-keyword-to-list-button").click();
		retryUntilAttached("keyword-specification-text-field").sendKeys(
				"Malinka");
		retryUntilAttached("add-keyword-to-list-button").click();
		retryUntilAttached("keyword-specification-text-field").sendKeys(
				"dziewczynka");
		retryUntilAttached("add-keyword-to-list-button").click();
		retryUntilAttached("campaign-finish-button").click();
		boolean campaignCreated = false;
		for (Campaign campaign : CampaignDao.get().getAll()) {
			long timeDiff= new Date().getTime() - campaign.getStartDate().getTime();
			if (campaign.getTitle().equals("Kampania-testowa") && timeDiff > 0 && timeDiff < 10000) {
				campaignCreated = true;
				break;
			}
		}
		
		assertTrue("New campaign created", campaignCreated);
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
