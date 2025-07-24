package Tests;

import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import Base.BaseTest;
import Pages.LoginPage;
import Utils.ExtentReportManager;

import org.testng.ITestResult;

@Listeners(Utils.ExtentReportManager.class)
public class LoginPageTest extends BaseTest {
	// Thread-local objects to isolate per-thread test state
    private ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

//    @BeforeSuite
//    public void cleanOldReports() {
//        File reportDir = new File("reports");
//        if (reportDir.exists()) {
//            for (File file : reportDir.listFiles()) {
//                file.delete();
//            }
//        }
//    }
    @BeforeMethod
    public void setup() {
        initialization(); // each thread gets its own WebDriver
        loginPage.set(new LoginPage()); // create new instance per test thread
    }

	@AfterMethod
	public void teardown(ITestResult result, Method method) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			ExtentReportManager.reportLogFail(methodName + " failed: " + result.getThrowable());
		}
		BaseTest.getDriver().quit();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.get().validateLoginPageTitle();
		System.out.println(actualTitle);
		ExtentReportManager.reportLogInfo("Title fetched: " + actualTitle);
		Assert.assertEquals(actualTitle, "Neostox Virtual Stock Simulator | Trading Simulator | Option Simulator");
		ExtentReportManager.reportLogPass("Title matched successfully.");
	}

	@Test(priority = 2)
	public void neoStockImgTest() {
		Assert.assertTrue(loginPage.get().validateNeoStockImg());
		System.out.println("NeoStock image is displayed.");
		ExtentReportManager.reportLogPass("NeoStock image is displayed.");
	}

	@Test(priority = 2)
	public void loginTest() throws InterruptedException {
		loginPage.get().login();
		System.out.println("Login functionality executed.");
		ExtentReportManager.reportLogPass("Login functionality executed.");
	}
}
