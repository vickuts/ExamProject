package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

import static libs.TestData.VALID_EMAIL;
import static libs.TestData.VALID_PASSWORD;

public class LoginTest extends BaseTest {
    @Test
    public void validLogin(){
        loginPage
                .openLoginPage()
                .enterEmailInSignIn(VALID_EMAIL)
                .enterPasswordInSignIn(VALID_PASSWORD)
                .clickOnButtonSignIn()
            .checkIsRedirectionOnAccountPage();
    }

    @Test
    public void invalidLogin(){
        loginPage
                .openLoginPage()
                .enterEmailInSignIn(VALID_EMAIL)
                .enterPasswordInSignIn("12345")
                .clickOnButtonSignIn();
        checkExpectedResult("Button Sign Out is visible", accountPage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Error is not displayed", loginPage.isAlertErrorPresent(), true);
    }

    @Test
    public void forgottenPassword(){
        loginPage
                .openLoginPage()
                .enterEmailInSignIn(VALID_EMAIL)
                .clickOnLinkForgottenPassword()
            .checkWindowModal(VALID_EMAIL)
                .clickOnLinkEdit()
//            .checkIsEmailAddressOrCustomerNumberFieldEmpty()
//            .checkPlaceholderForEmailAddressOrCustomerNumberField()
        ;
    }
}
