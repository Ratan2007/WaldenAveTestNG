package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BrowserFactory {
	public WebDriver driver;
	public BrowserFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
