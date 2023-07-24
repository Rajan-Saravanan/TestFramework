package com.automationTest.pageObjects.pageUtils;

import com.automationTest.config.ExtentReportManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Highlighter {
    private WebDriver driver;

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

    public Highlighter(WebDriver driver) {
        this.driver = driver;
    }

    public void initElements(Object page) {
        PageFactory.initElements(new HighlightFieldDecorator(driver), page);
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

    public class HighlightFieldDecorator extends org.openqa.selenium.support.pagefactory.DefaultFieldDecorator {
        private WebDriver driver;

        public HighlightFieldDecorator(WebDriver driver) {
            super(new org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory(driver));
            this.driver = driver;
        }

        @Override
        public Object decorate(ClassLoader loader, java.lang.reflect.Field field) {
            Object fieldInstance = super.decorate(loader, field);
            if (fieldInstance instanceof WebElement) {
                return new HighlightWebElement(driver, (WebElement) fieldInstance);
            }
            return fieldInstance;
        }
    }

    public class HighlightWebElement implements WebElement {
        private WebDriver driver;
        private WebElement element;


        public HighlightWebElement(WebDriver driver, WebElement element) {
            this.driver = driver;
            this.element = element;
        }

        private void highlight() {
            String originalStyle = element.getAttribute("style");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'background-color: #ffff00ba;');", element);
            interactionDelay();
            js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
        }

        // Implement the WebElement interface methods here
        // You can simply delegate these methods to the actual element.

        @Override
        public void click() {
            highlight();
            ExtentReportManager.logInfo("Performing click on element " + getLocatorFromWebElement(element));
            element.click();
        }

        @Override
        public void submit() {
            highlight();
            ExtentReportManager.logInfo("Performing Submit on element " + getLocatorFromWebElement(element));
            element.submit();
        }

        @Override
        public void sendKeys(CharSequence... keysToSend) {
            highlight();
            ExtentReportManager.logInfo("Entering Text on element " + getLocatorFromWebElement(element));
            element.sendKeys(keysToSend);
        }

        @Override
        public void clear() {
            highlight();
            ExtentReportManager.logInfo("Clearing Text on element " + getLocatorFromWebElement(element));
            element.clear();
        }

        @Override
        public String getTagName() {
            highlight();
            ExtentReportManager.logInfo("Getting Tag Name on element " + getLocatorFromWebElement(element));
            return element.getTagName();
        }

        @Override
        public String getAttribute(String name) {
            highlight();
            ExtentReportManager.logInfo(String.format("Getting '%s' Attribute value on element %s", name, getLocatorFromWebElement(element)));
            return element.getAttribute(name);
        }

        @Override
        public boolean isSelected() {
            highlight();
            ExtentReportManager.logInfo(String.format("Verifying element '%s' is selected", getLocatorFromWebElement(element)));
            return element.isSelected();
        }

        @Override
        public boolean isEnabled() {
            highlight();
            ExtentReportManager.logInfo(String.format("Verifying element '%s' is enabled", getLocatorFromWebElement(element)));
            return element.isEnabled();
        }

        @Override
        public String getText() {
            highlight();
            ExtentReportManager.logInfo("Getting text of element : " + getLocatorFromWebElement(element));
            return element.getText();
        }


        @Override
        public List<WebElement> findElements(By by) {
            highlight();
            return element.findElements(by);
        }

        @Override
        public WebElement findElement(By by) {
            highlight();
            return element.findElement(by);
        }

        @Override
        public boolean isDisplayed() {
            highlight();
            ExtentReportManager.logInfo("Getting element is displayed : " + getLocatorFromWebElement(element));
            return element.isDisplayed();
        }

        @Override
        public Point getLocation() {
            highlight();
            ExtentReportManager.logInfo("Getting location of element : " + getLocatorFromWebElement(element));
            return element.getLocation();
        }

        @Override
        public Dimension getSize() {
            highlight();
            ExtentReportManager.logInfo("Getting dimension of element : " + getLocatorFromWebElement(element));
            return element.getSize();
        }

        @Override
        public Rectangle getRect() {
            highlight();
            ExtentReportManager.logInfo("Getting Rectangle of element : " + getLocatorFromWebElement(element));
            return element.getRect();
        }

        @Override
        public String getCssValue(String propertyName) {
            highlight();
            ExtentReportManager.logInfo("Getting CSS property of element : " + getLocatorFromWebElement(element) + " and propery value looking for is " + propertyName);
            return element.getCssValue(propertyName);
        }


        @Override
        public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
            return ((TakesScreenshot) driver).getScreenshotAs(target);
        }
    }
}

