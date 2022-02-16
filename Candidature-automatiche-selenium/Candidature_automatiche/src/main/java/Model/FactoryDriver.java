package Model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FactoryDriver {

	public static WebDriver chromeDriver;

	public static WebDriver FireFoxDriver;
	public FactoryDriver() {

	}

	public static WebDriver getChromeDriver() {
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver",
				"/Users/nicche/eclipse-workspace/Candidature_automatiche/src/main/resources/chromedriver");
		return chromeDriver;
	}

	public static void setChromeDriver(WebDriver chromeDriver) {
		
		FactoryDriver.chromeDriver = chromeDriver;
	}

}
