package testScripts;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleNewTest {
	WebDriver driver;
	Properties prop;
	@BeforeMethod
  //@BeforeTest
  public void setup() {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  
  } 

  @Test(priority=1)
  public void javasearchtest() {
//	  	WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().window().maximize();	
		driver.get(prop.getProperty("url"));	
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search Page");
		softAssert.assertAll();

		
  }
  @BeforeTest
  public void property() throws IOException {
	  String path = System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties";
	  prop = new Properties(); 
	  prop.load(new FileInputStream(path));
  }
	@Test(enabled=true)
	public void seleniumsearchtest() {
//		System.setProperty("webdriver.chrome.driver", "E:\\Workspace\\Webdriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		WebDriverManager.edgedriver().setup();
//		WebDriver driver = new EdgeDriver();
//		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));	
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
//		Assert.assertEquals(driver.getTitle(),"Selenium Tutorial - Google Search Page");
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(driver.getTitle(),"Selenium Tutorial - Google Search Page");
//		softAssert.assertAll();
			
	  }
	@Test (priority=2)
	  public void cucumbertest() {
//		  	WebDriverManager.firefoxdriver().setup();
//			WebDriver driver = new FirefoxDriver();
//			driver.manage().window().maximize();	
			driver.get("https://www.google.com");	
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Cucumber Tutorial");
			searchBox.submit();
//			 	
	}
	@AfterMethod
	//@AfterTest
	public void finish() {
		driver.close();
	}

}
