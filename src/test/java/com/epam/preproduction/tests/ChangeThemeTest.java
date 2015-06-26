package com.epam.preproduction.tests;


import com.epam.preproduction.googlemail.helpers.Constants;
import com.epam.preproduction.googlemail.pages.BasePage;
import com.epam.preproduction.googlemail.pages.LoginGoogle;
import com.epam.preproduction.googlemail.pages.LogoutGoogle;
import com.epam.preproduction.googlemail.pages.PersonalAccountGoogle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class ChangeThemeTest extends BaseTest {

    public static final Logger INFO = Logger.getLogger(BasePage.class);

    protected LoginGoogle loginGoogle;
    protected PersonalAccountGoogle personalAccountGoogle;
    protected LogoutGoogle logoutGoogle;

    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        personalAccountGoogle = new PersonalAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void changeTheme() {
        INFO.info("Change theme test begin");
        INFO.info("Login to account");
        personalAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        personalAccountGoogle.selectAndVerifyRandomTheme();
        logoutGoogle.clickLogoutFromGoogle().logoutGoogle();
    }
}
