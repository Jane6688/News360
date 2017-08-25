package news360.automation.test;

import jxl.Cell;
import news360.automation.data.SignUpTestData;
import news360.automation.domain.SignUpPageField;
import news360.automation.factory.SignUpTestDataFactory;
import news360.automation.page.SignUpPage;
import news360.automation.verifier.SignUpPageVerifier;


public class SignUpTest extends BaseTest {
	@Override
	protected int runTestCase(Cell[] testLine) {
		SignUpPage signUpPage = new SignUpPage(this.testSettings, "https://news360.com/");
		SignUpTestData testData = SignUpTestDataFactory.createData(testLine);
		SignUpPageField pageField = new SignUpPageField(testData);
		signUpPage.signUpAction(pageField);
		
		SignUpPageVerifier verify = new SignUpPageVerifier();
		if (testData.getErrorMessage().isEmpty()) {
			return verify.verifySuccessfulSignUp(signUpPage);
		} 
		else 
		{	
			return verify.verifyErrorMessage(testData.getTestName(), testData.getErrorMessage(), signUpPage.getErrorMessage());
		}
	}
}
