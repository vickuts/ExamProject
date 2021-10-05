package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class OrnamentsGreenPage extends ParentPage{
    @FindBy(xpath = ".//h1//span[text()='Ornaments']//..//span[text()='Green']")
    private TextBlock titleOrnamentsGreen;
    @FindBy(xpath = ".//*[text()='Green Ceramic Apple Ornament']")
    private HtmlElement productGreenCeramicApple;
    @FindBy(xpath = ".//*[text()='Green Ceramic Pear Ornament']")
    private HtmlElement productGreenCeramicPear;
    @FindBy(xpath = ".//*[@id='colour']//*[text()='Clear']")
    private Button buttonClearCategory;
    @FindBy(xpath = ".//*[text()='Clear All']")
    private Button buttonClearAll;

    public OrnamentsGreenPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/shop/department-homeware-productaffiliation-decorativeaccessories/category-ornaments-colour-green";
    }

    public OrnamentsGreenPage checkIsRedirectionOnOrnamentsGreenPage() {
        checkUrlViaFilter();
        isTitleOrnamentsGreenPresent();
        return this;
    }

    private boolean isTitleOrnamentsGreenPresent() {
        return isElementPresent(titleOrnamentsGreen);
    }

    public OrnamentsGreenPage checkIsDisplayedGreenCeramicAppleOrnament() {
        Assert.assertTrue("Product Green Apple is not displayed in results", isElementPresent(productGreenCeramicApple));
        return this;
    }

    public OrnamentsGreenPage checkIsDisplayedGreenCeramicPearOrnament() {
        Assert.assertTrue("Product Green Pear is not displayed in results", isElementPresent(productGreenCeramicPear));
        return this;
    }

    public OrnamentsPage clickButtonClearColour() {
        clickOnElement(buttonClearCategory);
        return new OrnamentsPage(webDriver);
    }

    public DecorativeAccessoriesPage clickButtonClearAll() {
        clickOnElement(buttonClearAll);
        return new DecorativeAccessoriesPage(webDriver);
    }
}
