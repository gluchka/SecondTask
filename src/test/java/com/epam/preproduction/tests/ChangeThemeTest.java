package com.epam.preproduction.tests;


import com.epam.preproduction.googlemail.pages.LoginGoogle;
import  com.epam.preproduction.googlemail.pages.LogoutGoogle;
import  com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import com.epam.preproduction.googlemail.helpers.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class ChangeThemeTest extends BaseTest {

    String login = Constants.LOGIN_FIRST_USER;
    String password = Constants.PASSWORD_FIRST_USER;

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
    public void changeTheme() {
        personalAccountGoogle = loginGoogle.loginToAccount(login, password);
        personalAccountGoogle.selectAndVerifyRandomTheme();
        logoutGoogle.clickLogoutFromGoogle().logoutGoogle();
    }
}
