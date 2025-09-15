package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.ExtentReportManager;
import Utils.ReadPropertiesFile;
import Utils.ReadXLdata;

@Listeners(Utils.ExtentReportManager.class)
public class HomePageTest extends BaseTest {

	LoginPage logInPage;
	HomePage homePage;

	@BeforeMethod
	public void setUP() throws InterruptedException {
		initialization();
		logInPage = new LoginPage();
		homePage = logInPage.login();
	}

	@AfterMethod
	public void tearDown() {
		BaseTest.getDriver().quit();
	}

	@Test
	public void validateHomepageTitle() {
		try {
			ExtentReportManager.reportLogInfo("User logged in successfully.");
			String title = homePage.validateTitle();
			ExtentReportManager.reportLogInfo("Home Page Title: " + title);
			Assert.assertEquals(title, "Trade Panel :: Neostox", "Title is mismatching");
			ExtentReportManager.reportLogPass("Home Page title validation passed.");
			Thread.sleep(9000); // Use Thread.sleep instead of driver.wait
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "tradeData")
	public void placeEquityOrderTest(String securityName, String securityType, String quantity, String purchaseType) {
		ExtentReportManager.reportLogInfo("Placing Equity Order with: " + "Security = " + securityName + ", Type = "
				+ securityType + ", Qty = " + quantity + ", Buy/Sell = " + purchaseType);

		homePage.placeEquityOrder(securityName, securityType, quantity, purchaseType);

		ExtentReportManager.reportLogPass("Equity order placed successfully.");
	}

	@DataProvider()
	public Object[][] tradeData() throws IOException {
		String excelName = ReadPropertiesFile.readDataFromPropertiesFile("testDataExcelName");
		String sheetName = ReadPropertiesFile.readDataFromPropertiesFile("testDataExcelSheetName");

		ExtentReportManager.reportLogInfo("Fetching test data from Excel: " + excelName + " | Sheet: " + sheetName);

//		System.out.println(excelName);
//		System.out.println(sheetName);

		return ReadXLdata.getData(excelName, sheetName);
	}
	
//	@Test
//	public void validationNavigationMenuTest() {
//		homePage.validateNavigationMenu();
//		homePage.validateStrategiesSubLinks();
//		homePage.validateScreensSubLinks();
//		homePage.validateReportsSubLinks();
//		
//	}
}
