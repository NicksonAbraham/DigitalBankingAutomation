package com.bank.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.bank.qa.reports.ExtentTestListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.bank.qa.tests.LoginTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        LoginTest test =
            (LoginTest) result.getInstance();

        WebDriver driver = test.getDriver();

        if (driver != null) {

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            try {
                Path folder = Paths.get("screenshots");

                Files.createDirectories(folder);

                String fileName = result.getName()
                        + "_" + System.currentTimeMillis() + ".png";

                Path destination = folder.resolve(fileName);

                Files.copy(source.toPath(), destination);
                String screenshotPath =
                        destination.toAbsolutePath().toString();

                if (ExtentTestListener.getTest() != null) {
                    ExtentTestListener.getTest()
                            .addScreenCaptureFromPath(screenshotPath);
                }
                

                System.out.println(
                    "Screenshot saved: " + destination
                );

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
