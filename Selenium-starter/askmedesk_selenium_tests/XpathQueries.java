package it.lascaux.askme.askmedesk_selenium_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class XpathQueries {


    private String xpathQueryText;
    private String xpathContainsText = "//*[contains(text(),'Richiesta')]";
    private String xpathQueryAttribute = "//a[@title='value'"; //// da fare metodo per rendere dinamico


    public static String xpathContainsText(String text) {

        return "//*[contains(text(),'" + text + "')]";

    }


    public static String xpathTextEqualsTo(String text) {
        return "//*[text()='" + text + "']";

    }
}
