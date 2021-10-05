package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class PrivacyPolicyPage extends ParentPage{
    @FindBy(xpath = ".//h1[text()='Privacy Policy']")
    private TextBlock titlePrivacyPolicy;

    public PrivacyPolicyPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/privacypolicy";
    }

    public PrivacyPolicyPage checkIsTabPrivacyPolicyOpened() {
        switchToTabByNumber(1);
        return this;
    }

    public boolean isTitlePrivacyPolicyPresent(){
        return isElementPresent(titlePrivacyPolicy);
    }

    public RegistrationPage checkIsRedirectionOnPrivacyPolicyPage() {
        checkUrl();
        isTitlePrivacyPolicyPresent();
        return new RegistrationPage(webDriver);
    }

    public RegistrationPage closeOpenedTab() {
        closeActiveTabAndSwitchToTabByNumber(0);
        return new RegistrationPage(webDriver);
    }

    public PrivacyPolicyPage checkIsTabCookiesAndPrivacyPolicyOpened() {
        switchToTabByNumber(2);
        return this;
    }
}
