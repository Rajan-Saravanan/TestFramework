package com.automationTest.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;
import java.util.Properties;

public abstract class TestConfig {
    public WebDriver driver;

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

    public WebDriver intializeDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        //Clear browser cookies
        this.driver.manage().deleteAllCookies();
        //Maximize windows
        this.driver.manage().window().maximize();
        //set up implicit wait
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("IMPLICIT_WAIT"))));
        // Navigate to app
        this.driver.get(prop.getProperty("APP_URL"));

        return this.driver;
    }

    public abstract void setup();
    public abstract void tearDown();
}
