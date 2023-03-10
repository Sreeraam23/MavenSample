package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest2 {
	WebDriver driver;
	@BeforeMethod
	  //@BeforeTest
	  public void setup() {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
	  }
	 @Test(retryAnalyzer = IRetrysampleTest.class)
	  public void javasearchtest() {
//		  	WebDriverManager.firefoxdriver().setup();
//			WebDriver driver = new FirefoxDriver();
//			driver.manage().window().maximize();	
			driver.get("https://www.google.com");	
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Java Tutorial");
			searchBox.submit();
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search Page");
			softAssert.assertAll();

			
	  }
//		@Test(enabled=false)
//		public void seleniumsearchtest() {
////			System.setProperty("webdriver.chrome.driver", "E:\\Workspace\\Webdriver\\chromedriver.exe");
////			WebDriver driver = new ChromeDriver();
////			WebDriverManager.edgedriver().setup();
////			WebDriver driver = new EdgeDriver();
////			driver.manage().window().maximize();
//			driver.get("https://www.google.com");	
//			WebElement searchBox = driver.findElement(By.name("q"));
//			searchBox.sendKeys("Selenium Tutorial");
//			searchBox.submit();
////			Assert.assertEquals(driver.getTitle(),"Selenium Tutorial - Google Search Page");
//			SoftAssert softAssert = new SoftAssert();
//			softAssert.assertEquals(driver.getTitle(),"Selenium Tutorial - Google Search Page");
//			softAssert.assertAll();
//				
//		  }
//		@Test (alwaysRun=true, dependsOnMethods = "javasearchtest" )
//		  public void cucumbertest() {
////			  	WebDriverManager.firefoxdriver().setup();
////				WebDriver driver = new FirefoxDriver();
////				driver.manage().window().maximize();	
//				driver.get("https://www.google.com");	
//				WebElement searchBox = driver.findElement(By.name("q"));
//				searchBox.sendKeys("Cucumber Tutorial");
//				searchBox.submit();
//				SoftAssert softAssert = new SoftAssert();
//				softAssert.assertEquals(driver.getTitle(),"Cucumber Tutorial - Google Search");
//				softAssert.assertAll();
//		}
		@AfterMethod
		//@AfterTest
		public void finish() {
			driver.close();
		}
}
