package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//*[text()='My Account']")
    private Link linkMyAccount;
    @FindBy(xpath = ".//*[@id='snail-trail-container']//li//*[text()='HOME']")
    private Link linkHome;
    @FindBy(xpath = ".//*[text()='All Home Accessories']")
    private Link linkHomeAccessories;
    @FindBy(id = "catalogue")
    private WebElement catalog;
    @FindBy(id = "header-big-screen-search-box")
    private TextInput inputSearch;
    @FindBy(xpath = ".//*[@id='header-big-screen-search-box']//..//..//*[@alt='Search Icon']")
    private Button buttonGlass;
    @FindBy(xpath = ".//*[@alt='Favourites icon']")
    private Button buttonFavourites;
    @FindBy(xpath = ".//*[@data-testid='shopping-bag-link-button']")
    private Button buttonShoppingBag;
    @FindBy(xpath = ".//*[text()='Your shopping bag is empty']")
    private TextInput messageEmptyBag;
    @FindBy(xpath = ".//*[@title='Next Icon']")
    private Link logoNext;

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
            logger.info("Home page was opened");
        }catch (Exception e){
            logger.error("Can not work with Home page" + e);
            Assert.fail("Can not work with Home page");
        }
        return this;
    }

    public LoginPage clickOnLinkMyAccount(){
        clickOnElement(linkMyAccount);
        return new LoginPage(webDriver);
    }

    public DecorativeAccessoriesPage selectAllHomeAccessoriesCategory() {
        try {
            Actions actions = new Actions(webDriver);
            WebElement linkHome = webDriver.findElement(By.xpath(".//*[@id='snail-trail-container']//li//*[text()='HOME']"));
            actions.moveToElement(linkHome).perform();
            webDriverWait30.until(ExpectedConditions.visibilityOf(catalog));
//            Thread.sleep(2000);
            WebElement linkHomeAccessories = webDriver.findElement(By.xpath(".//*[text()='All Home Accessories']"));
            actions.moveToElement(linkHomeAccessories).click().perform();
            logger.info("All accessories category was selected");
        } catch (Exception e) {
            logger.error("Can not work with All accessories category" + e);
            Assert.fail("Can not work with All accessories category");
        }
        return new DecorativeAccessoriesPage(webDriver);
    }

    public HomewarePage clickLinkHome() {
        clickOnElement(linkHome);
        return new HomewarePage(webDriver);
    }

    public HomePage clickOnSearchField() {
        clickOnElement(inputSearch);
        return this;
    }

    public HomePage checkSearchFieldActiveState() {
        isPlaceholderDisplayed(inputSearch, "Search product or brand");
        isElementPresent(buttonGlass);
        return this;
    }

    public HomePage enterGreenCeramicTextToSearchField() {
        enterTextToElement(inputSearch, "Green Ceramic");
        return this;
    }

    public GreenCeramicPage clickOnSearchButton() {
        clickOnElement(buttonGlass);
        return new GreenCeramicPage(webDriver);
    }

    public FavouritesPage clickOnFavouritesButton() {
        clickOnElement(buttonFavourites);
        return new FavouritesPage(webDriver);
    }

    public HomePage clickOnShoppingBagButton() {
        clickOnElement(buttonShoppingBag);
        return this;
    }

    public HomePage checkShoppingBagIsEmpty() {
        Assert.assertTrue("Shopping bag is not empty", isElementPresent(messageEmptyBag));
        return this;
    }

    public HomePage clickOnNextLogo() {
        clickOnElement(logoNext);
        return this;
    }
}
