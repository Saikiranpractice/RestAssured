package api.utilities;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter spakrkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0));//time stamp
		repName="Test-Report-"+timeStamp+".html";
		
		spakrkReporter=new ExtentSparkReporter(".\\reporter\\"+repName);//specify location of the report
		spakrkReporter.config().setDocumentTitle("Employee API");
		spakrkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(spakrkReporter);
		extent.setSystemInfo("Application","Employee API");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Sai");
		

	}
	
	public void onTestSuccess(ITestResult tr)
	{
		test=extent.createTest(tr.getName());
		test.assignCategory(tr.getMethod().getGroups());
		test.createNode(tr.getName());
		test.log(Status.PASS, "Test Pass");
	}
	
	public void onTestFailure(ITestResult tr)
	{
		test=extent.createTest(tr.getName());
		test.assignCategory(tr.getMethod().getGroups());
		test.createNode(tr.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, tr.getThrowable().getMessage());
	}
	
	
	public void onTestSkipped(ITestResult tr)
	{
		test=extent.createTest(tr.getName());
		test.assignCategory(tr.getMethod().getGroups());
		test.createNode(tr.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, tr.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
