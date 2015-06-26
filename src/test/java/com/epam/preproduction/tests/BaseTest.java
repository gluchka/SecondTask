package com.epam.preproduction.tests;


import com.epam.preproduction.googlemail.core.DriverManager;
import com.epam.preproduction.googlemail.core.PropertyFactory;
import com.epam.preproduction.googlemail.helpers.*;
import com.epam.preproduction.googlemail.pages.LoginGoogle;
import com.epam.preproduction.googlemail.pages.LogoutGoogle;
import com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class BaseTest {

    public WebDriver driver;
    protected static PropertyFactory propertyFactory = new PropertyFactory();
    public static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected LoginGoogle loginGoogle;
    protected PersonalAccountGoogle personalAccountGoogle;
    protected LogoutGoogle logoutGoogle;


    @BeforeTest
    public void beforeTest() {
        LOGGER.info("Initialize browser");
        driver = DriverManager.chromeDriver();
        driver.manage().window().maximize();
        driver.get(propertyFactory.getProperty("url"));

    }


    @AfterTest
    public void endTest() {
        if (driver != null)
            driver.quit();
    }

}
