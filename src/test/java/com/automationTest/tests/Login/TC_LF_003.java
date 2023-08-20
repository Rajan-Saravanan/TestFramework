package com.automationTest.tests.Login;

import com.automationTest.config.ExtentReportManager;
import com.automationTest.config.JsonDataProviderUtil;
import com.automationTest.config.Utility;
import com.automationTest.pageObjects.Dashboard;
import com.automationTest.pageObjects.LoginPage;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_LF_003 extends Utility {

    @DataProvider(name = "loginData")
    public Object[][] getTestData() {
        return JsonDataProviderUtil.getTestData("login.json", "validLogin");
    }

    @Test(dataProvider = "loginData")
    public void perform_valid_login(JsonObject loginData){
        ExtentReportManager.logInfo("Data Used : " + loginData.toString());
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard;

        //Enter userName
        loginPage.enterUserName(loginData.get("userName").getAsString());

        //Enter Password
        loginPage.enterPassword(loginData.get("password").getAsString());

        //Click on Login button
        dashboard = loginPage.clickOnLoginButton();

        Assert.assertEquals(dashboard.getPageName(), "Dashboard", "Dashboard page is not displayed after login");

    }

}
