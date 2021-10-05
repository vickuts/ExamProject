package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class DecorativeAccessoriesPage extends ParentPage{
    @FindBy(xpath = ".//h5[text()='Ornaments']")
    private Link linkOrnaments;
    @FindBy(xpath = ".//p[text()='category']")
    private Button buttonExpandCategory;
    @FindBy(xpath = ".//h1[text()='Decorative Accessories']")
    private TextBlock titleDecorativeAccessories;

    public DecorativeAccessoriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/shop/department-homeware-productaffiliation-decorativeaccessories-0";
    }

    public DecorativeAccessoriesPage openDecorativeAccessoriesPage(){
        try {
            webDriver.get(BASE_URL + getRelativeUrl());
            logger.info("Decorative accessories page was opened");
        } catch (Exception e){
            logger.error("Can not work with Decorative accessories page" + e);
            Assert.fail("Can not work with Decorative accessories page");
        }
        return this;
    }

    public OrnamentsPage selectOrnamentsSubcategory() {
        clickOnElement(linkOrnaments);
        return new OrnamentsPage(webDriver);
    }

    public DecorativeAccessoriesPage clickButtonExpandCategory() {
        clickOnElement(buttonExpandCategory);
        return this;
    }

    public OrnamentsPage setCheckboxOrnaments() {
        Actions actions = new Actions(webDriver);
        WebElement checkboxGreen = webDriver.findElement(By.id("category2"));
        actions.moveToElement(checkboxGreen);
        setCheckBoxValue(checkboxGreen, "check");
        return new OrnamentsPage(webDriver);
    }

    public DecorativeAccessoriesPage checkIsRedirectionOnDecorativeAccessoriesPage() {
        checkUrl();
        isTitleDecorativeAccessoriesPresent();
        return this;
    }

    private boolean isTitleDecorativeAccessoriesPresent() {
        return isElementPresent(titleDecorativeAccessories);
    }
}
