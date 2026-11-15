package com.bank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators
    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.id("loginBtn");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    // Enter password
    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    // Click login
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    
    //Get Error message
    public String getErrorMessage() {
    	return driver.findElement(By.id("message")).getText();
    }
}