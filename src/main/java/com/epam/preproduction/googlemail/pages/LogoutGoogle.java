package com.epam.preproduction.googlemail.pages;

import com.epam.preproduction.googlemail.helpers.ClickerHelper;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class LogoutGoogle extends BasePage {

    LoginGoogle loginGoogle = new LoginGoogle(driver);

    public LogoutGoogle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final String CLICK_TO_LOGOUT = "//a[contains(@href, 'SignOutOptions')]";
    private final String LOGOUT = "//a[contains(@href, 'logout')]";

    private final static String ADD_ACCOUNT = "//a[contains(@href,'https://accounts.google.com/AddSession')]";
    private final static String ANOTHER_ACCOUNT = "//div[2]/div[2]/a[2]/div/div[1]";

    @FindBy(xpath = ADD_ACCOUNT)
    protected WebElement addAccount;

    @FindBy(xpath = ANOTHER_ACCOUNT)
    protected WebElement anotherAccount;


    @FindBy(xpath = CLICK_TO_LOGOUT)
    protected WebElement clickToLogoutGoogle;

    @FindBy(xpath = LOGOUT)
    protected WebElement logoutGoogle;

    public LogoutGoogle clickLogoutFromGoogle() {
        ClickerHelper.clickOnElement(driver, clickToLogoutGoogle);
        return this;
    }

    public void logoutGoogle() {
        ClickerHelper.clickOnElement(driver, logoutGoogle);
    }

    public PersonalAccountGoogle verifySecondAccount(String login, String password) {
        ClickerHelper.clickOnElement(driver, clickToLogoutGoogle);
        int i =2;
        try {
            ClickerHelper.clickOnElement(driver, anotherAccount);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(i));
            i++;
            return  new PersonalAccountGoogle(driver);
        } catch (NoSuchElementException | ElementNotVisibleException e) {
            ClickerHelper.clickOnElement(driver, addAccount);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            new LoginGoogle(driver).loginToAccount(login, password);
        }
        return new PersonalAccountGoogle(driver);
    }

}


