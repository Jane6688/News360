package news360.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import news360.automation.data.TestSettings;
import news360.automation.domain.SignUpPageField;

public class SignUpPage {
	private TestSettings testSettings;
	private String pageUrl;
	private static final String EMAIL_ID = "signupemail";
	private static final String PASSWORD_ID = "password";
	//private static final String CONFIRM_PASSWORD_XPATH = "//input[@class='confirmpassword textbox']";
	private static final String CONFIRM_PASSWORD_XPATH = "html/body/div[1]/div[3]/div/div[5]/div[4]/form[3]/fieldset[3]/input";
	//private static final String SIGNIN_BTN_XPATH = "//button[contains(text(),'Sign up') AND @type='submit']";
	private static final String SIGNUP_BTN_XPATH = "html/body/div[1]/div[3]/div/div[5]/div[4]/form[3]/div[1]/button[2]";

	private static final String SUCESS_MSG_XPATH = "//*[contains(text(),'Welcome to News360')]";
    private static final String ERROR_MSG_XPATH = "html/body/div[1]/div[3]/div/div[5]/div[1]";
	
	public SignUpPage(TestSettings testSettings, String pageUrl) {
		this.testSettings = testSettings;
		this.pageUrl = pageUrl;
	}
	
	public void closeBrowser() {
		WebDriver webDriver = testSettings.getWebDriver();
		webDriver.close();
	}

	public void signUpAction(SignUpPageField pageField) {
		WebDriver webDriver = testSettings.getWebDriver();
		
		webDriver.get(this.pageUrl);
		
		String titleOfPage = webDriver.getTitle();
		
		System.out.println("Title of the page is: " + titleOfPage);
		
		String currentUrl = webDriver.getCurrentUrl();
		System.out.println("Current URL is:" + currentUrl);		
				
		try {
			webDriver.findElement(By.linkText("Sign in with email")).click();
			Thread.sleep(3000);
			
			webDriver.findElement(By.linkText("Sign up")).click();
			
			webDriver.findElement(By.id(EMAIL_ID)).sendKeys(pageField.getEmailAddress());
			webDriver.findElement(By.id(PASSWORD_ID)).sendKeys(pageField.getPassword());
			webDriver.findElement(By.xpath(CONFIRM_PASSWORD_XPATH)).sendKeys(pageField.getConfirmPassword());
			
			webDriver.findElement(By.xpath(SIGNUP_BTN_XPATH)).click();;
			
			Thread.sleep(3000);		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isSuccessSignUp() {
		try {
			this.testSettings.getWebDriver().findElement(By.xpath(SUCESS_MSG_XPATH)).getText();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getSuccessMessage() {
		return this.testSettings.getWebDriver().findElement(By.xpath(SUCESS_MSG_XPATH)).getText();
	}
	
	public String getErrorMessage() {
		String errorMessage ="";
		errorMessage = this.testSettings.getWebDriver().findElement(By.xpath(ERROR_MSG_XPATH)).getText();
		return errorMessage;
	}



}