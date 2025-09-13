package Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		//String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
		String reportPath = System.getProperty("user.dir") + "/test-output/Extent.html";
		System.out.println("Extent Report saved at: " + reportPath);
		//sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/myReport. html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Shalini");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", Base.BaseTest.getBrowser());
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) {
		getTest().log(Status.PASS, "Test case PASSED: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		getTest().log(Status.FAIL, "Test case FAILED: " + result.getName());
		getTest().log(Status.FAIL, "Failure Reason: " + result.getThrowable());

		try {
			String path = CapturingScreenshot.getScreenshot(result.getName());
			getTest().addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		getTest().log(Status.SKIP, "Test case SKIPPED: " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private static ExtentTest getTest() {
		return test.get();
	}

	public static void reportLogInfo(String message) {
		if (getTest() != null) {
			getTest().log(Status.INFO, message);
		} else {
			System.out.println("[INFO] " + message);
		}
	}

	public static void reportLogPass(String message) {
		if (getTest() != null) {
			getTest().log(Status.PASS, message);
		} else {
			System.out.println("[PASS] " + message);
		}
	}

	public static void reportLogFail(String message) {
		if (getTest() != null) {
			getTest().log(Status.FAIL, message);
		} else {
			System.out.println("[FAIL] " + message);
		}
	}
}
