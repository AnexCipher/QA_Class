package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.BaseModel;

public class CartPage extends BaseModel{

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By cart = By.cssSelector(".wf-cart");
	By remove = By.cssSelector("li:nth-of-type(1) > .product a[title='Remove item']");
	By ok = By.cssSelector(".action-accept.action-primary");
	By removedConfirm = By.xpath("//div[@id='minicart-content-wrapper']/span/span[.='Item was removed successfully']");
	By viewEdit = By.cssSelector(".action.viewcart");
	By quantity = By.cssSelector(".input-text.qty");
	By updateCart = By.cssSelector("button[name='update_cart_action']");
	By total = By.cssSelector(".grand.totals > .amount  .price");
	By unitPrice = By.cssSelector(".col.price > .price-excluding-tax  .price");
	By checkout = By.cssSelector("button[title='Proceed to Checkout']");

	public void getCart() {
		driver.findElement(cart).click();
	}

	public void getRemove() {
		driver.findElement(remove).click();
	}

	public void getOkRemove() {
		driver.findElement(ok).click();
	}

	public WebElement getConfirm() {
		return driver.findElement(removedConfirm);
	}

	public void getEditCart() {
		driver.findElement(viewEdit).click();
	}

	public WebElement getItemQuantity() {
		return driver.findElement(quantity);
	}

	public WebElement getUpdateCart() {
		return driver.findElement(updateCart);
	}

	public WebElement getTotal() {
		return driver.findElement(total);
	}

	public WebElement getUnitPrice() {
		return driver.findElement(unitPrice);
	}

	public void getCheckout() {
		driver.findElement(checkout).click();
	}
	
	public void totalPriceChecker(String quantity) {
		String TotalPriceA = getTotal().getText();
		WebElement ItemQuantity = getItemQuantity();
		ItemQuantity.clear();
		ItemQuantity.sendKeys(quantity);
		WebElement UpdateCartBtn = getUpdateCart();
		UpdateCartBtn.click();
		String TotalPriceB = getTotal().getText();
		if(!(TotalPriceA.equalsIgnoreCase(TotalPriceB))) {
			System.out.println("Total Price has updated.");
			getCheckout();
		} else {
			System.out.println("Total Price was not updated.");
		}
	}

}
