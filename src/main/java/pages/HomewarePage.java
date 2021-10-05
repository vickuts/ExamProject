package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomewarePage extends ParentPage{
    @FindBy(xpath = ".//a[@title='Accessories']")
    private Button buttonAccessories;

    public HomewarePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/homeware";
    }

    public HomewarePage openHomewarePage(){
        try {
            webDriver.get(BASE_URL + getRelativeUrl());
            logger.info("Homeware page was opened");
        } catch (Exception e){
            logger.error("Can not work with Homeware page" + e);
            Assert.fail("Can not work with Homeware page");
        }
        return this;
    }

    public DecorativeAccessoriesPage clickButtonAccessories() {
        clickOnElement(buttonAccessories);
        return new DecorativeAccessoriesPage(webDriver);
    }
}
