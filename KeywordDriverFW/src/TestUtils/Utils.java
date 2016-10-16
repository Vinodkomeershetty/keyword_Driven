package TestUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import Test.TestController;


public class Utils extends TestController{
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(cal.getTime());
	}

	
	public static void takeScreenShot(String filename) {
		File outputFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(outputFile, new File("D://reports//ScreenShots//"+filename+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
