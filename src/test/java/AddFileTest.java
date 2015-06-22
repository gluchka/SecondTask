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
    public void attachFiles() {
        persAccountGoogle = loginGoogle.loginToAccount(Constants.LOGIN_FIRST_USER, Constants.PASSWORD_FIRST_USER);
        persAccountGoogle.writeLetterClick()
                .sendLetterWithAttachedFile(Constants.LOGIN_SECOND_USER, Constants.THEME_FOR_THIRD_SUBTASK, Constants.MESSAGE_CONTENT, Constants.FILE_PATH)
                .loginToAnotherUser(Constants.LOGIN_SECOND_USER, Constants.PASSWORD_SECOND_USER);
        persAccountGoogle.goToInboxTabVerifyLetter()
                .logoutEnd();
    }


}
