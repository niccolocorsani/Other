package Model;

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

	
	
	public WebElement findAllWithText(String text) {

		try {
			return this.findWithText(text);
		} catch (Exception e) {
			System.err.println("errore in findWithText");

			try {
				return this.findById(text);
			} catch (Exception e1) {
				System.err.println("errore in findWithId");
				try {
     				return this.findWithXPath(text);
				} catch (Exception e2) {
					System.err.println("errore in findWithXPath");


				}
			}
		}
		return null;

	}

	



 

}
