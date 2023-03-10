package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertiesTest {
	WebDriver driver;
  @Test
  public void urllist() throws IOException {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    Properties prop = new Properties();
	    FileInputStream file = new FileInputStream("Config Properties");
	    prop.load(file);
		String url;
		url = prop.getProperty("url");
		driver.get(url);
		
		
		
		
		
//		file.close();
		
		
	  
  }
}
