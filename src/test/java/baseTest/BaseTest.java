package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver webDriver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected AccountPage accountPage;
    protected Logger logger = Logger.getLogger(getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp(){
        logger.info("------- " + testName.getMethodName() + " was started -------");
        webDriver = initDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        accountPage = new AccountPage(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("------- " + testName.getMethodName() + " was ended -------");
    }

    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult){
        Assert.assertEquals(message, expectedResult, actualResult);
    }

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }
}
