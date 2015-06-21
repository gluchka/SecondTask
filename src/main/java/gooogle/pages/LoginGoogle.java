package gooogle.pages;

import components.BasePage;
import components.DriverManager;
import helpers.ClickerHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginGoogle extends BasePage {


    private final String LOGIN_GOOGLE = "//input[@id='Email']";
    private final String LOGIN_GOOGLE_BUTTON = "//input[@id='next']";
    private final String PASSWORD_GOOGLE = "//input[@id='Passwd']";
    private final String PASS_BUTTON_GOOGLE = "//input[@id='signIn']";


    private ClickerHelper clickerHelper;

    public LoginGoogle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = LOGIN_GOOGLE)
    private WebElement loginGoogleTextField;

    @FindBy(xpath = LOGIN_GOOGLE_BUTTON)
    private WebElement loginButtonGoogle;

    @FindBy(xpath = PASSWORD_GOOGLE)
    private WebElement passGoogleTextField;

    @FindBy(xpath = PASS_BUTTON_GOOGLE)
    private WebElement passButtonGoogle;

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

    public PersAccountGoogle clickSignInButton() {
        ClickerHelper.clickOnElement(driver, passButtonGoogle);
        return new PersAccountGoogle(driver);
    }


    public PersAccountGoogle loginToAccount(String login, String password) {
        enterLogin(login)
                .clickLoginButton()
                .enterPassword(password)
                .clickSignInButton();
        return new PersAccountGoogle(driver);
    }

}
