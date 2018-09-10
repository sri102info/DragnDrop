package MyAssignment.MyAssignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.EmailAndSmsData;
import utilities.PageObjectUtilities;

public class Plivo extends PageObjectUtilities {

	private By createApp = By.id("link-create");
	private By letsGetStart = By.cssSelector("button.ui-corner-all.done.ui-button.ui-state-default");
	private By newPage = By.id("add-page");
	private By newPageName = By.cssSelector("input.indented.submitonenter");
	private By newPageCreate = By.cssSelector("button.ui-corner-all.ui-button.ui-state-default");
	private By messagingSection = By.partialLinkText("Messaging");
	private By messageDiv = By.xpath("//a[contains(text(),'Messaging')]/parent::h3/following-sibling::div[contains(@class, 'active')]");
	private By basicSection = By.partialLinkText("Basic");
	private By basicDiv = By.xpath("//a[contains(text(),'Basic')]/parent::h3/following-sibling::div[contains(@class, 'active')]");

	private By phoneNumberBy = By.xpath("//div[@id='module-2']//textarea[@name= 'phone_constant']");
	private By phoneMessageBy = By.xpath("//div[@id='module-2']//tbody//div[@class='syn-selectappvar-wrap']//textarea[@name='message_phrase[]']");
	private By smtpBy = By.xpath("//input[@name='smtp_url']");
	private By portBy = By.xpath("//input[@name='port']");
	private By sslBy = By.xpath("//input[@name='ssl']");
	private By usernameBy = By.xpath("//div[@class='left']//input[@name='username']");
	private By passwordBy = By.xpath("//input[@name='password']");
	private By fromBy = By.xpath("//textarea[@name='from_constant']");
	private By toBy = By.xpath("//textarea[@name='to_constant']");
	private By subjectBy = By.xpath("//textarea[@name='subject_constant']");
	private By ccBy = By.xpath("//textarea[@name='cc_constant']");
	private By emailMessageBy = By.xpath("//div[@class='panel-subsection']//tbody//div[@class='syn-selectappvar-wrap']//textarea[@name='message_phrase[]']");
	
	public WebDriverWait wait = new WebDriverWait(driver, 200);
	Actions actions = new Actions(driver);
	EmailAndSmsData emailSMS = new EmailAndSmsData();
	String val = "module-";
	static List<String> webele = new ArrayList<String>();
	int i = 0;

