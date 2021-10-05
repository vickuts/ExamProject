package filterTest;

import baseTest.BaseTest;
import org.junit.Test;

public class FilterTest extends BaseTest {
    @Test
    public void filterOrnamentsGreen(){
        homePage
                .openHomePage()
                .clickLinkHome()
                .clickButtonAccessories()
                .selectOrnamentsSubcategory()
                .openOrnamentsPage()
                .clickButtonExpandColour()
                .setCheckboxGreen()
            .checkIsRedirectionOnOrnamentsGreenPage()
            .checkIsDisplayedGreenCeramicAppleOrnament()
            .checkIsDisplayedGreenCeramicPearOrnament();
    }

    @Test
    public void clearCategoryFilter(){
        homePage
                .openHomePage()
                .clickLinkHome()
                .clickButtonAccessories()
                .clickButtonExpandCategory()
                .setCheckboxOrnaments()
            .checkIsRedirectionOnOrnamentsPage()
                .clickButtonCollapseCategory()
                .clickButtonExpandColour()
                .setCheckboxGreen()
            .checkIsRedirectionOnOrnamentsGreenPage()
                .clickButtonClearColour()
            .checkIsRedirectionOnOrnamentsPage();
    }

    @Test
    public void clearAllFilters(){
        homePage
                .openHomePage()
                .clickLinkHome()
                .clickButtonAccessories()
                .clickButtonExpandCategory()
                .setCheckboxOrnaments()
                .clickButtonCollapseCategory()
                .clickButtonExpandColour()
                .setCheckboxGreen()
                .clickButtonClearAll()
            .checkIsRedirectionOnDecorativeAccessoriesPage();

    }
}
