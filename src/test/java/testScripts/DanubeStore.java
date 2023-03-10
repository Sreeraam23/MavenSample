package testScripts;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DanubeStore {
	WebDriver driver;
	Properties prop;
	ExtentReports report;
	ExtentSparkReporter spark;
	ExtentTest test;
	@BeforeTest
	public void report() {
		report = new ExtentReports();
		spark = new ExtentSparkReporter("target\\report.html");
		report.attachReporter(spark);
	}
	@BeforeClass
	  public void property() throws IOException {
		  String path = System.getProperty("user.dir")+"\\src\\test\\resources\\config\\danube.properties";
		  prop = new Properties(); 
		  prop.load(new FileInputStream(path));
		  
		  
		}
	@BeforeClass
	 public void setup() {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(prop.getProperty("url"));
	  } 
  @Test
  public void Reg() throws InterruptedException {
	  test = report.createTest("Reg");
	  Thread.sleep(3000);
	  driver.findElement(By.id("signup")).click();
	  driver.findElement(By.id("s-name")).sendKeys(prop.getProperty("username"));
	  driver.findElement(By.id("s-surname")).sendKeys(prop.getProperty("surname"));
	  driver.findElement(By.id("s-email")).sendKeys(prop.getProperty("email"));
	  driver.findElement(By.id("s-password2")).sendKeys(prop.getProperty("password"));
	  driver.findElement(By.id("myself")).click();
	  driver.findElement(By.id("marketing-agreement")).click();
	  driver.findElement(By.id("privacy-policy")).click();
	  driver.findElement(By.id("register-btn")).click();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(driver.findElement(By.id("login-message")).getText(),"Welcome back, "+prop.getProperty("email"));
	  softAssert.assertAll();
  }
  @Test(enabled = false)
  public void Login() {
		  driver.findElement(By.id("login")).click();
		  driver.findElement(By.id("n-password2")).sendKeys(prop.getProperty("password"));
		  driver.findElement(By.id("goto-signin-btn")).click();
		  	  
  }
  @Test(dependsOnMethods = "Reg",priority=2)
  public void search() throws InterruptedException {
	  test = report.createTest("search");
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys(prop.getProperty("search"));
	  driver.findElement(By.id("button-search")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.className("preview")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("(//div/div/button)[6]")).click();	
  }

  @Test(dependsOnMethods = "search")
  public void checkout() throws AWTException, InterruptedException, InvalidFormatException, IOException {
	  test = report.createTest("checkout");
	  driver.findElement(By.id("s-name")).sendKeys(prop.getProperty("username"));
	  driver.findElement(By.id("s-surname")).sendKeys(prop.getProperty("surname"));
	  driver.findElement(By.id("s-address")).sendKeys(prop.getProperty("address"));
	  driver.findElement(By.id("s-zipcode")).sendKeys(prop.getProperty("zip"));
	  driver.findElement(By.id("s-city")).sendKeys(prop.getProperty("city"));
	  driver.findElement(By.id("s-company")).sendKeys("Zuci");
	  driver.findElement(By.id("asap")).click();
	  Robot robot = new Robot();
	  for (int i = 0; i < 2; i++) {
		  robot.keyPress(KeyEvent.VK_CONTROL);
		  robot.keyPress(KeyEvent.VK_SUBTRACT);
		  robot.keyRelease(KeyEvent.VK_SUBTRACT);
		  robot.keyRelease(KeyEvent.VK_CONTROL);
		  }
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[contains(text(),'Buy')]")).click();
  }
  @AfterMethod
  public void finish(ITestResult result) {
	  if(ITestResult.FAILURE == result.getStatus()) {
		  test.log(Status.FAIL, result.getThrowable().getMessage());
	  }
	  report.flush();
  }
  @AfterClass
  public void tearout() {
	  driver.close();
  }
}
