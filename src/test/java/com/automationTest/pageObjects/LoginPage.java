package com.automationTest.pageObjects;

import com.automationTest.config.ExtentReportManager;
import com.automationTest.pageObjects.pageUtils.Highlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    private WebDriver driver;

    @FindBy(name = "username")
    public WebElement userName;

    @FindBy(css = "input[name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[text()=' Login ']")
    public WebElement loginButton;

    @FindBy(xpath = "normalize-space(//p[text()=' Forgot your password?'])")
    public WebElement forgotPassword;

    @FindBy(css = "div.orangehrm-login-footer-sm > a:nth-child(1)")
    public WebElement linkedIn;

    @FindBy(css = "div.orangehrm-login-footer-sm > a:nth-child(2)")
    public WebElement facebook;

    @FindBy(css = "div.orangehrm-login-footer-sm > a:nth-child(3)")
    public WebElement twitter;

    @FindBy(css = "div.orangehrm-login-footer-sm > a:nth-child(4)")
    public WebElement youtube;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text') and text()='Invalid credentials']")
    public WebElement invalidCredentialsError;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        Highlighter highlighter = new Highlighter(driver);
        highlighter.initElements(this);
    }

    public void enterUserName(String userName) {
        this.userName.sendKeys(userName);
        ExtentReportManager.logInfo("User Name entered " + userName);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        ExtentReportManager.logInfo("Password entered " + password);
    }

    public Dashboard clickOnLoginButton() {
        loginButton.click();
        return new Dashboard(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickOnForgotPassword() {
        forgotPassword.click();
    }

    public void clickOnLinkedIn() {
        linkedIn.click();
    }

    public void clickOnFacebook() {
        facebook.click();
    }

    public void clickOnTwitter() {
        twitter.click();
    }

    public void clickOnYoutube() {
        youtube.click();
    }
}
