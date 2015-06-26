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
public class DragToStarTest extends BaseTest {

    LoginGoogle loginGoogle;
    PersonalAccountGoogle personalAccountGoogle;
    LogoutGoogle logoutGoogle;

    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        personalAccountGoogle = new PersonalAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void letterInStarred() {
        personalAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        personalAccountGoogle.writeLetterClick()
                .enterRecipient(Constants.LOGIN_SECOND_USER)
                .enterSubject(Constants.THEME_FOR_SECOND_SUBTASK)
                .sendLetterTo()
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        personalAccountGoogle.dragAndDrop()
                .goToStarredTabVerifyLetter()
                .logoutEnd();
    }

}
