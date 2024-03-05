package jenkins_execution_check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class B_SearchProduct extends B_TestBase {
	
	public B_SearchProduct() throws Exception {
		super();
	}	
	
public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
           driver = initializeAndOpenApplication("Chrome");
	}
	
	@Test(priority=1)
	public void verifySearchValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		AssertJUnit.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("DELL");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is not product that matches the search criteria.']")).isDisplayed());
		
	}
	
	@Test(priority=3)
	public void verifySearchWithNoProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is not product that matches the search criteria.']")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
