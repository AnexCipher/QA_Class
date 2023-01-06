package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import model.BaseModel;

public class ProductPage extends BaseModel{
	
	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By productColour = By.xpath("//select[@id='attribute92']");
	By productSize = By.cssSelector("select[name*='super_attribute[2']");
	By productList = By.cssSelector("[id='section-4'] > div:nth-child(2) > .category-products > .products-grid > li > .text > .product-name > a");
	By addToCartBtn = By.cssSelector("button#product-addtocart-button > span");
	By cartQty = By.cssSelector(".cart-qty.count");
	
	
	public WebElement getProductColour() {
		WebElement productCol;
		int numOfColours = driver.findElements(productColour).size();
		if (numOfColours > 0) {
			productCol = driver.findElement(productColour);
		} else {
			productCol = null;
		}
		return productCol;
	}
	
	public WebElement getProductSize() {
		WebElement productsize;
		int numOfSizes = driver.findElements(productSize).size();
		if(numOfSizes > 0) {
			productsize = driver.findElement(productSize);
		} else {
			productsize = null;
		}
		return productsize;
	}
	
	public List<WebElement> getProductList() {
		return driver.findElements(productList);
	}
	
	public void getProduct() {
		WebElement product = randomiser(getProductList());
		Actions action = new Actions(driver);
		action.click(product).perform();
	}
	
	public WebElement getAddToCartBtn() {
		return driver.findElement(addToCartBtn);
	}

	public WebElement getCartQty() {
		return driver.findElement(cartQty);
	}
	
	public void selectProduct() {
		String cartQty = getCartQty().getText();
		while(!(cartQty.equalsIgnoreCase("2"))) {
			cartQty = getCartQty().getText();
			
			if (cartQty.equalsIgnoreCase("2")) {
				break;
			}
			
			getProduct();
			WebElement colour = getProductColour();
			WebElement size = getProductSize();
			
			if (colour != null) {
				if (colour.isEnabled()) {
					selector(productColour, 1);
					if (size != null) {
						selector(productSize, 1);
						getAddToCartBtn().click();
						goBack();
					} else {
						getAddToCartBtn().click();
						goBack();
					}
				} else {
					selector(productSize, 1);
					selector(productColour, 1);
					getAddToCartBtn().click();
					goBack();
				}
			} else {
				driver.navigate().back();
			}
			
		}
	}
}