	/**
	 * Constructor to instantiate the driver
	 * 
	 * @param driver Webdriver instance to initialize
	 *
	 */
	public Plivo(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click the button Create an App
	 *
	 */
	public void clickCreateAnApp() {
		WebElement createAppButton = driver.findElement(createApp);
		if (createAppButton.isDisplayed() && createAppButton.isEnabled())
			createAppButton.click();
	}

	/**
	 * Click the button Lets Get Start
	 *
	 */
	public void clickLetsGetStarted() {
		WebElement letsGetStartedButton = driver.findElement(letsGetStart);
		if (letsGetStartedButton.isDisplayed() && letsGetStartedButton.isEnabled())
			letsGetStartedButton.click();
	}

	/**
	 * Click the button Create New Page
	 * 
	 * @param title Name to be given for the title
	 */
	public void clickCreateNewPage(String title) {
		WebElement createNewPage = driver.findElement(newPage);
		createNewPage.click();
		WebElement createnewPageName = driver.findElement(newPageName);
		if (createnewPageName.isDisplayed() && createnewPageName.isEnabled())
			createnewPageName.click();
		createnewPageName.sendKeys(title);
		WebElement newPageCreation = driver.findElement(newPageCreate);
		if (newPageCreation.isDisplayed() && newPageCreation.isEnabled()) 
		{
			newPageCreation.click();
			webele.add(val + "1");
		}
	}
	
	/**
	 * Method to add other modules
	 * 
	 * @param exitName Name of the exit module to be added
	 */
	public void addOtherModules(String exitName) {
		connectSMSEmailAndExit(webele.get(1), webele.get(i), "Sent");
		scrolltoView(driver.findElement(By.id(webele.get(3))));
		addExitApp(exitName, "5", 500, 300);
		connectSMSEmailAndExit(webele.get(i -2), webele.get(i), "Sent");
		addExitApp(exitName, "6", 700, 300);
		connectSMSEmailAndExit(webele.get(i -3), webele.get(i), "Not Sent");
	}
	
	/**
	 * Method to verify the newly added Page
	 * 
	 * @param pageName Name to be verified
	 * 
	 *
	 * @return boolean value
	 */
	public boolean verifyPageName(String pageName) {
		WebElement pageTitleElement = driver.findElement(By.xpath("//a[contains(text(),'"+ pageName +"')]"));
		String pageTitle = pageTitleElement.getText();
		return pageTitle.equals(pageName);
	}

	/**
	 * Method to add Send an SMS component
	 * 
	 * @param dragEleText Element name to be dragged
	 * @param a component Number being added
	 * @param x X-coordinate of the component
	 * @param y Y-coordinate of the component 
	 *
	 */
	public void addSendSMS(String dragEleText, String a, int x, int y) {
		WebElement message = driver.findElement(messagingSection);
		message.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(messageDiv));
		WebElement messagediv = driver.findElement(messageDiv);
		List<WebElement> messageOptions = messagediv.findElement(By.tagName("ul")).findElements(By.tagName("li"));
		for (WebElement e : messageOptions) {
			if (e.getText().contains(dragEleText)) {
				new Actions(driver).dragAndDropBy(e, x, y).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='module-2']//textarea[@name= 'phone_constant']")));
				webele.add(val + a);
				i++;
			}
		}
	}
	
	/**
	 * Method to call add SMS component
	 * 
	 * @param phoneNumber Phone Number to be added
	 * @param phoneMessage Phone Message to be added
	 *
	 */
	public void enterSMSDetails(String phoneNumber, String phoneMessage) {
		fillSMSDetails(phoneNumber, phoneMessage);
		joinStartAndSendSMS();
	}

	/**
	 * Method to enter values inside SMS component
	 * 
	 * @param phoneNumberVal Phone Number to be added
	 * @param phoneMessageVal Phone Message to be added
	 *
	 */
	private void fillSMSDetails(String phoneNumberVal, String phoneMessageVal) {
		WebElement phoneNumber = driver.findElement(phoneNumberBy);
		WebElement phoneMessage = driver.findElement(phoneMessageBy);
		if (phoneNumber.isDisplayed() && phoneNumber.isEnabled()) {
			phoneNumber.click();
			phoneNumber.clear();
			phoneNumber.sendKeys(phoneNumberVal, Keys.TAB);
		}
		if (phoneMessage.isEnabled() && phoneMessage.isDisplayed()) {
			phoneMessage.click();
			phoneMessage.clear();
			phoneMessage.sendKeys(phoneMessageVal, Keys.TAB);
		}
	}

	/**
	 * Method to join Start and Send an SMS componenet
	 * 
	 */
	private void joinStartAndSendSMS() {
		WebElement conn = driver.findElement(By.xpath("//div[@id='editor-layout']/div[contains(@class, 'ui-layout-center')]"));
		conn = conn.findElement(By.id("tabs-2")).findElement(By.id("module-1"));
		WebElement connS = conn.findElement(By.cssSelector("div.mod-rail.mod-south > div"));
		WebElement conn2 = driver.findElement(By.id("module-1"));
		WebElement connEnd = conn2.findElement(By.xpath("//div[@class = 'mod-rail mod-north']/div[contains(@id, 'rec-')]"));
		Action conncet = actions.clickAndHold(connS).moveToElement(connEnd).release(connEnd).build();
		conncet.perform();
	}
	
	/**
	 * Method to add Exit component
	 * 
	 * @param dragEleText Element name to be dragged
	 * @param a component Number being added
	 * @param x X-coordinate of the component
	 * @param y Y-coordinate of the component 
	 *
	 */
	public void addExitApp(String dragEleText, String a, int x, int y) {
		WebElement basic = driver.findElement(basicSection);
		basic.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(basicDiv));
		WebElement basicdiv = driver.findElement(basicDiv);
		List<WebElement> basicOptions = basicdiv.findElement(By.tagName("ul")).findElements(By.tagName("li"));
		for (WebElement e : basicOptions) {
			if (e.getText().contains(dragEleText)) {
				new Actions(driver).dragAndDropBy(e, x, y).build().perform();
				String res = val + a;
				wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id(res)), "Exit App"));
				webele.add(res);
				i++;
			}
		}
		WebElement basicReset = driver.findElement(messagingSection);
		basicReset.click();
		int i=0;
	}

	/**
	 * Method to add Send an Email component
	 * 
	 * @param dragEleText Element name to be dragged
	 * @param a component Number being added
	 * @param x X-coordinate of the component
	 * @param y Y-coordinate of the component 
	 *
	 */
	public void addSendEmail(String dragEleText, String a, int x, int y) {
		WebElement message = driver.findElement(messagingSection);
		message.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(messageDiv));
		WebElement messagediv = driver.findElement(messageDiv);
		List<WebElement> messageOptions = messagediv.findElement(By.tagName("ul")).findElements(By.tagName("li"));
		for (WebElement e : messageOptions) {
			if (e.getText().contains(dragEleText)) {
				new Actions(driver).dragAndDropBy(e, x, y).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='smtp_url']")));
				webele.add(val + a);
				i++;
			}
		}
	}
	
	/**
	 * Method to add Email details
	 * 
	 * @param smtp SMTP value to be added
	 * @param port Port value to be added
	 * @param ssl SSL value to be added
	 * @param username UserName value to be added 
	 * @param password Password value to be added
	 * @param from From value to be added
	 * @param to To value to be added
	 * @param subject Subject value to be added
	 * @param cc CCvalue to be added
	 * @param emailMessage EmailMessage value to be added
	 *
	 */
	public void enterEmailDetails(String smtp, String port, String ssl, String username, String password, String from, String to, String subject, String cc, String emailMessage) {
		fillEmailDetails(smtp, port, ssl, username, password, from, to, subject, cc, emailMessage);
		connectSMSAndEmail(webele.get(i - 1), webele.get(i));
	}

	/**
	 * Method to fill Email details
	 * 
	 * @param smtp SMTP value to be added
	 * @param port Port value to be added
	 * @param ssl SSL value to be added
	 * @param username UserName value to be added 
	 * @param password Password value to be added
	 * @param from From value to be added
	 * @param to To value to be added
	 * @param subject Subject value to be added
	 * @param cc CCvalue to be added
	 * @param emailMessage EmailMessage value to be added
	 *
	 */
	private void fillEmailDetails(String smtpVal, String portVal, String sslVal, String usernameVal, String passwordVal, String fromVal, String toVal, String subjectVal, String ccVal, String emailMessageVal) {
		WebElement smtp = driver.findElement(smtpBy);
		WebElement port = driver.findElement(portBy);
		WebElement ssl = driver.findElement(sslBy);
		WebElement username = driver.findElement(usernameBy);
		WebElement password = driver.findElement(passwordBy);
		WebElement from = driver.findElement(fromBy);
		WebElement to = driver.findElement(toBy);
		WebElement subject = driver.findElement(subjectBy);
		WebElement cc = driver.findElement(ccBy);
		WebElement emailMessage = driver.findElement(emailMessageBy);
		
		if (smtp.isDisplayed() && smtp.isEnabled()) {
			smtp.clear();
			smtp.sendKeys(smtpVal);
		}
		if (!ssl.isSelected())
			ssl.click();
		if (port.isDisplayed() && port.isEnabled()) {
			port.clear();
			port.sendKeys(portVal);
		}
		if (username.isDisplayed() && username.isEnabled()) {
			username.clear();
			username.sendKeys(usernameVal);
		}
		if (password.isDisplayed() && password.isEnabled()) {
			password.clear();
			password.sendKeys(passwordVal);
		}
		if (from.isDisplayed() && from.isEnabled()) {
			from.clear();
			from.sendKeys(fromVal);
		}
		if (to.isDisplayed() && to.isEnabled()) {
			to.clear();
			to.sendKeys(toVal);
		}
		if (subject.isDisplayed() && subject.isEnabled()) {
			subject.clear();
			subject.sendKeys(subjectVal);
		}
		if (cc.isDisplayed() && cc.isEnabled()) {
			cc.clear();
			cc.sendKeys(ccVal);
		}
		if (emailMessage.isDisplayed() && emailMessage.isEnabled()) {
			emailMessage.click();
			emailMessage.clear();
			emailMessage.sendKeys(emailMessageVal);
		}
	}

	/**
	 * Method to draw connection link
	 *
	 * @param webElemStart Starting element to draw the connection
	 * @param webElemEnd Ending element of the connection
	 * 
	 * @return integer value based on the number of components added
	 */
	private void connectSMSAndEmail(String webElemStart, String webElemEnd) {
		WebElement connS = driver.findElement(By.xpath("//div[@id='editor-layout']/div[contains(@class, 'ui-layout-center')]"));
		connS = connS.findElement(By.id("tabs-2")).findElement(By.id(webElemStart));
		WebElement connStart = connS.findElement(By.xpath("//div[@class='panel-nodes-attached inner']/div[contains(@class, 'syn-node-attached-e')]"));
		WebElement connEnd = driver.findElement(By.xpath("//div[@id='" + webElemEnd + "']/div[@class = 'mod-rail mod-north']/div[contains(@id, 'rec-')]"));
		Action conncet = actions.clickAndHold(connStart).moveToElement(connEnd).release(connEnd).build();
		conncet.perform();
	}
	
	/**
	 * Method to add draw connection link
	 *
	 * @param webElemStart Starting element to draw the connection
	 * @param webElemEnd Ending element of the connection
	 * @param sentNotSent Sent/Not Sent to decide the connecting end
	 * 
	 * @return integer value based on the number of components added
	 */
	private void connectSMSEmailAndExit(String webElemStart, String webElemEnd, String sentNotSent) {
		WebElement connS = driver.findElement(By.xpath("//div[@id='editor-layout']/div[contains(@class, 'ui-layout-center')]"));
		connS = connS.findElement(By.id("tabs-2")).findElement(By.id(webElemStart));
		if(sentNotSent.equals("Sent"))
			sentNotSent = "syn-node-attached-w";
		if(sentNotSent.equals("Not Sent"))
			sentNotSent = "syn-node-attached-e";
		WebElement connStart = connS.findElement(By.xpath("//div[@id='" + webElemStart + "']//div[@class='panel-nodes-attached inner']/div[contains(@class, '" + sentNotSent + "')]"));
		WebElement connEnd = driver.findElement(By.xpath("//div[@id='" + webElemEnd + "']/div[@class = 'mod-rail mod-north']/div[contains(@id, 'rec-')]"));
		Action conncet = actions.clickAndHold(connStart).moveToElement(connEnd).release(connEnd).build();
		conncet.perform();
	}
	
	/**
	 * Method to verify added connections
	 * 
	 * @return boolean based on the connected links
	 */
	public boolean verifyConnections() {
		boolean res = true;
		for(int i=0,j=0;i<webele.size() && j<webele.size();i++,j++) {
			WebElement a = driver.findElement(By.xpath("//div[@class = 'syn-wire']/preceding-sibling::div[@id='"+webele.get(i)+"']"));
			String val = a.getAttribute("id");
			res = val.equals(webele.get(i));
			res = res == true ? true:false;
		}
		return res;
	}
	
	/**
	 * Method to fetch the added details from SMS and Email
	 * 
	 * @return EmailAndSmsData object 
	 */
	public EmailAndSmsData fetchEmailAndSMSDetails() {
		String phoneNumberValue = driver.findElement(phoneNumberBy).getAttribute("value");
		String phoneMessageValue = driver.findElement(phoneMessageBy).getAttribute("value");
		String smtpValue = driver.findElement(smtpBy).getAttribute("value");
		String portValue = driver.findElement(portBy).getAttribute("value");
		String sslValue = driver.findElement(sslBy).getAttribute("value");
		String userNameValue = driver.findElement(usernameBy).getAttribute("value");
		String passwordValue = driver.findElement(passwordBy).getAttribute("value");
		String fromValue = driver.findElement(fromBy).getAttribute("value");
		String toValue = driver.findElement(toBy).getAttribute("value");
		String subjectValue = driver.findElement(subjectBy).getAttribute("value");
		String ccValue = driver.findElement(ccBy).getAttribute("value");
		String emailMessageValue = driver.findElement(emailMessageBy).getAttribute("value");

		emailSMS.setPhoneNumber(phoneNumberValue);
		emailSMS.setPhoneMessage(phoneMessageValue);
		emailSMS.setEmailSMTP(smtpValue);
		emailSMS.setEmailPort(portValue);
		emailSMS.setEmailSSL(sslValue);
		emailSMS.setEmailUsername(userNameValue);
		emailSMS.setEmailPassword(passwordValue);
		emailSMS.setEmailFrom(fromValue);
		emailSMS.setEmailTo(toValue);
		emailSMS.setEmailSubject(subjectValue);
		emailSMS.setEmailCc(ccValue);
		emailSMS.setEmailMessage(emailMessageValue);
		return emailSMS;
	}

	/**
	 * Method to add Email details
	 *
	 * @return integer value based on the number of components added
	 */
	public int countAddedModules() {
		return webele.size();
	}
	
	/**
	 * Method to verify added Modules
	 *
	 * @return boolean value based on the components added
	 */
	public boolean verifyAddedModules() {
		char it = '1';
		boolean res = true;
		for(String a : webele) {
			String val = "module-"+ it;
			if(a.equals(val)) {
				res = res && true;
			}
			else {
				res = res && false;
			}
			it++; 
		}
		return res;
	}
}