package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 * Created by Viktoriia_Akhadova on 21-Jun-15.
 */
public class DriverManager {


        public static WebDriver chromeDriver()
        {
            System.setProperty("webdriver.chrome.driver", "DriversExeLib/chromedriver.exe");
            return new ChromeDriver();
        }
        public static WebDriver operaDriver()
        {
            System.setProperty("webdriver.opera.driver", "DriversExeLib/operadriver64.exe");
            return new OperaDriver();
        }
        public static WebDriver ieDriver()
        {
            System.setProperty("webdriver.ie.driver", "DriversExeLib/IEDriverServer64.exe");
            return new InternetExplorerDriver();
        }
        public static WebDriver firefoxDriver()
        {
            return new FirefoxDriver();
        }



}
