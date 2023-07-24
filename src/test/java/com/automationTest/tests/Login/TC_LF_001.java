package com.automationTest.tests.Login;

import com.automationTest.config.Utility;
import com.automationTest.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TC_LF_001 extends Utility {

    WebDriver driver;
    @Test()
    public void verify_UI_elements_in_Login_screen(){


        LoginPage login = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();

        //Verify UserName, Password and Login button is available
        Assert.assertTrue(login.userName.isDisplayed());
        Assert.assertTrue(login.passwordInput.isDisplayed());
        Assert.assertTrue(login.loginButton.isDisplayed());

        //Verify Footer social icons are displayed
        softAssert.assertTrue(login.linkedIn.isDisplayed());
        softAssert.assertTrue(login.facebook.isDisplayed());
        softAssert.assertTrue(login.twitter.isDisplayed());
        softAssert.assertTrue(login.youtube.isDisplayed());

    }

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
}
