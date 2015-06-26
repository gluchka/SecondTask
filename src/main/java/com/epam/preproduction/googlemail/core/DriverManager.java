package com.epam.preproduction.googlemail.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class DriverManager {

    protected static PropertyFactory property = new PropertyFactory();

        public static WebDriver chromeDriver()
        {
            System.setProperty("webdriver.chrome.driver", property.getProperty("chrome.driver"));
            return new ChromeDriver();
        }
        public static WebDriver operaDriver()
        {
            System.setProperty("webdriver.opera.driver", property.getProperty("opera.driver"));
            return new OperaDriver();
        }
        public static WebDriver ieDriver()
        {
            System.setProperty("webdriver.ie.driver", property.getProperty("ie.driver"));
            return new InternetExplorerDriver();
        }
        public static WebDriver firefoxDriver()
        {
            return new FirefoxDriver();
        }



}
