package news360.automation.factory;

import news360.automation.data.SignUpTestData;
import jxl.Cell;

public class SignUpTestDataFactory {

	public static SignUpTestData createData(Cell[] testLine) {
		
		SignUpTestData testData =  new SignUpTestData();
		testData.setTestName(testLine[1].getContents());		
		testData.setEmailAddress(testLine[2].getContents());
		testData.setPassword(testLine[3].getContents());
		testData.setConfirmPassword(testLine[4].getContents());
		testData.setErrorMessage(testLine[5].getContents());
		return testData;
	}
}
