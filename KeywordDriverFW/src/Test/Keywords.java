package Test;

import org.openqa.selenium.By;

public class Keywords extends TestController{

	
	public static String Navigate() {
		System.out.println("inside navigate");
		driver.get(OR.getProperty("site_url"));
		return "Pass";
	}
	public static String ClickLink() {
		System.out.println("inside ClickLink");
		try {	
		driver.findElement(By.xpath(OR.getProperty(WebElement))).click();
		} catch (Throwable t) {
			// report error
			return "Fail - "+WebElement+" Element not found";
		}
		return "Pass";
	}
	public static String VerifyText() {
		System.out.println("inside VerifyText");
		String actualVal;
		try {
		actualVal=driver.findElement(By.xpath(OR.getProperty(WebElement))).getText();
		}catch (Throwable t) {
			// report error
			return "Fail - "+WebElement+" Element not found";
		}
		if(!actualVal.equals(AppText.getProperty(WebElement))) {
			// report error
			return "Fail - Actual value "+actualVal+" and Expected value "+AppText.getProperty(WebElement)+" are not same";
		}
		return "Pass";
	}

	
	public static String VerifySheetText() {
		System.out.println("inside VerifyText");
		String actualVal;
		try {
		actualVal=driver.findElement(By.xpath(OR.getProperty(WebElement))).getText();
		}catch (Throwable t) {
			// report error
			return "Fail - "+WebElement+" Element not found";
		}
		if(!actualVal.equals(TestDataVal)) {
			// report error
			return "Fail - Actual value "+actualVal+" and Expected value "+TestDataVal+" are not same";
		}
		return "Pass";
	}
	
	
	
	public static String InputText() {
		System.out.println("inside InputText");
		try {	
		driver.findElement(By.xpath(OR.getProperty(WebElement))).sendKeys(TestDataVal);
		} catch (Throwable t) {
			// report error
			return "Fail - "+WebElement+" Element not found";
		}
		return "Pass";
	}
	
	
}
