package MyAssignment.MyAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestSetupBase {

	public enum Browsers {
		chrome, firefox;
	};

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	private void setDriver(String browserType, String appURL) {
		Browsers browserName;
		browserName = Browsers.valueOf(browserType);
		switch (browserName) {
		case chrome:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities desired = DesiredCapabilities.chrome();
			desired.setCapability(ChromeOptions.CAPABILITY, options);
			driver = initChromeDriver(appURL, desired);
			break;
		case firefox:
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL, DesiredCapabilities desired) {
		System.out.println("Launching google chrome..");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(desired);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestSetupBase(@Optional("chrome") String browserType,
			@Optional("http://coviam.com/") String appURL) {
		try {
			setDriver(browserType, appURL);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

}