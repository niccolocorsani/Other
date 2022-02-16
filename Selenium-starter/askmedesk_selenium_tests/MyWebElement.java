package it.lascaux.askme.askmedesk_selenium_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyWebElement {


    private WebDriver driver;

    public MyWebElement(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement findWithText(String text) {
        return driver.findElement(By.linkText(text));
    }

    /**
     * @param text : text should be the supposed tag "id" for example input type =
     *             "text" id="identificativo123!" name="Alberto"/
     * @return WebElement
     */
    public WebElement findById(String text) {
        return driver.findElement(By.id(text));

    }

    public WebElement findWithXPath(String text) {
        return driver.findElement(By.xpath("//*[text()='" + text + "']"));

    }



}
