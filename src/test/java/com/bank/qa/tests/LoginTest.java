package com.bank.qa.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.bank.qa.listeners.TestListener;
import com.bank.qa.reports.ExtentTestListener;

import com.bank.qa.pages.LoginPage;

@Listeners({TestListener.class,
	ExtentTestListener.class})
public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        // GitHub Actions Linux configuration
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        String filePath = System.getProperty("user.home")
                + "/Downloads/practice_digital_banking_login-2.html";

        driver.get(new File(filePath).toURI().toString());

        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {

        loginPage.enterUsername("testuser");

        loginPage.enterPassword("Password123");

        loginPage.clickLogin();

        String actualTitle = driver.getTitle();

        System.out.println("Page title: " + actualTitle);

        Assert.assertTrue(
            driver.getPageSource().contains("Welcome to your dashboard"),
            "Login was unsuccessful"
        );
    }
    
    @Test
    public void invalidLoginTest() {

        loginPage.enterUsername("testuser");

        loginPage.enterPassword("WrongPassword123");

        loginPage.clickLogin();

        String actualMessage =
                loginPage.getErrorMessage();

        Assert.assertEquals(
            actualMessage,
            "Invalid username or password. Please try again.",
            "Error message validation failed"
        );
    }
    
    @DataProvider(name="loginData")
    public Object[][] getLoginData(){
    	return new Object[][] {
    		{"testuser","Test@123", true},
    		{"testuser","wrongPassword", false},
    		{"wronguser", "Test@123", false},
    		{"","", false}
    	};
    }
    
    @Test(dataProvider = "loginData")
    public void loginWithMultipleData(
            String username,
            String password,
            boolean expectedResult) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedResult) {
            Assert.assertTrue(
                driver.getPageSource().contains("Welcome to your dashboard")
            );
        } else {
            Assert.assertTrue(
                loginPage.getErrorMessage().contains("Invalid username")
            );
        }
    }
    @Test(enabled= false)
    public void intentionalFailureTest() {
    	Assert.fail("Testing automatic screeenshot capture");
    }
    public WebDriver getDriver() {
    	return driver;
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}