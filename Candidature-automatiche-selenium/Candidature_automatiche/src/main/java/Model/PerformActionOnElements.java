package Model;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author nicche
 *
 */
public class PerformActionOnElements {

	/**
	 * @param the WebElement wich we want to write inside and the String to write
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws AWTException
	 */

	public static void tryWriteInsideInput(WebElement webElement, String text)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, AWTException {

		Method[] methods = webElement.getClass().getDeclaredMethods();
		Robot robot = new Robot();
		robot.setAutoDelay(10);

		for (Method m : methods) {

			try {
				
				RobotUtilities.clickOnCenter();
				m.invoke(webElement, null);
				m.invoke(webElement, text);
				System.out.println("Successo con il metodo" + m.getName());
				RobotUtilities.writeText(text);
				break; ///// if the method succesful end the loop

			} catch (Exception e) {
				System.err.println("Errore nel metodo: " + m.getName());

			}

		}

	}

	public static void tryWriteOnIterationPartent(WebElement webElement, WebDriver driver, String text) {

		while (true) {

			try {
				RobotUtilities.clickOnCenter();

				Explore.getParentWebElement(webElement, driver).click();
				RobotUtilities.writeText(text);
				System.out.println("iiiiiiii");
				break;

			} catch (Exception e) {

				Explore.getParentWebElement(webElement, driver);
				e.printStackTrace();
				System.out.println("Eccezione in IterationParent");

			}

		}

	}

	public static void main(String[] args) {

	}

}
