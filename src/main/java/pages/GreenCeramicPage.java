package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class GreenCeramicPage extends ParentPage{
    @FindBy(xpath = ".//*[text()='green ceramic']")
    private TextBlock titleGreenCeramic;
    @FindBy(xpath = ".//*[text()='5 PRODUCTS']")
    private TextBlock numberProducts;
    @FindBy(id = "dk_container_iSort")
    private Select dropDownSortBy;
    @FindBy(id = "i1")
    private WebElement firstProductInSearchResults;
    @FindBy(xpath = ".//*[@class='Images']//a[@title='Green Ceramic Pear Ornament']")
    private Link linkGreenCeramicPearOrnaments;

    public GreenCeramicPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/search?w=green%20ceramic";
    }

    public GreenCeramicPage checkIsRedirectionOnGreenCeramicPage() {
        checkUrl();
        isTitleGreenCeramicPresent();
        return this;
    }

    private boolean isTitleGreenCeramicPresent() {
        return isElementPresent(titleGreenCeramic);
    }

    public GreenCeramicPage checkNumberOfSearchResults() {
        Assert.assertTrue("Number of search results are incorrect", isElementPresent(numberProducts));
        return this;
    }

    public GreenCeramicPage selectAlphabeticalValueInDDSortBy() {
        clickOnElement(dropDownSortBy);
        clickOnElement(webDriver.findElement(By.xpath(".//*[text()='Alphabetical']")), "Alphabetical value");
        return this;
    }

    public GreenCeramicPage checkSearchResultsAlphabetOrder() {
        Assert.assertTrue("Search results order is incorrect", firstProductInSearchResults.getText().substring(0, 1).equalsIgnoreCase("a"));
        return this;
    }

    public ProductDetailsPage clickOnGreenCeramicPearOrnaments() {
        clickOnElement(linkGreenCeramicPearOrnaments);
        return new ProductDetailsPage(webDriver);
    }
}
