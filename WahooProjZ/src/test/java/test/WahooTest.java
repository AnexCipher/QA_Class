package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import library.ExcelReader;
import page.CartPage;
import page.CheckoutPage;
import page.HomePage;
import page.ProductPage;

public class WahooTest extends BaseTest {

	@Test(dataProvider = "WahooCheckoutData")
	public void Wahoo(String email, String firstName, String lastName, String address, String city, String state,
			String country, String zipCode, String phoneNumber, String cDNum, String cDExpDate, String cDCV)
			throws InterruptedException {
		HomePage homepage = new HomePage(driver);
		homepage.killPopUp();
		homepage.getShowAll();

		ProductPage productpage = new ProductPage(driver);
		productpage.selectProduct();

		CartPage cart = new CartPage(driver);
		cart.getCart();
		Thread.sleep(2000);
		cart.getRemove();
		Thread.sleep(2000);
		cart.getOkRemove();
		Thread.sleep(2000);
		cart.getEditCart();
		Thread.sleep(2000);
		cart.totalPriceChecker("3");

		CheckoutPage checkoutapage = new CheckoutPage(driver);
		checkoutapage.enterEmail(email);
		checkoutapage.enterFirstName(firstName);
		checkoutapage.enterLastName(lastName);
		checkoutapage.enterAddress(address);
		checkoutapage.enterCity(city);
		checkoutapage.selectCountry(country);
		Thread.sleep(1000);
		checkoutapage.selectState(state);
		checkoutapage.enterZipCode(zipCode);
		Thread.sleep(2000);
		checkoutapage.addressValidation();
		checkoutapage.clickValidationCtn();
		Thread.sleep(2000);
		checkoutapage.enterPhoneNumb(phoneNumber);
		Thread.sleep(8000);
		checkoutapage.clickCreditCard();
		checkoutapage.getCardInfo(cDNum, cDExpDate, cDCV);
		Thread.sleep(2000);
		checkoutapage.clickPlaceOrder();
		Thread.sleep(5000);
		Assert.assertEquals(checkoutapage.status(), "Your card was declined. Your request was in live mode, but used a known test card.");

	}

	@DataProvider(name = "WahooCheckoutData")
	public Object[][] getData() throws IOException {
		Object[][] x;
		String filename = "data/WahooCheckoutData.xlsx";
		String sheetname = "CheckoutData";
		ExcelReader eReader = new ExcelReader(filename, sheetname);
		x = eReader.excelToArray();
		return x;
	}

}
