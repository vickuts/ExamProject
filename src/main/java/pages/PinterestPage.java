package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class PinterestPage extends ParentPage{
    @FindBy(xpath = ".//h2[text()='Pinterest']")
    private TextBlock titlePinterest;
    @FindBy(xpath = ".//img[@alt='Аватар пользователя']")
    private Image labelNext;

    public PinterestPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    protected void checkUrl(){
        Assert.assertEquals("Invalid page ",
                "https://www.pinterest.com/nextofficial/",
                webDriver.getCurrentUrl()
        );
    }

    public void switchToTabByNumber(int windowNumber) {
        try {
            getAllOpenedTabs();
            webDriver.switchTo().window(tabs.get(windowNumber));
            logger.info("Tab with URL https://www.pinterest.com/nextofficial/ is opened");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public PinterestPage checkIsTabPinterestOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isTitlePinterestPresent(){
        return isElementPresent(titlePinterest);
    }

    public boolean isLabelNextPresent(){
        return isElementPresent(labelNext);
    }

    public LoginPage checkIsRedirectionOnPinterestPage() {
        checkUrl();
        isTitlePinterestPresent();
        isLabelNextPresent();
        return new LoginPage(webDriver);
    }
}
