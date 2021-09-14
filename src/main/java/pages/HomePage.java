package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//*[text()='My Account']")
    private Link linkMyAccount;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage openHomePage(){
        try {
            webDriver.get(BASE_URL);
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
        return this;
    }

    public LoginPage clickOnLinkMyAccount(){
        clickOnElement(linkMyAccount);
        return new LoginPage(webDriver);
    }
}
