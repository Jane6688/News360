package news360.automation.test;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import news360.automation.data.TestSettings;
import news360.automation.utils.TestUtils;

public abstract class BaseTest {

	protected final Logger logger = Logger.getLogger(this.getClass());

	protected TestSettings testSettings = new TestSettings();

	private File getInputFile(String fileName) {
		String inputDirectory = testSettings.getInputDirectory();
		File file = new File(inputDirectory, fileName);
		if (!file.exists()) {
			throw new IllegalStateException(String.format("The input filename: %s does not exist!", file.getName()));
		}
		return file;
	}

	private File getOutputFile(String fileName) {
		String outputFile = TestUtils.getDateTimeAsFilenameStr() + "_" + fileName;
		String outputDirectory = testSettings.getOutputDirectory();
		File file = new File(outputDirectory, outputFile);
		return file;
	}

	private boolean isTestLine(Cell[] testLine) {
		// "Y" in "RunTest" column for valid test line
		if (testLine.length > 0 && testLine[0].getContents().startsWith("Y"))
			return true;
		return false;
	}
	
	@Before
	public void init() {
	}

	/**
	 * Open the test data file (Test class name end with '.xls').<br>
	 * Read the input one row at a time. <br>
	 * Then call executeTestCase.
	 */
	@Test
	public void runAllTestCases() {
		// Use Java class name as test data filename 
		String inputFile = this.getClass().getSimpleName()+".xls"; 

		logger.info("Starting Test");
		logger.info("Input File : " + inputFile);
		int currentRowNumber;
		WorkbookSettings ws = new WorkbookSettings();
		Sheet inputSheet;
		ws.setEncoding("ISO8859_1");
		Workbook testDataWorkbook = null;

		try {
			testDataWorkbook = Workbook.getWorkbook(getInputFile(inputFile), ws);
			inputSheet = testDataWorkbook.getSheet(0);

			WritableWorkbook outputWorkbook = Workbook.createWorkbook(getOutputFile(inputFile), testDataWorkbook);
			WritableSheet outputSheet = outputWorkbook.getSheet(0);

			Cell[] testLine;
			testLine = inputSheet.getRow(0);
			for (currentRowNumber = 1; currentRowNumber < inputSheet.getRows(); currentRowNumber++) {
				testLine = inputSheet.getRow(currentRowNumber);
				if (!isTestLine(testLine)) {
					continue;
				}
				logger.info("Processing Test Data : " + currentRowNumber);
				int testResult = runTestCase(testLine);
				if (testResult == 0) {
					int testResultCol = testLine.length;
					Label testResultLabel = new Label(testResultCol, currentRowNumber, "Pass");
					outputSheet.addCell(testResultLabel);					
				} else {
					int testResultCol = testLine.length;
					Label testResultLabel = new Label(testResultCol, currentRowNumber, String.format("Fail(%d)",testResult));
					outputSheet.addCell(testResultLabel);					
				}
			}			
			outputWorkbook.write();
			outputWorkbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		logger.info("End of Test");
	}

	/**
	 * This tears down the test. Report all Errors and closing the browser.
	 */
	@After
	public void finalize() {
		this.testSettings.getWebDriver().close();

	}

	/**
	 * This should be implemented by the actual test class. 
	 * 
	 * @param testLine
	 */
	protected abstract int runTestCase(Cell[] testLine);
}
