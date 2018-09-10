package MyAssignment.MyAssignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.EmailAndSmsData;

public class PlivoTest extends TestSetupBase {
	// Made webdriver as Static, because to run 10 cases in a single suite, we
	// initialize a webdriver for once
	private static WebDriver driver;
	private Plivo plivo;
	private List<String> testData = new ArrayList<String>();
	private static int i=0;
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		plivo = new Plivo(driver);
	}
	
	/**
	 * Method to fill test data
	 * 
	 */
	@Parameters({"phoneNumber","phoneMessage", "smtp","port","ssl","username","password","from","to","subject", "cc", "emailMessage"})
	@BeforeClass
	public void fillTestData(String phoneNumber, String phoneMessage, String smtp, String port,String ssl, String username, String password, String from, String to, String subject, String cc, String emailMessage) {
		testData.add(phoneNumber);
		testData.add(phoneMessage);
		testData.add(smtp);
		testData.add(port);
		testData.add(ssl);
		testData.add(username);
		testData.add(password);
		testData.add(from);
		testData.add(to);
		testData.add(subject);
		testData.add(cc);
		testData.add(emailMessage);
	}

	/**
	 * Method to create and verify new page
	 *
	 * @param pageName Title of the page
	 * @param smsName SMS component name
	 * @param emailName EMail component name
	 * @param exitName Exit component name
	 * 
	 */
	@Parameters({ "pageName", "smsName","emailName","exitName"})
	@Test(priority=0)
	public void createApp(String pageName, String smsName, String emailName, String exitName) {
		System.out.println(testData);
		plivo.clickCreateAnApp();
		plivo.clickLetsGetStarted();
		plivo.clickCreateNewPage(pageName);
		boolean pageNameStatus = plivo.verifyPageName(pageName);
		Assert.assertEquals(pageNameStatus, true, "Entered Page Title is getting saved properly");
	}
	
	
	/**
	 * Method to create and verify new page
	 *
	 * @param smsName SMS component name
	 * @param emailName EMail component name
	 * @param exitName Exit component name
	 * @param count Number of components added to verify 
	 */
	@Parameters({ "smsName","emailName","exitName", "count"})
	@Test(priority=1)
	private void addModules(String smsName, String emailName,String exitName, int count) {
		plivo.addSendSMS(smsName, "2", 600,0);
		plivo.enterSMSDetails(testData.get(i++), testData.get(i++));
		plivo.addSendEmail(emailName, "3", 700,200);
		plivo.enterEmailDetails(testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++), testData.get(i++));
		plivo.addExitApp(exitName, "4", 500, 100);
		plivo.addOtherModules(exitName);
		boolean res = plivo.verifyAddedModules();
		int size = plivo.countAddedModules();
		res = ((res == true) && (size == 6))? true : false;
		Assert.assertEquals(res, true, "Ensured that all Modules are added  properly");
	}
	
	/**
	 * Method to verify the added connections
	 *
	 */
	@Test(priority=2)
	private void checkConnections() {
		boolean res = plivo.verifyConnections();
		Assert.assertEquals(res, true, "Ensured that the modules are properly connected");
	}
	
	/**
	 * Method to verify the added values
	 *
	 */
	@Test(priority=3)
	private void  verifyAddedValues() {
		EmailAndSmsData enteredData = plivo.fetchEmailAndSMSDetails();
		int j=0;
		boolean valueRes = enteredData.getPhoneNumber().equals(testData.get(j++)) &&
				enteredData.getPhoneMessage().equals(testData.get(j++)) && 
				enteredData.getEmailSMTP().equals(testData.get(j++)) &&
				enteredData.getEmailPort().equals(testData.get(j++)) &&
				enteredData.getEmailSSL().equals(testData.get(j++)) &&
				enteredData.getEmailUsername().equals(testData.get(j++)) &&
				enteredData.getEmailPassword().equals(testData.get(j++)) &&
				enteredData.getEmailFrom().equals(testData.get(j++)) &&
				enteredData.getEmailTo().equals(testData.get(j++)) &&
				enteredData.getEmailSubject().equals(testData.get(j++)) &&
				enteredData.getEmailCc().equals(testData.get(j++)) &&
				enteredData.getEmailMessage().equals(testData.get(j++));
		Assert.assertEquals(valueRes, true, "Entered details in SMS and Email modules are verified");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}