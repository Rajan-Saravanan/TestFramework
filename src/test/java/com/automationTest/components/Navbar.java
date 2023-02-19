package com.automationTest.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Navbar {
    private WebDriver driver;

    @FindBy(xpath = "//a[@aria-label='Amazon.in']")
    private WebElement amazonLogo;

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    private WebElement searchCategoryDropdown;

    public Navbar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnAmazonLogo(){
        amazonLogo.click();
    }

    public void selectSearchCategoryDropdown(String visibleText){
        Select select = new Select(searchCategoryDropdown);
        select.selectByVisibleText(visibleText);
    }

    public List<String> getSearchDropdownCategory(){
        Select select = new Select(searchCategoryDropdown);
//        return select.getOptions().stream().map(ele -> ele.getText()).collect(Collectors.toList());
        List<String> optionsValue = new ArrayList<>();
        for(WebElement element : select.getOptions()){
            optionsValue.add(element.getText());
        }
        return optionsValue;
    }
}
