package utilities;

/**
 * Class Module to get and set module details
 * 
 */

public class EmailAndSmsData {
	private String phoneNumber;
	private String phoneMessage;
	private String emailSMTP;
	private String emailPort;
	private String emailSSL;
	private String emailUsername;
	private String emailPassword;
	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String emailCc;
	private String emailMessage;
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneMessage(String phoneMessage) {
		this.phoneMessage = phoneMessage;
	}
	public String getPhoneMessage() {
		return phoneMessage;
	}
	public String getEmailSMTP() {
		return emailSMTP;
	}
	public void setEmailSMTP(String emailSMTP) {
		this.emailSMTP = emailSMTP;
	}
	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}
	public String getEmailPort() {
		return emailPort;
	}
	public String getEmailSSL() {
		return emailSSL;
	}
	public void setEmailSSL(String emailSSL) {
		this.emailSSL = emailSSL;
	}
	public String getEmailUsername() {
		return emailUsername;
	}
	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailCc() {
		return emailCc;
	}
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}
	public String getEmailMessage() {
		return emailMessage;
	}
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
}
