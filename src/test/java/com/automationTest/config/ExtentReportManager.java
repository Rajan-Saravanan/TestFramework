package com.automationTest.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static void setWebDriver(WebDriver driver) {
        ExtentReportManager.driver.set(driver);
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void endTest() {
        getInstance().flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // TestNG onStart() method
    }

    @Override
    public void onTestStart(ITestResult result) {
        startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Take a screenshot on test failure
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            String screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BASE64);
            getTest().addScreenCaptureFromBase64String(screenshot);
            getTest().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot, "Failure Screenshot").build());
        }else
            getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        endTest();
    }

    public static void logInfo(String message) {
        LogManager.getLogger("ExtentTestLogger").info(message);
        getTest().info(message);
    }

    public static void logPass(String message) {
        LogManager.getLogger("ExtentTestLogger").info("PASS: " + message);
        getTest().pass(message);
    }

    public static void logFail(String message) {
        LogManager.getLogger("ExtentTestLogger").error("FAIL: " + message);
        getTest().fail(message);
    }

    public static void logWarning(String message) {
        LogManager.getLogger("ExtentTestLogger").warn("WARNING: " + message);
        getTest().warning(message);
    }

    public static void logError(String message) {
        LogManager.getLogger("ExtentTestLogger").error("ERROR: " + message);
        getTest().fail(message);
    }

    public static void logException(Throwable throwable) {
        LogManager.getLogger("ExtentTestLogger").error("EXCEPTION: " + throwable.getMessage());
        getTest().fail(throwable);
    }
}



