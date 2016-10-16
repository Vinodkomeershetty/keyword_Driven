package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Data.Xls_Reader;
import ReportUtils.ReportUtil;
import TestUtils.Utils;

public class TestController {

	public static Xls_Reader testcaseReader;
	public static Xls_Reader testDataReader;
	
	public static WebDriver dr;
	public static EventFiringWebDriver driver;
	
	public static Properties OR;
	public static Properties AppText;
	
	
	public static String currentTestcase;
	public static String Description;
	public static String Keyword;
	public static String WebElement;
	public static String ProceedOnFail;
	public static String TestData;
	public static String TestDataVal;

	
	
	public static void Initialize() throws IOException {
		OR = new Properties();
		FileInputStream FI = new FileInputStream(System.getProperty("user.dir")+"//src//Config//OR.properties");
		OR.load(FI);
		
		AppText = new Properties();
		FI = new FileInputStream(System.getProperty("user.dir")+"//src//Config//AppText.properties");
		AppText.load(FI);
		
		testcaseReader =  	 new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\testSuite1.xlsx".replace("\\", File.separator));
		testDataReader = new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\Data.xlsx".replace("\\", File.separator));
	}
	
	@Test
	public void TestDriverController() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		// current date
		String startTime = Utils.now("dd.MMMM.yyyy hh.mm.ss aaa");
		
		// start reporting
		ReportUtil.startTesting("D://reports//index.html", startTime, "Test", "1.5");
		
		// start the suite
		ReportUtil.startSuite("Suite1");
		
		// status
		String status="Pass";
		
		// read all test cases
		for(int TC=2;TC<=testcaseReader.getRowCount("test cases");TC++) {
			
			// current test case name
			currentTestcase = testcaseReader.getCellData("test cases", "TCID", TC);
			String runMode=testcaseReader.getCellData("test cases", "RunMode", TC);
			
			if(runMode.equals("Y")) {
				
				int dataRows=testDataReader.getRowCount(currentTestcase);
				if(dataRows<2) {
					dataRows=2;
				}
				
				// loop through the data(parametrization)
				for(int testdata=2;testdata<=dataRows;testdata++) {
					
					// read all test steps
					for(int TS=2;TS<=testcaseReader.getRowCount(currentTestcase);TS++) {
						String filename;
						Description=testcaseReader.getCellData(currentTestcase, "Description", TS);
						Keyword=testcaseReader.getCellData(currentTestcase, "Keyword", TS);
						WebElement=testcaseReader.getCellData(currentTestcase, "WebElement", TS);
						ProceedOnFail=testcaseReader.getCellData(currentTestcase, "ProceedOnFail", TS);
						TestData=testcaseReader.getCellData(currentTestcase, "TestData", TS);
						String testStep = testcaseReader.getCellData(currentTestcase, "TSID", TS);
						TestDataVal=testDataReader.getCellData(currentTestcase, TestData, testdata);
							
						Method method= Keywords.class.getMethod(Keyword);
						String result = (String) method.invoke(method);
						filename=currentTestcase+"["+testdata+"]"+testStep;
						if(result.contains("Fail")) {
							// report error
							// take screenshot
							Utils.takeScreenShot(filename);
							ReportUtil.addKeyword(Description, Keyword, result, "ScreenShots//"+filename+".jpg");
							status="Failed";
						} else {
							ReportUtil.addKeyword(Description, Keyword, result, null);
						}		
					}
					ReportUtil.addTestCase(currentTestcase+"["+(testdata-1)+"]", startTime, Utils.now("dd.MMMM.yyyy hh.mm.ss aaa"), status);
										
				}
				
			} else {
				// skipping the test case
				// report it
				ReportUtil.addTestCase(currentTestcase, startTime, Utils.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped");
			}
		}
		ReportUtil.endSuite();
		ReportUtil.updateEndTime(Utils.now("dd.MMMM.yyyy hh.mm.ss aaa"));
	}
	//@After
	@AfterClass
	public static void closeBrowser() {
		driver.close();
	}

	//@Before
	@BeforeClass
	public static void InitBrowser() throws IOException {
		//dr = new InternetExplorerDriver();
		dr = new FirefoxDriver();
		driver = new EventFiringWebDriver(dr);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Initialize();
	}

}
