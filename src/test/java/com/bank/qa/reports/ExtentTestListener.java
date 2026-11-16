package com.bank.qa.reports;

import org.testng.ITestListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.bank.qa.tests.LoginTest;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener implements ITestListener {

    ExtentReports extent =
            ExtentReportManager.getReportInstance();

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {

            LoginTest loginTest =
                    (LoginTest) result.getInstance();

            WebDriver driver = loginTest.getDriver();

            if (driver != null) {

                TakesScreenshot screenshot =
                        (TakesScreenshot) driver;

                File source =
                        screenshot.getScreenshotAs(OutputType.FILE);

                Files.createDirectories(Paths.get("screenshots"));

                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                String fileName =
                        result.getMethod().getMethodName()
                        + "_" + timestamp + ".png";

                Path destination =
                        Paths.get("screenshots", fileName);

                Files.copy(source.toPath(), destination,
                        StandardCopyOption.REPLACE_EXISTING);

                test.get().addScreenCaptureFromPath(
                        "../screenshots/" + fileName);

                System.out.println(
                        "Failure screenshot saved: " + destination);

            }

        } catch (Exception e) {

            test.get().warning(
                    "Unable to capture failure screenshot: "
                    + e.getMessage());

        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");

    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        extent.flush();
        

    }
    
    public static ExtentTest getTest() {
    	return test.get();
    }
}
