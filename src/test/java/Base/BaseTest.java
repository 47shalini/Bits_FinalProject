package Base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import Utils.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	// ThreadLocal WebDriver for parallel execution
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static Properties properties = new Properties();

	// Constructor - loads config file
	public BaseTest() {
		try {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configFile\\Config.properties");
			properties.load(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to get browser value from properties
	public static String getBrowser() {
		return properties.getProperty("browser").trim();
	}

	// Getter for WebDriver instance
	public static WebDriver getDriver() {
		return driver.get();
	}

	// Initialize WebDriver per thread
	public void initialization() {
		try {
			String browser = getBrowser().toLowerCase();
			System.out.println("Browser from config: " + browser);

			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
				System.out.println("Chrome launched");
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
				driver.set(new FirefoxDriver());
				System.out.println("Firefox launched");
			} else if (browser.equals("edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
				driver.set(new EdgeDriver());
				System.out.println("Edge launched");
			} else {
				throw new RuntimeException("Browser not supported: " + browser);
			}

			getDriver().get(properties.getProperty("URL"));
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.implicitly_Wait));
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.pageLoadTimeOut));
			System.out.println("Running on: " + ((RemoteWebDriver) getDriver()).getCapabilities().getBrowserName());

		} catch (Exception e) {
			System.out.println("Exception during browser setup: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Quit and clean up WebDriver instance
	public static void quitDriver() {
		getDriver().quit();
		driver.remove();
	}
}
