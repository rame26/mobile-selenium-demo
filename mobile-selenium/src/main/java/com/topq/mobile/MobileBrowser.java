package com.topq.mobile;

import java.util.List;

import jsystem.framework.TestProperties;
import junit.framework.SystemTestCase4;

import org.jsystem.webdriver_so.WebDriverSystemObject;
import org.jsystem.webdriver_so.eventlistener.WebDriverScreenshotEventHandler;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class MobileBrowser extends SystemTestCase4 {

	protected WebDriver driver;
	protected WebDriverSystemObject seleniumSystemObject;

	@Before
	public void setUp() throws Exception {
		seleniumSystemObject = (WebDriverSystemObject) system.getSystemObject("web");
		driver = seleniumSystemObject.getDriver();
	}

	protected void takeScreenshot(String title) {
		List<WebDriverEventListener> allRegisteredWebDriverEventListeners = seleniumSystemObject
				.getAllRegisteredWebDriverEventListeners();
		for (WebDriverEventListener webDriverEventListener : allRegisteredWebDriverEventListeners) {
			if (webDriverEventListener instanceof WebDriverScreenshotEventHandler) {
				WebDriverScreenshotEventHandler screenshot = (WebDriverScreenshotEventHandler) webDriverEventListener;
				screenshot.takeScreenshot(driver, title);
				break;
			}
		}
	}

	@Test
	@TestProperties(name="Sample google mobile test")
	public void googleSearch() {
		// And now use this to visit Google
		driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));

		// Enter something to search for
		element.sendKeys("Cheese!");

		// Now submit the form. WebDriver will find the form for us from the
		// element
		element.submit();

		// Check the title of the page
		report.report("Page title is: " + driver.getTitle());	
		
		takeScreenshot("Final results");
	}
}
