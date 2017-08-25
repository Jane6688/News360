package news360.automation.domain;

import news360.automation.data.SignInTestData;

public class SignInPageField {
	private String emailAddress;
	private String password;
	
	public SignInPageField(SignInTestData testData) {
		super();
		this.emailAddress = testData.getEmail();
		this.password = testData.getPassword();
	}
	
	public SignInPageField() {
		super();
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
