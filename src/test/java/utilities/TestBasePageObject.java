package utilities;

public interface TestBasePageObject {
	public final String chromeDriverName = "webdriver.chrome.driver";
	public final String projectPath = System.getProperty("user.dir");
	public final String chromePath = projectPath + "\\src\\test\\resources\\Driver\\chromedriver.exe";
	public final String propertiesFolder = projectPath + "\\src\\test\\resources\\Opencart.properties";

	public final String folderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Opencart.properties";
	public final String chromePathr = "C:\\Users\\SKRAT\\eclipse-workspace\\EBFSMavenProject\\src\\test\\resources\\Driver\\chromedriver.exe";

}
