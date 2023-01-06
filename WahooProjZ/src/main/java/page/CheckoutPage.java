package page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.BaseModel;

public class CheckoutPage extends BaseModel {

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By email = By.xpath("//input[@id='customer-email']");
	By firstName = By.xpath("//input[@name='firstname']");
	By lastName = By.xpath("//input[@name='lastname']");
	By address = By.xpath("//input[@name='street[0]']");
	By city = By.xpath("//input[@name='city']");
	By country = By.xpath("//select[@name='country_id']");
	By state = By.xpath("//select[@name='region_id']");
	By zipCode = By.xpath("//input[@name='postcode']");
	By selectAddress = By.cssSelector("[name='candidate']");
	By validationCtn = By.cssSelector("aside:nth-of-type(9) .button.dark.learn_more");
	By phoneNumber = By.xpath("//input[@name='telephone']");
	By shippingMethod = By.xpath("//input[@id='s_method_amstrates_amstrates16']");
	By paymentMothodCredit = By.xpath("//input[@id='amasty_stripe']");
	By paymentCredit = By.xpath("//input[@id='amasty_stripe']/parent::div");
	By cardNum = By.xpath("//input[@name='cardnumber']");
	By cardDate = By.cssSelector("input[name='exp-date']");
	By cvc = By.cssSelector("input[name='exp-date']");
	By placeOrder = By.cssSelector(".-summary.checkout-block button[title='Place Order']");
	By orderStatus = By.cssSelector("#checkout > div.messages > div > div");
	

	public void enterEmail(String Email) {
		waitTillVisible(email, 5);
		driver.findElement(email).sendKeys(Email);
	}

	public void enterFirstName(String Firstname) {
		driver.findElement(firstName).sendKeys(Firstname);
	}

	public void enterLastName(String Lastname) {
		driver.findElement(lastName).sendKeys(Lastname);
	}

	public void enterAddress(String Address) {
		driver.findElement(address).sendKeys(Address);
	}

	public void enterCity(String City) {
		driver.findElement(city).sendKeys(City);
	}

	public void selectCountry(String Country) {
		waitTillNestedElementLocated(country, 3);
		WebElement CountryList = driver.findElement(country);
		Select countries = new Select(CountryList);
		List<WebElement> countryOptions = countries.getOptions();
		for (WebElement i : countryOptions) {
			String item = i.getText();
			if (item.equals(Country)) {
				countries.selectByVisibleText(item);
				break;
			}
		}
	}

	public void selectState(String State) {
		waitTillNestedElementLocated(state, 7);
		WebElement StateList = driver.findElement(state);
		Select states = new Select(StateList);
		List<WebElement> stateOptions = states.getOptions();
		for (WebElement i : stateOptions) {
			String item = i.getText();
			if (item.equals(State)) {
				states.selectByVisibleText(item);
				break;
			}
		}
	}

	public void enterZipCode(String ZipCode) {
		driver.findElement(zipCode).clear();
		driver.findElement(zipCode).sendKeys(ZipCode);
	}

	public void addressValidation() {
		waitTillVisible(selectAddress, 10);
		driver.findElement(selectAddress).click();
	}

	public void clickValidationCtn() {
		driver.findElement(validationCtn).click();
	}

	public void enterPhoneNumb(String PhoneNum) {
		waitTillInputed(phoneNumber, 6);
		driver.findElement(phoneNumber).sendKeys(PhoneNum);
	}

	public void clickShippingMethod() {
		waitTillVisible(shippingMethod, 10);
		driver.findElement(shippingMethod).click();
	}

	public void clickCreditCard() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(paymentMothodCredit));
		WebElement element = driver.findElement(paymentMothodCredit);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

	public void getCardInfo(String num, String date, String cvcNum) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Secure card payment input frame']")));
//		for (int i = driver.findElements(cardNum).size(); i != 1; i = driver.findElements(cardNum).size()) {
//			
//		}
		
		driver.findElement(cardNum).sendKeys(num);
		driver.findElement(cardDate).sendKeys(date);
		driver.findElement(cvc).sendKeys(cvcNum);

		driver.switchTo().parentFrame();
	}

	public void clickPlaceOrder() {
		waitTillClickable(placeOrder, 6);
		WebElement element = driver.findElement(placeOrder);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

	public String status() {
		waitTillVisible(orderStatus, 10);
		return driver.findElement(orderStatus).getText();
	}

}