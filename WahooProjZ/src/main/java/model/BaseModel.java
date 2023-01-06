package model;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseModel {

	protected WebDriver driver;

	public BaseModel(WebDriver driver) {
		this.driver = driver;
	}
	
	public Select selector(By element, int x) {
		Select item = new Select(driver.findElement(element));
		item.selectByIndex(x);
		return item;
	}
	
	public void goBack() {
		driver.navigate().back();
	}
	
	public WebElement randomiser(List<WebElement> x	) {
		Random random = new Random();
		int maxNumOfProduct = x.size();
		int i = random.nextInt(maxNumOfProduct - 1);
		WebElement product = x.get(i);
		return product;
	}
	
	//////   WAITS   \\\\\\	
	public void waitTillVisible(By element, int x) {
		WebDriverWait wait = new WebDriverWait(driver, x);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void waitTillInputed(final By element, int x) {
		WebDriverWait wait = new WebDriverWait(driver, x);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(element).getAttribute("telephone") != " ";
			}
		});
	}
	
	public void waitTillClickable(By element, int x) {
		WebDriverWait wait = new WebDriverWait(driver, x);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitTillNestedElementLocated(By element, int x) {
		WebDriverWait wait = new WebDriverWait(driver, x);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.tagName("option")));
	}
	
}