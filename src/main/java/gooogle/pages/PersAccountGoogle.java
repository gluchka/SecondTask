package gooogle.pages;

import components.BasePage;
import helpers.ClickerHelper;
import helpers.Constants;
import helpers.WaiterHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static helpers.WaiterHelper.delay;

public class PersAccountGoogle extends BasePage {

    public PersAccountGoogle(WebDriver driver) {
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
    private WebElement navigateToStarret;

    @FindBy(xpath = FIRST_STARRED_LETTER)
    private WebElement firstStarredLetter;

    @FindBy(xpath = ATTACHED_TO_LETTER_FILE)
    private WebElement attachedToLetterFile;

    @FindBy(xpath = FIRST_SINBOX_LETTER)
    private WebElement firstInboxLetter;

    @FindBy(xpath = NEW_LETTER_BUTTON)
    private WebElement writeLetterButtonGoogle;

    @FindBy(xpath = GOTO_DRAFTS)
    private WebElement gotoDraftButtonGoogle;

    @FindBy(xpath = SETTINGS)
    private WebElement settingsButton;

    @FindBy(xpath = SETTINGS_THEME)
    private WebElement settingsTheme;

    @FindBy(xpath = ALL_THEMES)
    private List<WebElement> allThemes;

    @FindBy(xpath = FIRST_LETTER_CHECKBOX)
    private WebElement firstLetterCheckBox;

    @FindBy(xpath = SPAM_TOP_BUTTON)
    private WebElement spamTopButton;

    @FindBy(xpath = ALERT_CHANGE_THEME)
    private WebElement allertMessege;

    @FindBy(xpath = MORE_MENU_ITEMS)
    private WebElement moreMenuItem;

    @FindBy(xpath = SPAM_BUTTON)
    private WebElement spamButton;

    @FindBy(xpath = FIRST_SPAM_LETTER)
    private WebElement firstSpamLetter;

    @FindBy(xpath = FIRST_LETTER_THEME)
    private WebElement firstLetterTheme;

    public WriteLetterGoogle writeLetterClick() {
        ClickerHelper.clickOnElement(driver, writeLetterButtonGoogle);
        return new WriteLetterGoogle(driver);
    }

    public PersAccountGoogle settings() {
        ClickerHelper.clickOnElement(driver, settingsButton);
        return this;
    }

    public PersAccountGoogle settingsTheme() {
        ClickerHelper.clickOnElement(driver, settingsTheme);
        return this;
    }

    public PersAccountGoogle selectRandomTheme() {
        ClickerHelper.clickOnElement(driver, allThemes.get(new Random().nextInt(allThemes.size())));
        return this;
    }

    public PersAccountGoogle selectAndVerifyRandomTheme() {
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

    public PersAccountGoogle moveLetterToSpam() {
        ClickerHelper.clickOnElement(driver, firstLetterCheckBox);
        ClickerHelper.clickOnElement(driver, spamTopButton);
        Assert.assertTrue(allertMessege.getText().equals("Цепочка отмечена как спам."));
        return this;
    }

    public PersAccountGoogle goToSpam() {
        ClickerHelper.clickOnElement(driver, moreMenuItem);
        ClickerHelper.clickOnElement(driver, spamButton);
        ClickerHelper.clickOnElement(driver, firstSpamLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_FIRST_SUBTASK));
        return this;
    }

    public PersAccountGoogle dragAndDrop() {

        Actions actions = new Actions(driver);
        actions.dragAndDrop(firstLetterCheckBox, navigateToStarret).release().perform();
        delay(Constants.SEC_DELAY + Constants.SEC_DELAY);
        return this;
    }

    public PersAccountGoogle goToStarredTabVerifyLetter() {
        ClickerHelper.clickOnElement(driver, navigateToStarret);
        ClickerHelper.clickOnElement(driver, firstStarredLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_SECOND_SUBTASK));
        return this;
    }

    public PersAccountGoogle goToInboxTabVerifyLetter() {
        ClickerHelper.clickOnElement(driver, firstInboxLetter);
        Assert.assertTrue(firstLetterTheme.getText().equals(Constants.THEME_FOR_THIRD_SUBTASK));
        Assert.assertTrue(attachedToLetterFile.getText().equals(Constants.FILE_NAME));
        return this;
    }

}
