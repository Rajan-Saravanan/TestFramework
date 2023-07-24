package com.automationTest.tests.Login;

import com.automationTest.config.ExtentReportManager;
import com.automationTest.config.JsonDataProviderUtil;
import com.automationTest.config.TestConfig;
import com.automationTest.pageObjects.LoginPage;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_LF_004 extends TestConfig {
    WebDriver driver;
    @Override
    @BeforeMethod
    public void setup() {
        driver = initializeDriver();
    }

    @Override
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "invalidLogin")
    public Object[][] getTestData(){
        return JsonDataProviderUtil.getTestData("login.json", "invalidLogin");
    }

    @Test(dataProvider = "invalidLogin")
    public void verify_invalid_login(JsonObject data){
        ExtentReportManager.logInfo("Test Data Used : "  + data.toString());
        LoginPage login = new LoginPage(driver);

        //Enter Invalid User Name
        login.enterUserName(data.get("userName").getAsString());
        ExtentReportManager.logPass("Step 1 : Invalid Username typed into Username input field");

        //Enter Invalid Password
        login.enterPassword(data.get("password").getAsString());
        ExtentReportManager.logPass("Step 2 : Invalid Password typed in Password input field");

        //Click on Login button
        login.clickOnLoginButton();
        ExtentReportManager.logPass("Step 3 : Clicked on Login button after entering Invalid User name and Password");

        //Verify Error message is displayed for Invalid Credentials
        Assert.assertTrue(login.invalidCredentialsError.isDisplayed(), "Invalid Credentials Error message not dispalyed for Invalid user name and Password");
    }
}
