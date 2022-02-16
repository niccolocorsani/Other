package it.lascaux.askme.askmedesk_selenium_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.prefs.Preferences;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    public static void main(String[] args) {


        WebDriver driver = FactoryDriver.getChromeDriver();
        driver.get("https://harvesthq.github.io/chosen/");

      //  WebElement wb = driver.findElement(By.xpath("//ul[@class='chosen-results']/*"));
        WebElement wb = driver.findElement(By.xpath("//ul[@class='chosen-results']/*[1]"));

        try {

             WebElement w1b = driver.findElement(By.xpath("//ul[@class='chosen-results']/*[1]"));
            System.out.println(wb.getTagName());

            wb = driver.findElement(By.xpath("//"));
            wb.click();

        } catch (Exception e) {

            System.out.println("oooooooo");
        }


    }


}