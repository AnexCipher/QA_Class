package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.CartPage;
import page.CheckoutPage;
import page.HomePage;
import page.ProductPage;

public class WahooFullSteps {
	
	static WebDriver driver = BaseSteps.driver;
	HomePage homepage = new HomePage(driver);
	ProductPage productpage = new ProductPage(driver);
	CartPage cart = new CartPage(driver);
	CheckoutPage checkoutapage = new CheckoutPage(driver);
	
	
	@Given("I am in the home page {string} of Wahoo.")
	public void i_am_in_the_home_page_of_wahoo(String url) {
	    driver.get(url);
	    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@When("I click on show all products.")
	public void i_click_on_show_all_products() {
		homepage.killPopUp();
		homepage.getShowAll();
	}

	@When("I randomly select products until there are two different products in the cart.")
	public void i_randomly_select_products_until_there_are_two_different_products_in_the_cart() {
		productpage.selectProduct();
	}

	@When("I remove an item from the cart, then click view cart.")
	public void i_remove_an_item_from_the_cart_then_click_view_cart() {
		cart.getCart();
		cart.getRemove();
		cart.getOkRemove();
		cart.getEditCart();
	}

	@When("I update the item quantity {string}, click on update cart, then verify price change. Next I proceed to check out.")
	public void i_update_the_item_quantity_click_on_update_cart_then_verify_price_change(String string) {
		cart.totalPriceChecker("3");
	}

	@When("I fill out customer shipping information {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}.")
	public void i_fill_out_customer_shipping_information(String email, String firstName, String lastName, String address, String city, String country, String state, String zipCode, String phoneNum) {
		checkoutapage.enterEmail(email)
;
		checkoutapage.enterFirstName(firstName);
		checkoutapage.enterLastName(lastName);
		checkoutapage.enterAddress(address);
		checkoutapage.enterCity(city);
		checkoutapage.selectCountry(country);
		checkoutapage.selectState(state);
		checkoutapage.enterZipCode(zipCode);
		checkoutapage.addressValidation();
		checkoutapage.clickValidationCtn();
		checkoutapage.clickShippingMethod();
		checkoutapage.enterPhoneNumb(phoneNum); 
	}

	@When("I fill out the payment method {string}, {string}, {string}.")
	public void i_fill_out_the_payment_method(String creditNum, String creditExpDate, String creditCV) throws InterruptedException {
		checkoutapage.clickCreditCard();
		checkoutapage.getCardInfo(creditNum, creditExpDate, creditCV);
	}

	@When("I place the order.")
	public void i_place_the_order() {
		checkoutapage.clickPlaceOrder();
	}

	@Then("I verify message {string} to verify functionality.")
	public void i_verify_the_to_verify_functionality(String errorMessage) {
		Assert.assertEquals(checkoutapage.status(), errorMessage);
	}
	
}