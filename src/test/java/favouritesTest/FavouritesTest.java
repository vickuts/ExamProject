package favouritesTest;

import baseTest.BaseTest;
import org.junit.Test;

public class FavouritesTest extends BaseTest {
    @Test
    public void favouritesProducts(){
        homePage
                .openHomePage()
                .clickOnFavouritesButton()
            .checkIsRedirectionOnFavouritesPage()
            .checkFavouritesNoSavedItems()
                .clickOnHomeBreadcrumb()
                .clickOnSearchField()
                .enterGreenCeramicTextToSearchField()
                .clickOnSearchButton()
                .clickOnGreenCeramicPearOrnaments()
                .clickOnFavouritesButtonGreenCeramicPearOrnaments()
                .clickOnFavouritesButton()
            .checkFavouritesAddedItems();
    }
}
