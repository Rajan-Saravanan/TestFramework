package com.automationTest.tests.Login;

import com.automationTest.config.JsonDataProviderUtil;
import com.automationTest.config.Utility;
import com.automationTest.pageObjects.LoginPage;
import com.automationTest.pageObjects.pageUtils.PageUtil;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TC_LF_002 extends Utility {
    WebDriver driver;

    @Test(dataProvider = "testData")
    public void verify_social_links_are_working_in_login_screen(JsonObject testData){
        LoginPage login = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();

        //Verify LinkinIn is redirected
        softAssert.assertEquals(clickOnSocialIconAndGetTitle(login.linkedIn, login), testData.get("linkedIn").getAsString(), "LinkedIn icon was not redirected properly");

        //Verify Facebook is redirected
        softAssert.assertEquals(clickOnSocialIconAndGetTitle(login.facebook, login), testData.get("facebook").getAsString(), "Facebook icon was not redirected properly");

        //Verify Twitter is redirected
        softAssert.assertEquals(clickOnSocialIconAndGetTitle(login.twitter, login), testData.get("twitter").getAsString(), "Twitter icon not redirected properly");

        //Verify Youtube is redirected
        softAssert.assertEquals(clickOnSocialIconAndGetTitle(login.youtube,login), testData.get("youtube").getAsString(), "Youtube icon is not redirected properly");

        softAssert.assertAll();
    }

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return JsonDataProviderUtil.getTestData("login.json", "socialLinkTitle");
    }

    public String clickOnSocialIconAndGetTitle(WebElement socialIcon, LoginPage page){
        socialIcon.click();
        PageUtil pageUtil = new PageUtil(driver);
        String parenWindow = driver.getWindowHandle();
        pageUtil.switchToNewWindow(parenWindow);
        staticWaitFor(3);
        String title = page.getTitle();
        driver.close();
        driver.switchTo().window(parenWindow);
        return title;
    }
    @Override
    @BeforeMethod
    public void setup() {
        this.driver = initializeDriver();
    }

    @Override
    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
