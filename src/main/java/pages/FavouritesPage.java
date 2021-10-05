package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class FavouritesPage extends ParentPage{
    @FindBy(id = "FLP-title")
    private TextBlock titleFavourites;
    @FindBy(id = "noItems")
    private TextBlock messageNoItems;
    @FindBy(xpath = ".//*[@class='Breadcrumb bcHome']//*[text()='Home']")
    private Link breadcrumbHome;
    @FindBy(xpath = ".//*[text()='Green Regular Ceramic Pear Ornament']")
    private HtmlElement productGreenCeramicPear;

    public FavouritesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/favourites";
    }

    public FavouritesPage checkIsRedirectionOnFavouritesPage() {
        checkUrl();
        isTitleFavouritesPresent();
        return this;
    }

    private boolean isTitleFavouritesPresent() {
        return isElementPresent(titleFavourites);
    }

    public FavouritesPage checkFavouritesNoSavedItems() {
        Assert.assertTrue("Favourites products were added", isElementPresent(messageNoItems));
        return this;
    }

    public HomePage clickOnHomeBreadcrumb() {
        clickOnElement(breadcrumbHome);
        return new HomePage(webDriver);
    }

    public FavouritesPage checkFavouritesAddedItems() {
        webDriverWait10.until(ExpectedConditions.visibilityOf(productGreenCeramicPear));
        Assert.assertTrue("Product Green Pear is not displayed in favourites", isElementPresent(productGreenCeramicPear));
        return this;
    }
}
