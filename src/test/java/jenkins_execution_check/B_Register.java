package jenkins_execution_check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class B_Register  extends B_TestBase{
	
	public B_Register() throws Exception {
		super();
	}	
	
	public WebDriver driver;
	
	@Test
	@BeforeMethod
	public void test1() {
		driver = initializeAndOpenApplication("Chrome");
		driver.findElement(By.linkText("MyAccount")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	@Test(priority=1)
	public void verifyRegisterWithNoInputs() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = "Warning: You must agree to the Privacy Policy!";
		AssertJUnit.assertEquals(actualPrivacyPolicyWarningMessage, expectedPrivacyPolicyWarningMessage);
		
		String actualFirstNameWarningMessage = driver.findElement(By.cssSelector("input#input-firstname+div")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		AssertJUnit.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.cssSelector("input#input-lastname+div")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		AssertJUnit.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.cssSelector("input#input-email+div")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		AssertJUnit.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.cssSelector("input#input-telephone+div")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		AssertJUnit.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = driver.findElement(By.cssSelector("input#input-password+div")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		AssertJUnit.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
	}
	
	@Test(priority=2)
	public void verifyRegisterWithMandatoryDetails() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Ronald");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Mcdonald");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("frenchfriessm@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7039412020");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("IamHungry");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("IamHungry");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
	}
	
	@Test(priority=3)
	public void verifyRegisterWithAllDetails () {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("China");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Boy");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("beeflomeinn@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7039412021");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("IamSuperHungry");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("IamSuperHungry");
		driver.findElement(By.xpath("//input@name = 'newsletter' and @value='1'")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
	}
	
	@Test(priority=4)
	public void verifyRegisterUsingExistingEmail () {	
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("China");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Boy");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("ellatal@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7039412021");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("IamSuperHungry");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("IamSuperHungry");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
	String actualExistingEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
	String expectedExistingEmailWarningMessage = "Warning E-mail Address is already registered";
	AssertJUnit.assertTrue(actualExistingEmailWarningMessage.contains(expectedExistingEmailWarningMessage));
		
	}
	
	@Test(priority=5)
	public void verifyRegisterWithWrontConfirmPassword() {	
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("China");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Boy");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("beeflomein12@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7039412021");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("IamSuperHungry");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("IamSuperHungry1");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.cssSelector("input#input-confirm+div")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
