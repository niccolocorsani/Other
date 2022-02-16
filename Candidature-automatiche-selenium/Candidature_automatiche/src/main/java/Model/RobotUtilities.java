package Model;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotUtilities {

	private static Robot robot;

	public static void writeText(String text) throws AWTException {

		robot = new Robot();
		robot.setAutoDelay(100);

		for (char c : text.toCharArray()) {
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			if (KeyEvent.CHAR_UNDEFINED == keyCode) {
				throw new RuntimeException("Key code not found for character '" + c + "'");
			}
			robot.keyPress(keyCode);
			robot.delay(10);
			robot.keyRelease(keyCode);
			robot.delay(10);
		}

	}

	public static void clickOnCenter() throws AWTException {

		robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseMove(300, 300);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void blockPopup() {
	}

	public static void main(String[] args) throws AWTException {

	}

}
