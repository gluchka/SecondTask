package helpers;



import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class WaiterHelper {

    public static boolean waitElement(WebDriver driver, WebElement element,	int seconds) {
        for (int i = 0; i < seconds; i += Constants.HALF_SEC_DELAY) {
            driver.manage().timeouts()
                    .implicitlyWait(Constants.HALF_SEC_DELAY, TimeUnit.MILLISECONDS);
            try {
                if (element.isDisplayed()) {
                    return true;
                } else {
                    delay(Constants.HALF_SEC_DELAY);
                }
            } catch (NoSuchElementException e) {
            } finally {
                driver.manage().timeouts().implicitlyWait(Constants.MIDDLE_DELAY, TimeUnit.MILLISECONDS);
            }
        }
        return false;
    }


    public static boolean isElementVisible(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.SMALL_DELAY);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
            return true;
        } catch (NoSuchElementException | ElementNotVisibleException e) {
            return false;
        }
    }

    public static void delay(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException!");
        }
    }

}
