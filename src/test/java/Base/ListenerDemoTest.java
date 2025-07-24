package Base;
import org.testng.Assert;
import org. testng. annotations. Test;

public class ListenerDemoTest extends BaseTest{
	//Here we are intentionally failing the test case to show that we handled the testNG listeners
	@Test
	public void launchApp() {
		initialization();
		BaseTest.getDriver().get("https://ebay.com");
		Assert.assertTrue(false);
	}
}
