	package testScripts;
	
	import java.util.List;

import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;
	
	import io.github.bonigarcia.wdm.WebDriverManager;
	
	public class ParaBankTest {
		WebDriver driver;
		@BeforeClass
		public void setup() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
	  @Test(dependsOnMethods = "Reg")
	  public void Login() {
		  driver.get("https://parabank.parasoft.com/parabank/index.htm");
		  driver.findElement(By.xpath("(//div/input[@class='input'])[1]")).sendKeys("Tester");
		  driver.findElement(By.xpath("(//div/input[@class='input'])[2]")).sendKeys("testing");
		  driver.findElement(By.xpath("//div/input[@class='button']")).click();
	//	  SoftAssert softAssert = new SoftAssert();
	//	  softAssert.assertEquals(driver.getTitle(),"ParaBank | Accounts Overview");
	//	  softAssert.assertAll();
		  }
	  @Test(priority=1)
	  public void Reg() {
		  driver.get("https://parabank.parasoft.com/parabank/index.htm");
		  driver.findElement(By.xpath("//p/a[text()='Register']")).click();
		  driver.findElement(By.xpath("//td/input[@id='customer.firstName']")).sendKeys("Tester");
		  driver.findElement(By.xpath("//td/input[@id='customer.lastName']")).sendKeys("Test");
		  driver.findElement(By.xpath("//td/input[@id='customer.address.street']")).sendKeys("xxxxx");
		  driver.findElement(By.xpath("//td/input[@id='customer.address.city']")).sendKeys("yyyyy");
		  driver.findElement(By.xpath("//td/input[@id='customer.address.state']")).sendKeys("aaaaa");
		  driver.findElement(By.xpath("//td/input[@id='customer.address.zipCode']")).sendKeys("00001");
		  driver.findElement(By.xpath("//td/input[@id='customer.phoneNumber']")).sendKeys("9012345678");
		  driver.findElement(By.xpath("//td/input[@id='customer.ssn']")).sendKeys("0987654321");
		  driver.findElement(By.xpath("//td/input[@id='customer.username']")).sendKeys("Tester");
		  driver.findElement(By.xpath("//td/input[@id='customer.password']")).sendKeys("testing");
		  driver.findElement(By.xpath("//td/input[@id='repeatedPassword']")).sendKeys("testing");
		  driver.findElement(By.xpath("//td/input[@class='button']")).click();
		  
		  
		  }
	  @Test(priority=2, dependsOnMethods = "Login")
	  public void Accountprocess() {
		  driver.findElement(By.xpath("//li/a[text()='Open New Account']")).click();
		  Select sel = new Select(driver.findElement(By.xpath("//select[@id='type']")));
		  sel.selectByVisibleText("SAVINGS");
		  driver.findElement(By.xpath("//div/input[@class='button']")).click();	
		  
		  }
	  @Test(priority=3,dependsOnMethods = "Login")
	  public void AccountOverview() throws InterruptedException {
//			  driver.findElement(By.xpath("(//td/a[@class='ng-binding'])[1]")).click();
//		  	  Thread.sleep(3000);
//			  Select sel2 = new Select(driver.findElement(By.xpath("//td/select[@id='month']")));
//			  sel2.selectByVisibleText("February");
//			  driver.findElement(By.xpath("//td/input[@class='button']")).click();
//		  	  
//			  driver.get("https://parabank.parasoft.com/parabank/overview.htm");
//			  List<WebElement> lists = driver.findElements(By.xpath("(//td/a[@class='ng-binding'])[1]"));
//			  for(WebElement list:lists) {
//				  System.out.println(list.getText());
//				  System.out.println(lists.size());
//			  }
		  }
	  @Test(priority=4,dependsOnMethods = "Login")
	  public void TransferFunds() throws InterruptedException {
		  driver.findElement(By.xpath("//li/a[text()='Transfer Funds']")).click();
		  Thread.sleep(3000);
//		  driver.navigate().to("https://parabank.parasoft.com/parabank/transfer.htm");
//		  driver.navigate().refresh();
		  driver.findElement(By.xpath("//form//p/input")).sendKeys("100");
		  Select sel3 = new Select(driver.findElement(By.id("toAccountId")));
		  sel3.selectByIndex(0);
		  driver.findElement(By.xpath("//div/input[@class='button']")).click();
		  Thread.sleep(2000);
//		  driver.close();
		  }
	  
	  @AfterClass
	  public void tearout() {
		  driver.close();
	  }
	
	}
