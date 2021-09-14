package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class AccountPage extends ParentPage{
    @FindBy(id = "btnlogout")
    private Button buttonSignOut;
    @FindBy(id = "sec")
    private TextBlock sectionMyAccount;
    @FindBy(id = "signindetails")
    private TextBlock sectionSignInDetails;
    @FindBy(id = "billingaddress")
    private TextBlock sectionBillingAddress;
    @FindBy(id = "saveCards")
    private TextBlock sectionSavedCards;
    @FindBy(className = "BigBox")
    private TextBlock sectionCommunicationPreference;

    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/myaccount";
    }

    public AccountPage openAccountPage() {
        try {
            webDriver.get(BASE_URL.replace("www","account") + getRelativeUrl());
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
        return this;
    }

    public AccountPage checkIsRedirectionOnAccountPage(){
        checkUrlWithPattern();
        isButtonSignOutPresent();
        isSectionMyAccountPresent();
        isSectionSignInDetailsPresent();
        isSectionBillingAddressPresent();
        isSectionSavedCardsPresent();
        isSectionCommunicationPreferencePresent();
        return this;
    }

    private boolean isSectionCommunicationPreferencePresent() {
        return isElementPresent(sectionCommunicationPreference);
    }

    private boolean isSectionSavedCardsPresent() {
        return isElementPresent(sectionSavedCards);
    }

    private boolean isSectionBillingAddressPresent() {
        return isElementPresent(sectionBillingAddress);
    }

    private boolean isSectionSignInDetailsPresent() {
        return isElementPresent(sectionSignInDetails);
    }

    private boolean isSectionMyAccountPresent() {
        return isElementPresent(sectionMyAccount);
    }

    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }
}
