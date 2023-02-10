package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestListeners implements ITestListener {
	ExtentSparkReporter sparkRepoter;
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());//combination of above 3 line
		String reportname="Test Report-"+dateFormat+".html";
        
		sparkRepoter = new ExtentSparkReporter(".\\ExtentReports\\"+reportname);

		sparkRepoter.config().setDocumentTitle("Automation Test");
		sparkRepoter.config().setReportName("Amazon Automation Test");
		sparkRepoter.config().setTimeStampFormat("yyyy:mm:dd hh:mm:ss");
		sparkRepoter.config().setTheme(Theme.DARK);

	    extentReport = new ExtentReports();
		extentReport.attachReporter(sparkRepoter);
		
		extentReport.setSystemInfo("Application","Express");
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Email", "abc@yahoo.com");
		
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getTestClass().getName() + ":" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		extentTest.log(Status.INFO, result.getMethod().getMethodName() + " got successfully executed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test case failed " + result.getName());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
		String imagePath=new TestBase().takeScreenshot(result.getName());
		extentTest.addScreenCaptureFromPath(imagePath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName() + "Test got skipped ");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
	}


	@Override
	public void onFinish(ITestContext context) {
		if (extentReport != null) {
			extentReport.flush();
		}
	}


}
