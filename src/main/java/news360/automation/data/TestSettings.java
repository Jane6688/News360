package news360.automation.data;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSettings {
	private String    inputDirectory;
	private String    outputDirectory;
	private WebDriver webDriver;
	
	public TestSettings() {
		inputDirectory="C:\\Users\\ThinkPad\\eclipse-workspace\\new360\\test\\input";
		outputDirectory="C:\\Users\\ThinkPad\\eclipse-workspace\\new360\\test\\output";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ThinkPad\\eclipse-workspace\\chromedriver.exe");	
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public String getInputDirectory() {
		return inputDirectory;
	}

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}
	
	public String getOutputDirectory() {
		return outputDirectory;
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
