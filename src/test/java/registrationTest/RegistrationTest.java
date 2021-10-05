package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {
    @Test
    public void requiredFields() {
        homePage
                .openHomePage()
                .clickOnLinkMyAccount()
            .checkIsRedirectionOnLoginPage()
                .clickOnButtonRegisterNow()
            .checkIsRedirectionOnRegistrationPage()
                .clickOnButtonRegister()
            .checkAlertMessages("Please select a title.;" +
                        "Your first name was missing.;" +
                        "Your last name was missing.;" +
                        "Please use a valid email address.;" +
                        "Your password was missing.;" +
                        "Your telephone number was missing.;" +
                        "Please enter a House Number & Street Name.;" +
                        "Please enter a Village or Town or City.;" +
                        "Please enter a Province.;" +
                        "Please enter a Zip Code.");
    }

    @Test
    @Parameters({"Mr,q,q,q,1,1,1,1,q,q,1," +
            "Please supply a valid first name;" +
            "Please supply a valid last name;" +
            "This value should be a valid email.;" +
            "Your password needs to contain both numbers and letters and be 6-12 characters long.;" +
            "Length must be more than 6 and less than 15 digits",

            "Mr,q,q,q,1,q,1,1,q,q,1," +
            "Please supply a valid first name;" +
            "Please supply a valid last name;" +
            "This value should be a valid email.;" +
            "Your password needs to contain both numbers and letters and be 6-12 characters long.;" +
            "Please enter a valid Contact Number."
    })
    @TestCaseName("registrationAlerts: " +
            "title = {0}, " +
            "firstName = {1}, " +
            "lastName = {2}, " +
            "email = {3}, " +
            "password = {4}, " +
            "contactTelephone = {5}, " +
            "apartmentNumber = {6}, " +
            "houseNumber = {7}, " +
            "city = {8}, " +
            "province = {9}, " +
            "zipCode = {10}")
    public void registrationAlerts(String title, String firstName, String lastName, String email,
                                   String password, String contactTelephone, String apartmentNumber,
                                   String houseNumber, String city, String province, String zipCode,
                                   String alerts) {
        homePage
                .openHomePage()
                .clickOnLinkMyAccount()
                .clickOnButtonRegisterNow()
            .checkPlaceholderForTitle()
                .selectValueInDDTitle(title)
                .enterFirstNameInRegistration(firstName)
                .enterLastNameInRegistration(lastName)
                .enterEmailInRegistration(email)
            .checkShowPasswordButton()
                .clickOnFieldPasswordInRegistration()
            .checkTooltipForPasswordInRegistration()
                .enterPasswordInRegistration(password)
            .checkCountryCodePrefixForContactTelephoneInRegistration()
            .checkTooltipForContactTelephoneInRegistration()
                .enterContactTelephoneInRegistration(contactTelephone)
                .enterApartmentOrFlatInRegistration(apartmentNumber)
                .enterHouseNumberAndStreetNameInRegistration(houseNumber)
                .enterVillageOrTownOrCityInRegistration(city)
                .enterProvinceInRegistration(province)
                .enterZipCodeInRegistration(zipCode)
                .clickOnButtonRegister()
            .checkAlertMessages(alerts);
    }

    @Test
    public void newCustomerCreation() {
        loginPage
                .openLoginPage()
                .clickOnButtonRegisterNow()
                .registrationWithInputtedData("Mrs", "Test", "Testovi4", "test@testovi4.com",
                        "1q2w3e4r5t6y", "123456780", "5",
                        "Jenkins, 8080", "Java", "IT", "77777",
                        "check", "uncheck", "check")
            .checkIsRedirectionOnAccountPage();
    }

    @Test
    public void existingCustomerByEmail() {
        loginPage
                .openLoginPage()
                .clickOnButtonRegisterNow()
                .registrationWithExistingEmail("auto@test.com")
            .checkIsRedirectionOnExistingCustomersPage();
    }

    @Test
    public void regirectLinks() {
        loginPage
                .openLoginPage()
                .clickOnButtonRegisterNow()
                .clickOnLinkPrivacyAndCookiePolicy()
            .checkIsTabPrivacyPolicyOpened()
            .checkIsRedirectionOnPrivacyPolicyPage()
                .clickOnBackButton()
                .clickOnLinkTermsAndConditions()
            .checkIsTabTermsOpened()
            .checkIsRedirectionOnTermsPage()
                .clickOnBackButton()
                .clickOnLinkCookiesAndPrivacyPolicy()
            .checkIsTabCookiesAndPrivacyPolicyOpened()
            .checkIsRedirectionOnPrivacyPolicyPage();
    }
}