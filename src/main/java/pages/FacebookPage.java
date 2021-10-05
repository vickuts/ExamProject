package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class FacebookPage extends ParentPage{
    @FindBy(xpath = ".//u[text()='Facebook']")
    private TextBlock titleFacebook;
    @FindBy(xpath = ".//*[@aria-label='Основна світлина']")
    private Image labelNext;

    public FacebookPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    protected void checkUrl(){
        Assert.assertEquals("Invalid page ",
                "https://www.facebook.com/nextofficial",
                webDriver.getCurrentUrl()
        );
    }

    public void switchToTabByNumber(int windowNumber) {
        try {
            getAllOpenedTabs();
            webDriver.switchTo().window(tabs.get(windowNumber));
            logger.info("Tab with URL https://www.facebook.com/nextofficial is opened");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public FacebookPage checkIsTabFacebookOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isTitleFacebookPresent(){
        return isElementPresent(titleFacebook);
    }

    public boolean isLabelNextPresent(){
        return isElementPresent(labelNext);
    }

    public LoginPage checkIsRedirectionOnFacebookPage() {
        checkUrl();
        isTitleFacebookPresent();
        isLabelNextPresent();
        return new LoginPage(webDriver);
    }
}
