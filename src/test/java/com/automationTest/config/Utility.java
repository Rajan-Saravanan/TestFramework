package com.automationTest.config;

import org.openqa.selenium.WebElement;

import java.time.Duration;

public abstract class Utility extends TestConfig {

    public boolean isElementDisplayed(WebElement element) {
        try {
            disableImplicitWait();
            if (element.isDisplayed()) return true;
        } catch (Exception e) {
            return false;
        } finally {
            setImplicitWait();
        }
        return false;
    }

    public void staticWaitFor(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
