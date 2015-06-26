package com.epam.preproduction.googlemail.pages;

import com.epam.preproduction.googlemail.helpers.ClickerHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginGoogle extends BasePage {


    private final String LOGIN_GOOGLE = "//input[@id='Email']";
    private final String LOGIN_GOOGLE_BUTTON = "//input[@id='next']";
    private final String PASSWORD_GOOGLE = "//input[@id='Passwd']";
    private final String PASS_BUTTON_GOOGLE = "//input[@id='signIn']";

    public LoginGoogle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = LOGIN_GOOGLE)
    protected WebElement loginGoogleTextField;

    @FindBy(xpath = LOGIN_GOOGLE_BUTTON)
    protected WebElement loginButtonGoogle;

    @FindBy(xpath = PASSWORD_GOOGLE)
    protected WebElement passGoogleTextField;

    @FindBy(xpath = PASS_BUTTON_GOOGLE)
    protected WebElement passButtonGoogle;

    public LoginGoogle enterLogin(String login) {
        loginGoogleTextField.sendKeys(login);
        return this;
    }

    public LoginGoogle clickLoginButton() {
        ClickerHelper.clickOnElement(driver, loginButtonGoogle);
        return this;
    }

    public LoginGoogle enterPassword(String password) {
        passGoogleTextField.sendKeys(password);
        return this;
    }

    public PersonalAccountGoogle clickSignInButton() {
        ClickerHelper.clickOnElement(driver, passButtonGoogle);
        return new PersonalAccountGoogle(driver);
    }


    public PersonalAccountGoogle loginToAccount(String login, String password) {
        enterLogin(login)
                .clickLoginButton()
                .enterPassword(password)
                .clickSignInButton();
        return new PersonalAccountGoogle(driver);
    }

}
