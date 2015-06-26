package com.epam.preproduction.googlemail.pages;



import com.epam.preproduction.googlemail.helpers.ClickerHelper;
import com.epam.preproduction.googlemail.helpers.Constants;
import com.epam.preproduction.googlemail.helpers.WaiterHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.util.List;
import java.util.Random;

import static com.epam.preproduction.googlemail.helpers.WaiterHelper.delay;

public class PersonalAccountGoogle extends BasePage {

    public PersonalAccountGoogle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    LogoutGoogle logoutGoogle = new LogoutGoogle(driver);

    private final static String NEW_LETTER_BUTTON = "//div[@class='z0']/div";
    private final static String GOTO_DRAFTS = "//a[contains(@href, '#drafts')]";
    private final static String SETTINGS = "//div[contains(@role,'button') and (@title='Настройки')]";
    private final static String SETTINGS_THEME = "//div[@id and @role='menu']//div[9]/div";
    private final static String ALL_THEMES = "//tr[2]//div[@id]/div/div/div/div[@id]";
    private final static String ALERT_CHANGE_THEME = "//div[@aria-live='assertive' and @role='alert']/div/div[2]/span[1]";
    private final static String FIRST_LETTER_CHECKBOX = "//div[@role='tabpanel']//table//tbody/tr[1]/td[2]/div/div";
    private final static String SPAM_TOP_BUTTON = "//div[@act and @role='button'][2]/div/div";
    private final static String MORE_MENU_ITEMS = "//div[@role='navigation']//span[@role='button']/span[1]";

    private final static String SPAM_BUTTON = "//a[contains(@href, 'spam')]";
    private final static String FIRST_SPAM_LETTER = "//div[@role='main']/div//tbody/tr[1]/td[4]";
    private final static String FIRST_LETTER_THEME = "//table[@role='presentation']/tr/td//h2";

    private final static String NAVIGATE_TO_STARRET = "//div[@role='navigation']/div/div/div/div/div[2]//div/span/a";
    private final static String FIRST_STARRED_LETTER = "//div[@id]/div/div//div[@role='tabpanel']/div//tbody/tr[1]/td[6]/div";

    private final static String FIRST_SINBOX_LETTER = "//tbody//tr[@id][1]/td[6]/div";

    private final static String ATTACHED_TO_LETTER_FILE = "//div[@style='display:']//div[@id]/span/a[@id]//div[1]/span[@id]";

    @FindBy(xpath = NAVIGATE_TO_STARRET)
    protected WebElement navigateToStarret;

    @FindBy(xpath = FIRST_STARRED_LETTER)
    protected WebElement firstStarredLetter;

    @FindBy(xpath = ATTACHED_TO_LETTER_FILE)
    protected WebElement attachedToLetterFile;

    @FindBy(xpath = FIRST_SINBOX_LETTER)
    protected WebElement firstInboxLetter;

    @FindBy(xpath = NEW_LETTER_BUTTON)
    protected WebElement writeLetterButtonGoogle;

    @FindBy(xpath = GOTO_DRAFTS)
    protected WebElement gotoDraftButtonGoogle;

    @FindBy(xpath = SETTINGS)
    protected WebElement settingsButton;

    @FindBy(xpath = SETTINGS_THEME)
    protected WebElement settingsTheme;

    @FindBy(xpath = ALL_THEMES)
    protected List<WebElement> allThemes;

    @FindBy(xpath = FIRST_LETTER_CHECKBOX)
    protected WebElement firstLetterCheckBox;

    @FindBy(xpath = SPAM_TOP_BUTTON)
    protected WebElement spamTopButton;

    @FindBy(xpath = ALERT_CHANGE_THEME)
    protected WebElement alertMessage;

    @FindBy(xpath = MORE_MENU_ITEMS)
    protected WebElement moreMenuItem;

    @FindBy(xpath = SPAM_BUTTON)
    protected WebElement spamButton;

    @FindBy(xpath = FIRST_SPAM_LETTER)
    protected WebElement firstSpamLetter;

    @FindBy(xpath = FIRST_LETTER_THEME)
    protected WebElement firstLetterTheme;

    public WriteLetterGoogle writeLetterClick() {
        ClickerHelper.clickOnElement(driver, writeLetterButtonGoogle);
        return new WriteLetterGoogle(driver);
    }

    public PersonalAccountGoogle settings() {
        ClickerHelper.clickOnElement(driver, settingsButton);
        return this;
    }

    public PersonalAccountGoogle settingsTheme() {
        ClickerHelper.clickOnElement(driver, settingsTheme);
        return this;
    }

    public PersonalAccountGoogle selectRandomTheme() {
        ClickerHelper.clickOnElement(driver, allThemes.get(new Random().nextInt(allThemes.size())));
        return this;
    }

    public PersonalAccountGoogle selectAndVerifyRandomTheme() {
        settings().settingsTheme().selectRandomTheme();
        Assert.assertTrue(WaiterHelper.isElementVisible(driver, ALERT_CHANGE_THEME));
        return this;
    }

    public LoginGoogle loginToAnotherUser(String login, String password) {
        logoutGoogle.verifySecondAccount(login, password);
        return new LoginGoogle(driver);
    }

    public LoginGoogle logoutEnd() {
        logoutGoogle.clickLogoutFromGoogle().logoutGoogle();
        return new LoginGoogle(driver);
    }

    public PersonalAccountGoogle moveLetterToSpam() {
        ClickerHelper.clickOnElement(driver, firstLetterCheckBox);
        ClickerHelper.clickOnElement(driver, spamTopButton);
        Assert.assertTrue(alertMessage.getText().equals("Цепочка отмечена как спам."));
        return this;
    }

    public PersonalAccountGoogle goToSpam() {
        ClickerHelper.clickOnElement(driver, moreMenuItem);
        ClickerHelper.clickOnElement(driver, spamButton);
        ClickerHelper.clickOnElement(driver, firstSpamLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_FIRST_SUBTASK));
        return this;
    }

    public PersonalAccountGoogle dragAndDrop() {

        Actions actions = new Actions(driver);
        actions.dragAndDrop(firstLetterCheckBox, navigateToStarret).release().perform();
        delay(Constants.SEC_DELAY + Constants.SEC_DELAY);
        return this;
    }

    public PersonalAccountGoogle goToStarredTabVerifyLetter() {
        ClickerHelper.clickOnElement(driver, navigateToStarret);
        ClickerHelper.clickOnElement(driver, firstStarredLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_SECOND_SUBTASK));
        return this;
    }

    public PersonalAccountGoogle goToInboxTabVerifyLetter() {
        ClickerHelper.clickOnElement(driver, firstInboxLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_THIRD_SUBTASK));
        Assert.assertTrue(attachedToLetterFile.getText().equals(Constants.FILE_NAME));
        return this;
    }

}
