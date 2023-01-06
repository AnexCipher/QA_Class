package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import page.HomePage;

public class BaseTest {
	
	public WebDriver driver;
	public HomePage homepage;
	
	public WebDriver initializeDriver() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public HomePage setUp() {
		driver = initializeDriver();
		homepage = new HomePage(driver);
		
		homepage.getUrl();
		return homepage;
	}
}
