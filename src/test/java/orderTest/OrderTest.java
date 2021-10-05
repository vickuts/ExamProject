package orderTest;

import baseTest.BaseTest;
import org.junit.Test;

public class OrderTest extends BaseTest {
    @Test
    public void orderOrnaments() {
        homePage
                .openHomePage()
                .clickOnShoppingBagButton()
            .checkShoppingBagIsEmpty()
                .clickOnNextLogo()
                .selectAllHomeAccessoriesCategory()
                .selectOrnamentsSubcategory()
                .openOrnamentsPage()
                .clickOnGreenCeramicAppleOrnaments()
                .clickOnAddToBagButton()
            .checkNumberOfItemsInShoppingBag(1)
                .clickOnOrnamentsBreadcrumb()
                .clickOnGreenCeramicPearOrnaments()
                .clickOnAddToBagButton()
            .checkNumberOfItemsInShoppingBag(2);
    }
}
