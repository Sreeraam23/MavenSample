package parallelScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest1 {
	WebDriver driver;
	  @Test(groups="feature3")
	  public void testOne() {
		  System.out.println("TestOne form Sample One");
	  }
	  @Test(groups="feature3")
	  public void testSecond() {
		  System.out.println("TestSecond form Sample Second");
	  }
	  @Parameters("browser2")
	  @Test(groups="feature5")
	  public void testThree(String stredge) {
		  if(stredge.equalsIgnoreCase("edge")) {
			  WebDriverManager.edgedriver().setup();
			  driver = new EdgeDriver();
			  driver.manage().window().maximize();
			  driver.get("https://www.google.com");
//			  searchBox.sendKeys("Java Tutorial");
//			  searchBox.submit();
//			  SoftAssert softAssert = new SoftAssert();
//			  softAssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search Page");
//			  softAssert.assertAll();
			  }
	  }
	  @Parameters("browser1")
	  @Test(groups="feature5")
	  public void testFour(String strchrome) {
		  if(strchrome.equalsIgnoreCase("chrome")) {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://www.google.com");
		  }
	  }
	  @AfterMethod
		//@AfterTest
		public void finish() {
			driver.close();
		}
}
