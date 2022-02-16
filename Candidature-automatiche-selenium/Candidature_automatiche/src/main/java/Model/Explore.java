package Model;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Explore {

	private static WebDriver driver;
	private MyWebElement myWebElement;

	public Explore() {
	}

	public static WebElement getParentWebElement(WebElement wb, WebDriver wd) {

		return (WebElement) ((JavascriptExecutor) wd).executeScript("return arguments[0].parentNode;", wb);
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException, AWTException,
			IllegalAccessException, IllegalArgumentException {

		try {
			driver = FactoryDriver.getChromeDriver();
			driver.get("https://www.Linkedin.com");
			MyWebElement myWebELement = new MyWebElement(driver);
			WebElement cerca = myWebELement.findAllWithText("Email o telefono");
			PerformActionOnElements.tryWriteOnIterationPartent(cerca, driver, "prova");
			PerformActionOnElements.tryWriteInsideInput(cerca, "nome");
		} catch (Exception e) {

			driver.close();

		}

	}

}
