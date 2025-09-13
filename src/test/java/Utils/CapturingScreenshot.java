package Utils;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseTest;

public class CapturingScreenshot extends BaseTest {

	public static String getScreenshot(String testName) throws IOException {
		String screenshotName = testName + "_" + System.currentTimeMillis() + ".png";
		String destinationPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;

		File screenshotFile = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(destinationPath));

		return destinationPath;
	}

	public static String getScreenshot() throws IOException {
		return getScreenshot("Exception");
	}
	
}
