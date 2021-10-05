package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Image;

public class InstagramPage extends ParentPage{
    @FindBy(xpath = ".//img[@alt='Instagram']")
    private Image labelInstagram;
    @FindBy(xpath = ".//img[@alt='Фото профиля nextofficial']")
    private Image labelNext;

    public InstagramPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    protected void checkUrl(){
        Assert.assertEquals("Invalid page ",
                "https://www.instagram.com/nextofficial/",
                webDriver.getCurrentUrl()
        );
    }

    public void switchToTabByNumber(int windowNumber) {
        try {
            getAllOpenedTabs();
            webDriver.switchTo().window(tabs.get(windowNumber));
            logger.info("Tab with URL https://www.instagram.com/nextofficial/ is opened");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public InstagramPage checkIsTabInstagramOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isLabelInstagramPresent(){
        return isElementPresent(labelInstagram);
    }

    public boolean isLabelNextPresent(){
        return isElementPresent(labelNext);
    }

    public LoginPage checkIsRedirectionOnInstagramPage() {
        checkUrl();
        isLabelInstagramPresent();
        isLabelNextPresent();
        return new LoginPage(webDriver);
    }
}
