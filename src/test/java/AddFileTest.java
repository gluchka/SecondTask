import components.BaseTest;
import gooogle.pages.LoginGoogle;
import gooogle.pages.LogoutGoogle;
import gooogle.pages.PersAccountGoogle;
import helpers.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class AddFileTest extends BaseTest {

    String login = Constants.LOGIN_FIRST_USER;
    String password = Constants.PASSWORD_FIRST_USER;
    LoginGoogle loginGoogle;
    PersAccountGoogle persAccountGoogle;
    LogoutGoogle logoutGoogle;

    @BeforeMethod
    public void preparationForTheTest() {
        loginGoogle = new LoginGoogle(driver);
        persAccountGoogle = new PersAccountGoogle(driver);
        logoutGoogle = new LogoutGoogle(driver);
    }

    @Test
    public void changeTheme() {
        persAccountGoogle = loginGoogle.loginToAccount(login, password);
        persAccountGoogle.writeLetterClick();
    }
}
