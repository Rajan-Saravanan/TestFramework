package com.automationTest.tests;

import com.automationTest.components.Navbar;
import com.automationTest.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends TestConfig {

    WebDriver driver;

    @Test
    public void login(){
        System.out.println("Login Test");
        Navbar navbar = new Navbar(driver);
        navbar.selectSearchCategoryDropdown("Baby");
        List<String> options = navbar.getSearchDropdownCategory();
        System.out.println(options.toString());
    }

    @Override
    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Override
    @BeforeTest
    public void setup() {
        driver = intializeDriver();
    }
}
