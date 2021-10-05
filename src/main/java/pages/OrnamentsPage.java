package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class OrnamentsPage extends ParentPage{
    @FindBy(xpath = ".//p[text()='Colour']")
    private Button buttonExpandColour;
    @FindBy(xpath = ".//h1//span[text()='Ornaments']")
    private TextBlock titleOrnaments;
    @FindBy(xpath = ".//p[text()='category']")
    private Button buttonExpandCategory;

    Actions actions = new Actions(webDriver);

    public OrnamentsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/shop/department-homeware-productaffiliation-decorativeaccessories/category-ornaments";
    }

    public OrnamentsPage openOrnamentsPage(){
        try {
            webDriver.get(BASE_URL + getRelativeUrl());
            logger.info("Ornaments page was opened");
        } catch (Exception e){
            logger.error("Can not work with Ornaments page" + e);
            Assert.fail("Can not work with Ornaments page");
        }
        return this;
    }

    public OrnamentsPage clickButtonExpandColour() {
        clickOnElement(buttonExpandColour);
        return this;
    }

    public OrnamentsGreenPage setCheckboxGreen() {
        Actions actions = new Actions(webDriver);
        WebElement checkboxGreen = webDriver.findElement(By.id("colour8"));
        actions.moveToElement(checkboxGreen).perform();
        setCheckBoxValue(checkboxGreen, "check");
        return new OrnamentsGreenPage(webDriver);
    }

    public OrnamentsPage checkIsRedirectionOnOrnamentsPage() {
        checkUrlViaFilter();
        isTitleOrnamentsPresent();
        return this;
    }

    private boolean isTitleOrnamentsPresent() {
        return isElementPresent(titleOrnaments);
    }

    public OrnamentsPage clickButtonCollapseCategory() {
        clickOnElement(buttonExpandCategory);
        return this;
    }

    public ProductDetailsPage clickOnGreenCeramicAppleOrnaments() {
        WebElement linkGreenCeramicAppleOrnament = webDriver.findElement(By.xpath(".//*[text()='Green Ceramic Apple Ornament']"));
        actions.moveToElement(linkGreenCeramicAppleOrnament).perform();
        clickOnElement(linkGreenCeramicAppleOrnament, "Green Ceramic Apple Ornament link");
        return new ProductDetailsPage(webDriver);
    }

    public ProductDetailsPage clickOnGreenCeramicPearOrnaments() {
        WebElement linkGreenCeramicPearOrnament = webDriver.findElement(By.xpath(".//*[text()='Green Ceramic Pear Ornament']"));
        actions.moveToElement(linkGreenCeramicPearOrnament).perform();
        clickOnElement(linkGreenCeramicPearOrnament, "Green Ceramic Pear Ornament link");
        return new ProductDetailsPage(webDriver);
    }
}
