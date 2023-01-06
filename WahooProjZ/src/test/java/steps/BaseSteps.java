package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSteps {
	
	static WebDriver driver;
	
	@io.cucumber.java.Before("@wahooFullTest")
	public void setUp() {
		driver = getBrowser("Chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public WebDriver getBrowser(String browser) {
		if(driver == null) {
			if(browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		return driver;
	}
	
}