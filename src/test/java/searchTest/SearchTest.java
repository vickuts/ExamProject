package searchTest;

import baseTest.BaseTest;
import org.junit.Test;

public class SearchTest extends BaseTest {
    @Test
    public void searchGreenCeramicProducts(){
        homePage
                .openHomePage()
                .clickOnSearchField()
            .checkSearchFieldActiveState()
                .enterGreenCeramicTextToSearchField()
                .clickOnSearchButton()
            .checkIsRedirectionOnGreenCeramicPage()
            .checkNumberOfSearchResults();
    }

    @Test
    public void sortByAlphabetical(){
        homePage
                .openHomePage()
                .clickOnSearchField()
                .checkSearchFieldActiveState()
                .enterGreenCeramicTextToSearchField()
                .clickOnSearchButton()
                .selectAlphabeticalValueInDDSortBy()
            .checkSearchResultsAlphabetOrder();
    }
}
