package it.lascaux.askme.askmedesk_selenium_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FactoryDriver {
    private static WebDriver driver;
    private MyWebElement myWebElement;
    private  static WebDriver chromeDriver;
    private  static WebDriver fireFoxDriver;


    public static WebDriver getWebDriver(String driverName) {


        if (driverName.equals("Chrome")) return FactoryDriver.getChromeDriver();
        if (driverName.equals("Firefox")) return FactoryDriver.getFireFoxDriver();
        return null;
    }

    /**
     * @param
     * @return WebDriver : chrome
     */

////Funziona anche senza Driver
    public static WebDriver getChromeDriver() {

        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    /**
     * @param
     * @return WebDriver : fireFox
     */
    public static WebDriver getFireFoxDriver() {

        WebDriverManager.firefoxdriver().setup();
        fireFoxDriver = new FirefoxDriver();
        return fireFoxDriver;
    }

}
