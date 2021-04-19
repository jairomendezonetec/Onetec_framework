package framework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Logger;

public class ObjectManager extends DriverManager {

	final private static String CLASS_NAME = "ObjectManager";
	private static Logger logger = new Logger(CLASS_NAME);

	public static WebElement getObject(String[] elementToFound) {
		String attribute = null;
		String value = elementToFound[1];
		String keyContains = null;

		if (!elementToFound[0].contains("contains")) {
			attribute = elementToFound[0];
		} else {
			keyContains = elementToFound[0].substring(8);
			attribute = "contains";
		}
		try {
			switch (attribute) {
			case "id":
				return driver.findElement(By.id(value));
			case "class":
				return driver.findElement(By.className(value));
			case "contains":
				if (keyContains.contains("text")) {
					logger.debug("Generating xpath : '//*[contains(text(),'" + value + "')]'");
					return driver.findElement(By.xpath("//*[contains(text(),'" + value + "')]"));
				} else {
					logger.debug("Generating xpath : '//*[contains(@" + keyContains + ",'" + value + "')]'");
					return driver.findElement(By.xpath("//*[contains(@" + keyContains + ",'" + value + "')]"));
				}
			case "name":
				return driver.findElement(By.name(value));
			case "xpath":
				return driver.findElement(By.xpath(value));
			case "tag":
				return driver.findElement(By.tagName(value));
			case "text":
				logger.debug("Generating xpath : '//*[text()='" + value + "']'");
				return driver.findElement(By.xpath("//*[text()='" + value + "']"));
			case "linkText":
				return driver.findElement(By.linkText(value));
			default:
				logger.debug("Generating xpath : '//*[@" + elementToFound[0] + "='" + value + "']'");
				return driver.findElement(By.xpath("//*[@" + elementToFound[0] + "='" + value + "']"));
			}
		} catch (NoSuchElementException e) { // TODO
			logger.error("Element not found");
		} catch (NullPointerException e) {
			logger.error("Element can't be null");
		}
		return null;
	}

	public static List<WebElement> getObjects(String[] elementToFound) {
		String attribute = null;
		String value = elementToFound[1];
		String keyContains = null;

		if (!elementToFound[0].contains("contains")) {
			attribute = elementToFound[0];
		} else {
			keyContains = elementToFound[0].substring(8);
			attribute = "contains";
		}
		try {
			switch (attribute) {
			case "id":
				return driver.findElements(By.id(value));
			case "class":
				return driver.findElements(By.className(value));
			case "contains":
				if (keyContains.contains("text")) {
					logger.debug("Generating xpath : '//*[contains(text(),'" + value + "')]'");
					return driver.findElements(By.xpath("//*[contains(text(),'" + value + "')]"));

				} else {
					logger.debug("Generating xpath : '//*[contains(@" + keyContains + ",'" + value + "')]'");
					return driver.findElements(By.xpath("//*[contains(@" + keyContains + ",'" + value + "')]"));
				}
			case "name":
				return driver.findElements(By.name(value));
			case "xpath":
				return driver.findElements(By.xpath(value));
			case "tag":
				return driver.findElements(By.tagName(value));
			case "text":
				logger.debug("Generating xpath : '//*[text()='" + value + "']'");
				return driver.findElements(By.xpath("//*[text()='" + value + "']"));
			case "linkText":
				return driver.findElements(By.linkText(value));
			default:
				logger.debug("Generating xpath : '//*[@" + elementToFound[0] + "='" + value + "']'");
				return driver.findElements(By.xpath("//*[@" + elementToFound[0] + "='" + value + "']"));
			}
		} catch (NoSuchElementException e) {// TODO
			logger.error("Element not found");
		} catch (NullPointerException e) {
			logger.error("Element can't be null");
		}
		return null;
	}

	public static By getByObject(String[] elementToFound) {
		String attribute = null;
		String value = elementToFound[1];
		String keyContains = null;

		if (!elementToFound[0].contains("contains")) {
			attribute = elementToFound[0];
		} else {
			keyContains = elementToFound[0].substring(8);
			attribute = "contains";
		}

		try {
			switch (attribute) {
			case "id":
				return By.id(value);
			case "class":
				return By.className(value);
			case "contains":
				if (keyContains.contains("text")) {
					logger.debug("Generating xpath : '//*[contains(text(),'" + value + "')]'");
					return By.xpath("//*[contains(text(),'" + value + "')]");
				} else {
					logger.debug("Generating xpath : '//*[contains(@" + keyContains + ",'" + value + "')]'");
					return By.xpath("//*[contains(@" + keyContains + ",'" + value + "')]");
				}
			case "name":
				System.out.println("name : "+ value);
				return By.name(value);
			case "xpath":
				return By.xpath(value);
			case "tag":
				return By.tagName(value);
			case "text":
				logger.debug("Generating xpath : '//*[text()='" + value + "']'");
				return By.xpath("//*[text()='" + value + "']");
			case "linkText":
				return By.linkText(value);
			default:
				logger.debug("Generating xpath : '//*[@" + elementToFound[0] + "='" + value + "']'");
				return By.xpath("//*[@" + elementToFound[0] + "='" + value + "']");
			}
		} catch (NoSuchElementException e) {// TODO
			logger.error("Element not found");
		} catch (NullPointerException e) {
			logger.error("Element can't be null");
		}
		return null;
	}

	public static String[] replaceXpath(String[] element, String... changes) {
		String type = element[0];
		String selector = element[1];

		for (int i = 1; i - 1 < changes.length; i++) {
			selector = selector.replace("$var" + i, changes[i - 1]);
		}
		return new String[] { type, selector };
	}

	public static boolean isObjectPresent(String[] elementToFound, long sec) throws ExecutionException {

		if (elementToFound != null) {
			WebElement object = null;
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, sec);
				object = wait.until(ExpectedConditions.visibilityOf(getObject(elementToFound)));
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				return false;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				throw new ExecutionException("[ERROR] Unexpected error while waiting for element to be present",
						e.getCause());
			}

			return object != null && object.isDisplayed();
		} else {
			logger.error("isObjectPresent: elementToFound is null or empty");
			return false;
		}
	}

	public static boolean isObjectNotPresent(String[] elementToFound, long sec) throws ExecutionException {

		if (elementToFound != null) {
			boolean notPresent = false;
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, sec);
				notPresent = wait.until(ExpectedConditions
						.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByObject(elementToFound))));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				return false;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				throw new ExecutionException("[ERROR] Unexpected error while waiting for element to be present",
						e.getCause());
			}
			return notPresent;
		} else {
			logger.error("isObjectPresent: elementToFound is null or empty");
			return false;
		}
	}
}
