package com.bank.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/ExtentReport.html");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "Digital Banking Automation");
            extent.setSystemInfo("Tester", "QA Engineer");
            extent.setSystemInfo("Framework", "Selenium + TestNG");

        }

        return extent;
    }
}