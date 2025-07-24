package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class LoginPage extends BaseTest {
	
//	Page Factory or Object Repository:
	@FindBy(id="txt_mobilenumber")
	WebElement text_userID_loginPage;

	@FindBy(id="accessPinModel_accsspin")
	WebElement text_password_loginPage;
	
	@FindBy(id="frmsubmit")
	WebElement btn_Submit;
	
	@FindBy(className ="navbar-brand")
	WebElement img_NeoStock_loginPage;
	
	@FindBy(xpath = "//a[text()= 'Sign In']")
	WebElement btn_signIn_loginPage;
	
	
//	Page Factory for initializing page objects:
	public LoginPage() {
		PageFactory.initElements(BaseTest.getDriver(), this);
//		System.out.println("PF driver = " +driver);
	}
	
	
//	Actions: 
	public String validateLoginPageTitle() {
		return BaseTest.getDriver().getTitle();
	}
	
	public Boolean validateNeoStockImg() {
		return img_NeoStock_loginPage.isDisplayed();
	}
	
	public HomePage login() throws InterruptedException {
		btn_signIn_loginPage.click();

		text_userID_loginPage.sendKeys(properties.getProperty("LoginId"));
		btn_Submit.click();


		text_password_loginPage.sendKeys(properties.getProperty("Password"));
		WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(20));
		WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(btn_signIn_loginPage));
		btn_Submit.click();
		//Thread.sleep(9000); 
		//text_password_loginPage.click();

		//after login we will land in home page, so we are creating home page obj and sending from here
		return new HomePage();
	}
}
