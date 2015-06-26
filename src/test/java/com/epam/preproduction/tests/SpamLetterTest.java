package com.epam.preproduction.tests;


import com.epam.preproduction.googlemail.pages.LoginGoogle;
import com.epam.preproduction.googlemail.pages.LogoutGoogle;
import com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import com.epam.preproduction.googlemail.helpers.Constants;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class SpamLetterTest extends BaseTest {
    public static final Logger LOGGER = Logger.getLogger(SpamLetterTest.class);

    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        personalAccountGoogle = new PersonalAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void letterInSpam() {
        LOGGER.info("Test, that demonstrate works with spam");
        LOGGER.info("Login to account");
        personalAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        LOGGER.info("Send letter and login as user2");
        personalAccountGoogle.writeLetterClick()
                .enterRecipient(Constants.LOGIN_SECOND_USER)
                .enterSubject(Constants.THEME_FOR_FIRST_SUBTASK)
                .sendLetterTo()
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        LOGGER.info("Move letter to spam, login as user1");
        personalAccountGoogle.moveLetterToSpam().loginToAnotherUser(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        LOGGER.info("Write and send second letter, login as user2");
        personalAccountGoogle.writeLetterClick()
                .enterRecipient(Constants.LOGIN_SECOND_USER)
                .enterSubject(Constants.THEME_FOR_FIRST_SUBTASK)
                .sendLetterTo()
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        LOGGER.info("Verifythat letter in spam");
        personalAccountGoogle.goToSpam().logoutEnd();

    }

}
