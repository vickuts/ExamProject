package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Image;

public class TwitterPage extends ParentPage{
    @FindBy(xpath = ".//*[@aria-label='Твиттер']")
    private Image labelTwitter;
    @FindBy(xpath = ".//a[@href='/nextofficial/photo']")
    private Image labelNext;

    public TwitterPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    protected void checkUrl(){
        Assert.assertEquals("Invalid page ",
                "https://twitter.com/nextofficial",
                webDriver.getCurrentUrl()
        );
    }

    public void switchToTabByNumber(int windowNumber) {
        try {
            getAllOpenedTabs();
            webDriver.switchTo().window(tabs.get(windowNumber));
            logger.info("Tab with URL https://twitter.com/nextofficial is opened");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public TwitterPage checkIsTabTwitterOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isLabelTwitterPresent(){
        return isElementPresent(labelTwitter);
    }

    public boolean isLabelNextPresent(){
        return isElementPresent(labelNext);
    }

    public LoginPage checkIsRedirectionOnTwitterPage() {
        checkUrl();
        isLabelTwitterPresent();
        isLabelNextPresent();
        return new LoginPage(webDriver);
    }
}
