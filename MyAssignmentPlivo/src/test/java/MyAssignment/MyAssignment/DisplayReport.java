package MyAssignment.MyAssignment;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisplayReport extends TestSetupBase {
	// Made webdriver as Static, because to run 10 cases in a single suite, we
	// initialize a webdriver for once
	private static WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void displayIndexHtml() {
		String fname = "test-output\\index.html";
		File f = new File(fname);
		fname = f.getAbsolutePath();
		driver.get(fname);
		WebElement showLink = driver.findElement(By.partialLinkText("show"));
		showLink.click();
		driver.get(fname);
		showLink = driver.findElement(By.partialLinkText("show"));
		showLink.click();
	}
}
