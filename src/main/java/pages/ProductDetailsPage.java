package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class ProductDetailsPage extends ParentPage{
    @FindBy(xpath = ".//*[@class='divFavouritesContainer favourite-pdp-heart intl ']")
    private Button buttonFavouritesGreenCeramicPearOrnaments;
    @FindBy(xpath = ".//*[@alt='Favourites icon']")
    private Button buttonFavourites;
    @FindBy(xpath = ".//*[text()='Add To Bag']")
    private Button buttonAddToBag;
    @FindBy(xpath = ".//*[@class='BreadcrumbNavigation']//*[text()='Ornaments']")
    private Link breadcrumbOrnaments;
    @FindBy(xpath = ".//*[@data-testid='header-mini-shopping-bag-item-count']")
    private TextBlock textNumberOfItems;

    public ProductDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/style";
    }

    public ProductDetailsPage clickOnFavouritesButtonGreenCeramicPearOrnaments() {
        clickOnElement(buttonFavouritesGreenCeramicPearOrnaments);
        return this;
    }

    public FavouritesPage clickOnFavouritesButton() {
        clickOnElement(buttonFavourites);
        return new FavouritesPage(webDriver);
    }

    public ProductDetailsPage clickOnAddToBagButton() {
        clickOnElement(buttonAddToBag);
        return this;
    }

    public OrnamentsPage clickOnOrnamentsBreadcrumb() {
        clickOnElement(breadcrumbOrnaments);
        return new OrnamentsPage(webDriver);
    }

    public ProductDetailsPage checkNumberOfItemsInShoppingBag(int numberOfProducts) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(textNumberOfItems));
        Assert.assertTrue("Incorrect number of items in shopping bag", textNumberOfItems.getText().substring(0, 1).equalsIgnoreCase(String.valueOf(numberOfProducts)));
        return this;
    }
}
