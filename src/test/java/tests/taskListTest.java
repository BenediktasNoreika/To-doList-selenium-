package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import page.createList;


public class taskListTest {
	
	private static RemoteWebDriver driver;
	private static Logger LOGGER = Logger.getGlobal();
	
	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/chromedriver.exe");
		
		
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Before
	public void setup() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			LOGGER.warning("Connecting to the site...");
			driver.get(createList.URL);
		} catch (TimeoutException e) {
			System.out.println("Page - " + createList.URL + " - timedout");
		}
	}
	
	
	@Test
	public void createList() {
		LOGGER.info("Creating new task List");
		createList index = PageFactory.initElements(driver, createList.class);
		index.createList("new test task list");
		
		// find the customer table
	    String result = driver.findElementByXPath("//*[contains(text(), 'new test task list')]").getText();

		assertEquals("new test task list", result);
		
	}
	
	@Test
	public void deleteList() {
		
		createList index = PageFactory.initElements(driver, createList.class);
		index.createList("toDelete");
		String before = driver.findElementByXPath("//*[contains(text(), 'toDelete')]").getText();
		index.deleteList();
		
		// find the customer table
		try {
		String after = driver.findElementByXPath("//*[contains(text(), 'toDelete')]").getText();
		assertEquals(before, after);
		}
		catch(Exception e) {
			String after = "empty";
			assertNotEquals(before, after);
		}
		
		
	}
	
	@Test
	public void createTask() {
		
		
		createList index = PageFactory.initElements(driver, createList.class);
		index.createList("addTask");
		index.createTask("testName","testDate");
		
		 String result = driver.findElementByXPath("//*[contains(text(), 'testName')]").getText();
		 assertEquals("testName", result);
		 
		
	}
	
	@Test
	public void editTask() {
		
		
		createList index = PageFactory.initElements(driver, createList.class);
		index.createList("editTask");
		index.createTask("testName","testDate");
		String before = driver.findElementByXPath("//*[contains(text(), 'testName')]").getText();
		index.editTask("changed");
		
		 String result = driver.findElementByXPath("//*[contains(text(), 'changed')]").getText();
		 assertNotEquals(before, result);
		 
		
	}
	
	
	
	
	
	@AfterClass
	public static void tearDown() {
		LOGGER.warning("Closing webdriver instance");
		driver.quit();
		LOGGER.info("Logger closed successfully");
	}
	
}