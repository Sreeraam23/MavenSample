package testScripts;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginPageTest {
	WebDriver driver;
	ExtentTest test;
	ExtentReports report;
	ExtentSparkReporter sparkle;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("http://the-internet.herokuapp.com/login");
	}
	@BeforeTest
	public void extent() {
	report = new ExtentReports();
	sparkle = new ExtentSparkReporter("target//login.html");
	report.attachReporter(sparkle);
	}
  @Test(dataProvider = "logindata")
  public void validlogintest(String username,String password) throws InvalidFormatException, IOException, SAXException, ParserConfigurationException, InterruptedException {
	  Thread.sleep(3000);
		

		driver.findElement(By.id(readData("username"))).sendKeys(username);

		driver.findElement(By.id(readData("password"))).sendKeys(password);

		driver.findElement(By.className(readXmlData("btn"))).click();

//		boolean isdisp = driver.findElement(By.cssSelector(readXmlData("msg"))).isDisplayed();
//		Assert.assertTrue(isdisp);
	  
  }
  @DataProvider(name="logindata")
  public Object[][] getdata() throws CsvValidationException, IOException{
	  String path = System.getProperty("user.dir")+"//src//test//resources//DataFolder//logindata.csv";
	  CSVReader reader = new CSVReader(new FileReader(path));
	  String[] cols;
	  ArrayList<Object> datalist = new ArrayList<Object>();
	  while((cols = reader.readNext())!=null) {
		  Object[] record = {cols[0],cols[1]};
		  datalist.add(record);
	  }
	  return datalist.toArray(new Object[datalist.size()][]);
	  
  }
  public String readData(String ObjName) throws InvalidFormatException, IOException {
	  String Objpath = " ";
	  String path = System.getProperty("user.dir")+"//src//test//resources//DataFolder//data.xlsx";
	  XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
	  XSSFSheet sheet = workbook.getSheet("loginPage");
	  int Numrows = sheet.getLastRowNum();
	  for(int i = 1;i <= Numrows;i++) {
		  	XSSFRow row = sheet.getRow(i);
		  	if(row.getCell(0).getStringCellValue().equalsIgnoreCase(ObjName)) {
		  		Objpath  = row.getCell(1).getStringCellValue();
		  	}
	  }
	  return Objpath;
  }
  public String readXmlData(String tagname) throws ParserConfigurationException, SAXException, IOException {
	  String path=System.getProperty("user.dir")+"//src//test//resources//DataFolder//xmlObjRepo.xml";
	  File file=new File(path);
	  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	  DocumentBuilder build=factory.newDocumentBuilder();
	  Document document = build.parse(file);
	  NodeList list= document.getElementsByTagName("ObjRep");
	  Node node1= list.item(0);
	  Element elem=(Element)node1;
	  return elem.getElementsByTagName(tagname).item(0).getTextContent();
  }
  @AfterTest
  public void finish(ITestResult result) {
	  if(ITestResult.FAILURE == result.getStatus()) {
		  test.log(Status.FAIL, result.getThrowable().getMessage());
	  }
	  report.flush();
  }
@AfterMethod
  public void tearout() {
	  driver.close();
  }
}
