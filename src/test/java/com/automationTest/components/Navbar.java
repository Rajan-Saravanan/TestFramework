package com.automationTest.components;

import com.automationTest.pageObjects.pageUtils.Highlighter;
import org.openqa.selenium.WebDriver;

/**
 * This is sample reusable UI Component
 */
public class Navbar {
    private WebDriver driver;

    public Navbar(WebDriver driver){
        this.driver = driver;
        Highlighter highlighter = new Highlighter(driver);
        highlighter.initElements(this);
    }
}
