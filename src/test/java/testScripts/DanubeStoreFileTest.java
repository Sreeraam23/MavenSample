package testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import com.google.common.base.Objects;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DanubeStoreFileTest {
	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	  public void property() throws IOException {
		  String path = System.getProperty("user.dir")+"\\src\\test\\resources\\config\\danube.properties";
		  prop = new Properties(); 
		  prop.load(new FileInputStream(path));
		}
	@BeforeTest
	 public void setup() {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(prop.getProperty("url"));
	  } 
  @Test(dataProvider="signupdata")
  public void Reg(String username,String surname, String mail,String password) throws InterruptedException, InvalidFormatException, IOException, ParserConfigurationException, SAXException {
	  Thread.sleep(3000);
	  driver.findElement(By.id(location("signup-btn"))).click();
	  driver.findElement(By.id(readXmlData("name"))).sendKeys(username);
	  driver.findElement(By.id(readXmlData("surname"))).sendKeys(surname);
	  driver.findElement(By.id(location("email"))).sendKeys(mail);
	  driver.findElement(By.id(readXmlData("pass"))).sendKeys(password);
	  driver.findElement(By.id(location("my"))).click();
	  driver.findElement(By.id(location("Agreement"))).click();
	  driver.findElement(By.id(location("Privacy Policy"))).click();
	  driver.findElement(By.id(location("Reg"))).click();
//	  SoftAssert softAssert = new SoftAssert();
//	  softAssert.assertEquals(driver.findElement(By.id("login-message")).getText(),"Welcome back, "+mail);
//	  softAssert.assertAll();
  }
  @Test(enabled=false)
  public void Login() {
	  driver.findElement(By.id("login")).click();
	  driver.findElement(By.id("n-password2")).sendKeys(prop.getProperty("password"));
	  driver.findElement(By.id("goto-signin-btn")).click();
	  	  
  }
  @Test(priority=2)
  public void search() throws InterruptedException {
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys(prop.getProperty("search"));
	  driver.findElement(By.id("button-search")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.className("preview")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("(//div/div/button)[6]")).click();	
  }
  @DataProvider(name="signupdata")
   public Object[][] getdata() throws CsvValidationException, IOException{
	  String path = System.getProperty("user.dir")+"//src//test//resources//DataFolder//signup.csv";
	  CSVReader reader = new CSVReader(new FileReader(path));
	  String[] col;
	  ArrayList<Object> datalist = new ArrayList<Object>();
	  while((col = reader.readNext())!=null) {
		  Object[] record = {col[0],col[1],col[2],col[3]};
		  datalist.add(record);
	  }
	  return datalist.toArray(new Object[datalist.size()][]);
  }
  public String location(String Name) throws InvalidFormatException, IOException {
	  String Objpath = " ";
	  String path = System.getProperty("user.dir")+"//src//test//resources//DataFolder//danube_data.xlsx";
	  XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
	  XSSFSheet sheet = workbook.getSheet("Signup");
	  int nrows = sheet.getLastRowNum();
	  for(int i = 1;i <= nrows;i++) {
		  XSSFRow row = sheet.getRow(i);	
		  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(Name)) {
			  Objpath = row.getCell(1).getStringCellValue();
		  }
	  }
	  return Objpath;
  }
  public String readXmlData(String tagname) throws ParserConfigurationException, SAXException, IOException {
	  String path = System.getProperty("user.dir")+"//src//test//resources//DataFolder//danubetloc.xml";
	  File file = new File(path);
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder build = factory.newDocumentBuilder();
	  Document document = build.parse(file);
	  NodeList list = document.getElementsByTagName("repo");
	  Node node1 = list.item(0);
	  Element elem = (Element)node1;
	  return elem.getElementsByTagName(tagname).item(0).getTextContent();
  }
  @Test(dependsOnMethods = "search")
  public void checkout() throws AWTException, InterruptedException, InvalidFormatException, IOException {
	  driver.findElement(By.id(location("name"))).sendKeys(prop.getProperty("username"));
	  driver.findElement(By.id(location("surname"))).sendKeys(prop.getProperty("surname"));
	  driver.findElement(By.id(location("adress"))).sendKeys(prop.getProperty("address"));
	  driver.findElement(By.id(location("zipcode"))).sendKeys(prop.getProperty("zip"));
	  driver.findElement(By.id(location("city"))).sendKeys(prop.getProperty("city"));
	  driver.findElement(By.id(location("company"))).sendKeys("Zuci");
	  driver.findElement(By.id(location("soon"))).click();
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
  @AfterTest
  public void tearout() {
	  driver.close();
  }
  
}
