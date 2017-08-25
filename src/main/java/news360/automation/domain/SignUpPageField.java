package news360.automation.domain;

import news360.automation.data.SignUpTestData;

public class SignUpPageField {

	private String emailAddress;
	private String password;
	private String confirmPassword;
	
	
	public SignUpPageField(SignUpTestData testData) {
		this.emailAddress = testData.getEmailAddress();
		this.password = testData.getPassword();
		this.confirmPassword = testData.getConfirmPassword();
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
