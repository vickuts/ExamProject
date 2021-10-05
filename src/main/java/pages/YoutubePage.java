package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Image;

public class YoutubePage extends ParentPage{
    @FindBy(id = "logo-icon")
    private Image titleYoutube;
    @FindBy(xpath = ".//*[@id='channel-header-container']//*[@id='avatar']")
    private Image labelNext;

    public YoutubePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    protected void checkUrl(){
        Assert.assertEquals("Invalid page ",
                "https://www.youtube.com/user/nextshopping",
                webDriver.getCurrentUrl()
        );
    }

    public void switchToTabByNumber(int windowNumber) {
        try {
            getAllOpenedTabs();
            webDriver.switchTo().window(tabs.get(windowNumber));
            logger.info("Tab with URL https://www.youtube.com/user/nextshopping is opened");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public YoutubePage checkIsTabYoutubeOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isTitleYoutubePresent(){
        return isElementPresent(titleYoutube);
    }

    public boolean isLabelNextPresent(){
        return isElementPresent(labelNext);
    }

    public LoginPage checkIsRedirectionOnYoutubePage() {
        checkUrl();
        isTitleYoutubePresent();
        isLabelNextPresent();
        return new LoginPage(webDriver);
    }
}
