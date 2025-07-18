package Tests;

import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import Base.BaseTest;
import Pages.LoginPage;
import Utils.ExtentReportManager;

import org.testng.ITestResult;

@Listeners(Utils.ExtentReportManager.class)
public class LoginPageTest extends BaseTest {
	LoginPage loginPage;
	ExtentTest test;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		driver.quit();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.validateLoginPageTitle();
		ExtentReportManager.reportLogInfo("Title fetched: " + actualTitle);
		Assert.assertEquals(actualTitle, "Neostox Virtual Stock Simulator | Trading Simulator | Option Simulator");
		ExtentReportManager.reportLogPass("Title matched successfully.");
	}

	@Test(priority = 2)
	public void neoStockImgTest() {
		Assert.assertTrue(loginPage.validateNeoStockImg());
		ExtentReportManager.reportLogPass("NeoStock image is displayed.");
	}

	@Test(priority = 2)
	public void loginTest() {
		loginPage.login();
		ExtentReportManager.reportLogPass("Login functionality executed.");
	}
}
