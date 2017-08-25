package news360.automation.verifier;

import static org.hamcrest.core.IsEqual.equalTo;

import org.apache.log4j.Logger;
import org.junit.rules.ErrorCollector;

import news360.automation.page.*;

public class SignUpPageVerifier {
	
	public Logger logger;
	public ErrorCollector collector;
	
	
	public int verifySuccessfulSignUp(SignUpPage page) {
		//collector.checkThat("Checking SignUp Success Message.", page.isSuccessSignUp(), equalTo(true));
		if(page.isSuccessSignUp()==true) {
			return 0;
		}
		else {
			return -2;
		}

	}

	public int verifyErrorMessage(String testName, String expectedMessage, String actualMessage) {
		//collector.checkThat(testName, actualMessage, equalTo(expectedMessage));
		if(actualMessage.equals(expectedMessage)) {
			return 0;
		}
		else {
			return -1;
		}
	}
}
