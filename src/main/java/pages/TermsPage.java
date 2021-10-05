package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class TermsPage extends ParentPage{
    @FindBy(xpath = ".//h1[text()='NEXT DIRECTORY TERMS AND CONDITIONS']")
    private TextBlock titleTermsAndConditions;

    public TermsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/terms";
    }

    public TermsPage checkIsTabTermsOpened() {
        switchToTabByNumber(2);
        return this;
    }

    private boolean isTitleTermsAndConditionsPresent() {
        return isElementPresent(titleTermsAndConditions);
    }

    public RegistrationPage checkIsRedirectionOnTermsPage() {
        checkUrl();
        isTitleTermsAndConditionsPresent();
        return new RegistrationPage(webDriver);
    }
}
