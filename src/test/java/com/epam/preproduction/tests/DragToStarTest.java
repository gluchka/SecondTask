package com.epam.preproduction.tests;

import com.epam.preproduction.googlemail.helpers.Constants;
import com.epam.preproduction.googlemail.pages.LoginGoogle;
import com.epam.preproduction.googlemail.pages.LogoutGoogle;
import com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class DragToStarTest extends BaseTest {


    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        personalAccountGoogle = new PersonalAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void letterInStarred() {
        LOGGER.info("Test that move letter to starred with drag-and-drop method");
        LOGGER.info("Loin to mail");
        personalAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        LOGGER.info("Send letter to user2, login as user2");
        personalAccountGoogle.writeLetterClick()
                .enterRecipient(Constants.LOGIN_SECOND_USER)
                .enterSubject(Constants.THEME_FOR_SECOND_SUBTASK)
                .sendLetterTo()
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        LOGGER.info("DragAndDrop letter to starred and verify");
        personalAccountGoogle.dragAndDrop()
                .goToStarredTabVerifyLetter()
                .logoutEnd();
    }

}
