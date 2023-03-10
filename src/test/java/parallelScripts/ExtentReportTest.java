package parallelScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commonUtils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;


public class ExtentReportTest {
	WebDriver driver;
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentest;
	@BeforeTest
	public void extentsetup() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\Sparkreport.html");
		reports.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		  
	}
  @Test
  public void javatest() {
	  extentest = reports.createTest("Java Search test");
	  driver.get("https://www.google.com");
	  WebElement searchBox = driver.findElement(By.name("q"));
	  searchBox.sendKeys("Java Tutorial");
	  searchBox.submit();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search");
	  softAssert.assertAll();
  }
  @Test
  public void seleniumtest() {
	  extentest = reports.createTest("Selenium Search test");
	  driver.get("https://www.google.com");
	  WebElement searchBox = driver.findElement(By.name("q"));
	  searchBox.sendKeys("Selenium Tutorial");
	  searchBox.submit();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(driver.getTitle(),"Selenium  - Google Search");
	  softAssert.assertAll();
  }
  @AfterMethod
  public void tearout(ITestResult result) throws IOException {
	  if(ITestResult.FAILURE == result.getStatus()) {
		  extentest.log(Status.FAIL, result.getThrowable().getMessage());
		  String strpath = Utility.getScreenshotpath(driver);
		  extentest.addScreenCaptureFromPath(strpath);
	  }
	  driver.close();
  }
  @AfterTest
  public void finishextent() {
	  reports.flush();
  }
}
