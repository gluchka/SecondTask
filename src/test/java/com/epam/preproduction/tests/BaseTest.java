package com.epam.preproduction.tests;


import com.epam.preproduction.googlemail.core.DriverManager;
import com.epam.preproduction.googlemail.helpers.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class BaseTest {

        public WebDriver driver;


        @BeforeTest
        public void beforeTest() {
            driver = DriverManager.chromeDriver();
            driver.manage().window().maximize();
            driver.get(Constants.GET_URL);

        }


        @AfterTest
        public void endTest() {
            if (driver != null)
                driver.quit();
        }

}
