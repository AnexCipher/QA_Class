package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.BaseModel;

public class HomePage extends BaseModel {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By ride = By.xpath("//nav[@id='top_nav']//a[@title='Ride']/span[.='RIDE']");
	By popUp = By.cssSelector("[aria-busy] div:nth-child(60) .dy-lb-close");
	By cookies = By.cssSelector("button#onetrust-accept-btn-handler");

	public void getUrl() {
		driver.get("https://wahoofitness.com/");
	}
	
	public WebElement getPopUp() {
		WebElement PopUp;
		int numOfPops = driver.findElements(popUp).size();
		if (numOfPops > 0) {
			PopUp = driver.findElement(popUp);
		} else {
			PopUp = null;
		}
		return PopUp;
	}

	public WebElement getCookies() {
		WebElement Cookies;
		int numOfPops = driver.findElements(cookies).size();
		if (numOfPops > 0) {
			Cookies = driver.findElement(cookies);
		} else {
			Cookies = null;
		}
		return Cookies;
	}
	
	public void killPopUp() {
		WebElement popUpBtn = getPopUp();
		WebElement cookiesPopUpBtn = getCookies();
		if (popUpBtn == null) {
			cookiesPopUpBtn.click();
		} else {
			popUpBtn.click();
			cookiesPopUpBtn.click();
		}
	}
	
	public void getShowAll() {
		driver.findElement(ride).click();
	}

}
