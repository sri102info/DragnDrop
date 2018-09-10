package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectUtilities {
	public WebDriver driver;
	
	public PageObjectUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void scrolltoView(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static boolean isElementPresent(By by, WebDriver driver) {
		boolean present;
		try {
			driver.findElement(by);
			present = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			present = false;
		}
		return present;
	}
}
