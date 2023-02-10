package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class TestBase {
	public static WebDriver driver;
	public ResourceBundle rb;
	public Logger logger;

	static Properties prop;
	static File file;
	static FileInputStream inputStream;

	public static void loadProperty(String name) throws IOException {
		prop = new Properties();
		file = new File(TestBasePageObject.folderPath);
		inputStream = new FileInputStream(file);
		prop.load(inputStream);
	}

	public static String getProperty(String x) {
		return prop.getProperty(x);
	}


	@BeforeClass()
	public void setupDriver() {
//		rb = ResourceBundle.getBundle("Opencart");
//		logger = LogManager.getLogger(this.getClass());
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\SKRAT\\eclipse-workspace03\\WaldenAve210\\src\\test\\resources\\Driver\\chromedriver.exe");

//		//remove automation message from chrome browser
//		ChromeOptions options=new ChromeOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});

//		//remove automation message from firefox browser
//		FirefoxOptions options=new FirefoxOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});

		// WebDriverManager.chromedriver().setup(); //selenium 4 dont need webdriver
		// manager also
		// ChromeOptions options = new ChromeOptions(); // initializing chrome options
		// options.addArguments("--start-maximized"); //add argument to maximize browser
		// options.addArguments("--disable-notifications"); //add argument to diable
		// notifications
		// options.addArguments("--headless"); //headless execution
		// driver = new ChromeDriver(options);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass()
	public void teardown() throws InterruptedException {
		driver.close();
		driver.quit();
	}

	public String takeScreenshot(String screenshotName) {

//		SimpleDateFormat sf=new SimpleDateFormat();
//		Date date=new Date();
//		String dformat=sf.format(date);

		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_h_m_s").format(new Date());// combination of above 3
																							// line
		String directory = System.getProperty("user.dir") + "/Screenshot/" + screenshotName + dateFormat + ".png";

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(directory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return directory;
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomAlphaNumber() {
		String str = RandomStringUtils.randomNumeric(5);
		String num = RandomStringUtils.randomAlphabetic(5);
		return (str + num);
	}

	public String randomNumber() {
		String generatedStringNumber = RandomStringUtils.randomNumeric(5);
		return generatedStringNumber;
	}
}
