package com.epam.preproduction.tests;

import com.epam.preproduction.googlemail.pages.LoginGoogle;
import com.epam.preproduction.googlemail.pages.LogoutGoogle;
import com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import com.epam.preproduction.googlemail.helpers.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class AddFileTest extends BaseTest {

    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        personalAccountGoogle = new PersonalAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void attachFiles() {
        LOGGER.info("Test with attaching file is began");
        LOGGER.info("Login to account");
        personalAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        LOGGER.info("Write and send second letter with attached file, login as user2");
        personalAccountGoogle.writeLetterClick()
                .sendLetterWithAttachedFile(Constants.LOGIN_SECOND_USER, Constants.THEME_FOR_THIRD_SUBTASK, Constants.MESSAGE_CONTENT, Constants.FILE_PATH)
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        LOGGER.info("Verify that letter came with attached file");
        personalAccountGoogle.goToInboxTabVerifyLetter()
                .logoutEnd();
    }


}
