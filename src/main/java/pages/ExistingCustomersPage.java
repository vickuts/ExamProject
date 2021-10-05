package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class ExistingCustomersPage extends ParentPage {
    @FindBy(id = "SignInBtn")
    private Button buttonSignInNow;
    @FindBy(xpath = ".//p[contains(text(),'We have matched your Email to an existing account.')]")
    private TextBlock bannerInfo;

    public ExistingCustomersPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/LoginMatched.aspx";
    }

    public ExistingCustomersPage openExistingCustomersPage() {
        try {
            webDriver.get(BASE_URL.replace("www","account") + getRelativeUrl());
            logger.info("Existing customers page was opened");
        }catch (Exception e){
            logger.error("Can not work with Existing customers page" + e);
            Assert.fail("Can not work with Existing customers page");
        }
        return this;
    }

    public ExistingCustomersPage checkIsRedirectionOnExistingCustomersPage() {
        webDriverWait10.until(ExpectedConditions.visibilityOf(bannerInfo));
        checkUrlWithPattern();
        isBannerInfoPresent();
        isButtonSignInNowPresent();
        return null;
    }

    private boolean isButtonSignInNowPresent() {
        return isElementPresent(buttonSignInNow);
    }

    private boolean isBannerInfoPresent() {
        return isElementPresent(bannerInfo);
    }
}
