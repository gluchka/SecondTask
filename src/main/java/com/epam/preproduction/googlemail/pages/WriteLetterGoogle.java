package com.epam.preproduction.googlemail.pages;

import com.epam.preproduction.googlemail.helpers.ClickerHelper;
import com.epam.preproduction.googlemail.helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static com.epam.preproduction.googlemail.helpers.WaiterHelper.delay;

public class WriteLetterGoogle extends BasePage {

    public WriteLetterGoogle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final String TO_RECIPIENT = "//textarea[@name='to']";
    private final String LETTER_SUBJECT = "//input[@name='subjectbox']";
    private final String LETTER_CONTENT = "//div[@role='textbox']";
    private final String CLICK_CANCEL_ADD_DRAFT = ".//img[3]";
    private final String GO_TO_DRAFT = "//a[contains(@href, '#drafts')]";
    private final static String SEND_LETTER = "//tbody//div[count(div) = 2]/div[@role='button' and @data-tooltip]";
    private final static String ATTACH_FILES_BUTTON = "//div[@command = 'Files']/div/div/div";

    @FindBy(xpath = SEND_LETTER)
    private WebElement sendLetter;


    @FindBy(xpath = TO_RECIPIENT)
    private WebElement enterRecipientField;

    @FindBy(xpath = LETTER_SUBJECT)
    private WebElement enterSubjectField;

    @FindBy(xpath = LETTER_CONTENT)
    private WebElement enterСontentField;

    @FindBy(xpath = CLICK_CANCEL_ADD_DRAFT)
    private WebElement clickCancelButton;

    @FindBy(xpath = GO_TO_DRAFT)
    private WebElement goToDraftButton;

    @FindBy(xpath = ATTACH_FILES_BUTTON)
    private WebElement attachFileButton;

    public WriteLetterGoogle enterRecipient(String recipient) {
        enterRecipientField.sendKeys(recipient);
        return this;
    }

    public WriteLetterGoogle enterSubject(String subject) {
        enterSubjectField.sendKeys(subject);
        return this;
    }

    public WriteLetterGoogle enterСontent(String content) {
        enterСontentField.sendKeys(content);
        return this;
    }


    public PersonalAccountGoogle sendLetterTo() {
        ClickerHelper.clickOnElement(driver, sendLetter);
        return new PersonalAccountGoogle(driver);
    }


    public PersonalAccountGoogle sendLetterWithAttachedFile(String email, String subject, String text, String filePath)  {
        enterRecipient(email);
        enterSubject(subject);
        enterСontent(text);
        ClickerHelper.clickOnElement(driver, attachFileButton);
        delay(Constants.SMALL_DELAY );
        uploadFile(filePath);
        delay(Constants.SMALL_DELAY );
        sendLetterTo();
        return new PersonalAccountGoogle(driver);
    }

    private void uploadFile(String filePath) {
        try {
            setClipboardData(filePath);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

        private void setClipboardData(String path) {
            StringSelection stringSelection = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
    }

