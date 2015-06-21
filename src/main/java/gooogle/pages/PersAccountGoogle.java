package gooogle.pages;

import components.BasePage;
import helpers.ClickerHelper;
import helpers.WaiterHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

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
    private final static String FIRST_SPAM_LETTER_THEME ="//table[@role='presentation']/tr/td//h2";

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

    @FindBy(xpath = FIRST_SPAM_LETTER_THEME)
    private WebElement firstSpamLetterTheme;

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
        Assert.assertTrue(firstSpamLetterTheme.getText().equals("Theme"));
        return this;
    }

    Actions actions = new Actions(driver);

    private final static String TO = "//a[contains(@href, 'starred')]";
@FindBy(xpath = TO)
private WebElement to;


    public PersAccountGoogle dragAndDrop() {

        WebElement draggable = to;
        WebElement target = firstLetterCheckBox;
        new Actions(driver).clickAndHold(draggable).moveToElement(target)
                .release().perform();



//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(firstLetterCheckBox, receivedMailPage.getStarredTab().getWrappedElement()).build().perform();
//        CustomWaits.waitForPresenceOfElementLocated(driver, XpathContainer.GmailMailPageInfo.STARRED_CONFIRMATION_XPATH);
        return this;
    }


}
