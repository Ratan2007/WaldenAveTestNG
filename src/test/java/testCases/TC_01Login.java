package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utilities.TestBase;

public class TC_01Login extends TestBase{
	@Test
	public void login() {
		
		driver.get("http://localhost/opencart/upload/index.php");// localhost
		driver.findElement(By.xpath("//a[@title='My Account']")).click();//click on my account
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("hafiz2001@yahoo.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("B12345");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		//driver.findElement(By.xpath("//a[text()='Logout' and @class='list-group-item']")).click();
	
	}

}
