package com.automationTest.pageObjects;

import com.automationTest.pageObjects.pageUtils.Highlighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {

    WebDriver driver;

    @FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']/h6[text()='Dashboard']")
    WebElement pageName;

    public Dashboard(WebDriver driver){
        this.driver = driver;
        Highlighter highlighter = new Highlighter(driver);
        highlighter.initElements(this);
    }

    public String getPageName(){
        return pageName.getText();
    }
}
