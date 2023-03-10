package testScripts;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PreferencesTest {
	WebDriver driver;
//	@BeforeClass
//	public void setup() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//	}
//  @Test
//  public void downloadfile() throws InterruptedException {
//	  WebDriverManager.chromedriver().setup();
//	  ChromeOptions options = new ChromeOptions();
//	  Map<String,Object> prefs = new HashMap<String,Object>();
//	  prefs.put("download.prompt_for_download", false);
//	  driver = new ChromeDriver(options);
//	  driver.get("https://chromedriver.storage.googleapis.com/index.html?path=110.0.5481.77/");
//	  Thread.sleep(2000);
//	  WebElement btn = driver.findElement(By.xpath("//td/a[text()='chromedriver_win32.zip']"));
//	  btn.click();
//	  Thread.sleep(7000);
//	  //https://blueimp.github.io/jQuery-File-Upload/
//  }
  @Test
  public void uploadfile() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
//	  ChromeOptions options = new ChromeOptions();
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	  WebElement addfile = driver.findElement(By.xpath(".//input[@type='file']"));
	  String filepath = System.getProperty("user.dir")+ "//test.png"; 
	  addfile.sendKeys(filepath);
	  driver.findElement(By.xpath(".//span[text()='Start upload']"));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  if(driver.findElement(By.xpath(".//a[text()=''test.png]")).isDisplayed()) {
		  Assert.assertTrue(true,"Image Uploaded");
	  }
	  else {
		  Assert.assertTrue(false,"Image Uploade failed");
	  }
	  
  }
//  @AfterClass
//  public void tearout() {
//	  driver.close();
//  }
}
