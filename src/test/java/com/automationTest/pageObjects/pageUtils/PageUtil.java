package com.automationTest.pageObjects.pageUtils;

import com.automationTest.config.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PageUtil {

    WebDriver driver;
    Actions actions;

    public PageUtil(WebDriver driver){
        this.driver = driver;
        actions = new Actions(this.driver);
    }

    public static Properties prop;

    static {
        prop = new Properties();
        try{
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Config.properties");
            prop.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getLocatorFromWebElement(WebElement element) {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }

    public static void interactionDelay(){
        try{
            long seconds = Integer.parseInt(prop.getProperty("INTERACTION_DELAY")) * 1000;
            Thread.sleep(seconds);
        }catch (Exception e){
            ExtentReportManager.logError("Failed to set Interaction delay. Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actionsClick(WebElement element){
        ExtentReportManager.logInfo("Performing Actions click on element " + getLocatorFromWebElement(element));
        interactionDelay();
        actions.moveToElement(element).click().perform();
    }

    public void switchToNewWindow(String currentWindow){
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!window.equals(currentWindow))
                    driver.switchTo().window(window);
        }
    }
}
