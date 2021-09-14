package pages;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends ParentPage{
    @FindBy(id = "SignupButton")
    private Button buttonRegister;
    private final String listAlertMessagesLocator = ".//*[@class='field-validation-error']";
    @FindBy(xpath = ".//*[@class='field-validation-error']")
    private List<WebElement> actualListOfAlertMessages;
    private final String expectedPlaceholderText = "Select Title";
    @FindBy(id = "Title")
    private Select dropDownSelectValue;
    @FindBy(id = "FirstName")
    private TextInput inputFirstNameRegistration;
    @FindBy(id = "LastName")
    private TextInput inputLastNameRegistration;
    @FindBy(id = "Email")
    private TextInput inputEmailRegistration;
    @FindBy(xpath = ".//h3[text()='Password must contain:']")
    private TextBlock tooltipForPasswordRegistration;
    @FindBy(id = "Password")
    private TextInput inputPasswordRegistration;
    @FindBy(xpath = ".//*[text()='+380']")
    private WebElement iconCountryCodePrefixRegistration;
    @FindBy(id = "phoneNumberHelp")
    private WebElement tooltipForContactTelephoneRegistration;
    @FindBy(id = "PhoneNumber")
    private TextInput inputContactTelephoneRegistration;
    @FindBy(id = "AddressLine1")
    private TextInput inputApartmentOrFlatRegistration;
    @FindBy(id = "AddressLine2")
    private TextInput inputHouseNumberAndStreetNameRegistration;
    @FindBy(id = "AddressLine3")
    private TextInput inputVillageOrTownOrCityRegistration;
    @FindBy(id = "AddressLine4")
    private TextInput inputProvinceRegistration;
    @FindBy(id = "AddressLine5")
    private TextInput inputZipCodeRegistration;
    @FindBy(xpath = ".//*[@class='hide-show-visibility show']")
    private Button buttonShowPasswordRegistration;
    @FindBy(id = "ChkByEmail")
    private CheckBox checkboxEmail;
    @FindBy(id = "ChkBySale")
    private CheckBox checkboxSaleEmails;
    @FindBy(id = "ChkBySms")
    private CheckBox checkboxSMS;
//    @FindBy(xpath = ".//span[contains(text(), 'This email is already in use. Please sign in to use your existing Next Account.')]")
//    private TextBlock alertExistingCustomer;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/CustomerRegistration";
    }

    public RegistrationPage openRegistrationPage() {
        try {
            webDriver.get(BASE_URL.replace("www","account") + getRelativeUrl());
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
        return this;
    }

    public RegistrationPage checkIsRedirectionOnRegistrationPage(){
        checkUrlWithPattern();
        return this;
    }

    public RegistrationPage clickOnButtonRegister(){
        clickOnElement(buttonRegister);
        return this;
    }

    public void checkAlertMessages(String expectedAlertMessages) {
        String[] alertMessagesArray = expectedAlertMessages.split(";");
        webDriverWait10.withMessage("Number of alert messages ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listAlertMessagesLocator),
                        alertMessagesArray.length));

        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromAlertMessages = new ArrayList<>();
        for (WebElement element: actualListOfAlertMessages) {
            actualTextFromAlertMessages.add(element.getText());
        }
        for (int i = 0; i < alertMessagesArray.length; i++) {
            softAssertions.assertThat(alertMessagesArray[i]).isIn(actualTextFromAlertMessages);
        }
        softAssertions.assertAll();
    }

    public RegistrationPage checkPlaceholderForTitle() {
        String actualPlaceholderText = dropDownSelectValue.getFirstSelectedOption().getText();
        Assert.assertEquals("Placeholder for title is not displayed", expectedPlaceholderText, actualPlaceholderText);
        return this;
    }

    public RegistrationPage selectValueInDDTitle(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public RegistrationPage enterFirstNameInRegistration(String firstName) {
        enterTextToElement(inputFirstNameRegistration, firstName);
        return this;
    }

    public RegistrationPage enterLastNameInRegistration(String lastName) {
        enterTextToElement(inputLastNameRegistration, lastName);
        return this;
    }

    public RegistrationPage enterEmailInRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public RegistrationPage checkShowPasswordButton() {
        Assert.assertTrue("Show password button is not displayed",
                isElementPresent(buttonShowPasswordRegistration));
        return this;
    }

    public RegistrationPage clickOnFieldPasswordInRegistration() {
        clickOnElement(inputPasswordRegistration);
        return this;
    }

    public RegistrationPage checkTooltipForPasswordInRegistration() {
        Assert.assertTrue("Tooltip for password is not displayed",
                isElementPresent(tooltipForPasswordRegistration));
        return this;
    }

    public RegistrationPage enterPasswordInRegistration(String password) {
        enterTextToElement(inputPasswordRegistration, password);
        return this;
    }

    public RegistrationPage checkCountryCodePrefixForContactTelephoneInRegistration() {
        Assert.assertTrue("Code prefix for contact telephone is not displayed",
                isElementPresent(iconCountryCodePrefixRegistration));
        return this;
    }

    public RegistrationPage checkTooltipForContactTelephoneInRegistration() {
        Assert.assertTrue(isElementPresent(tooltipForContactTelephoneRegistration));
        return this;
    }

    public RegistrationPage enterContactTelephoneInRegistration(String contactTelephone) {
        enterTextToElement(inputContactTelephoneRegistration, contactTelephone);
        return this;
    }

    public RegistrationPage enterContactTelephoneInRegistration(Double contactTelephone) {
        enterTextToElement(inputContactTelephoneRegistration, contactTelephone);
        return this;
    }

    public RegistrationPage enterApartmentOrFlatInRegistration(String apartmentNumber) {
        enterTextToElement(inputApartmentOrFlatRegistration, apartmentNumber);
        return this;
    }

    public RegistrationPage enterHouseNumberAndStreetNameInRegistration(String houseNumber) {
        enterTextToElement(inputHouseNumberAndStreetNameRegistration, houseNumber);
        return this;
    }

    public RegistrationPage enterVillageOrTownOrCityInRegistration(String city) {
        enterTextToElement(inputVillageOrTownOrCityRegistration, city);
        return this;
    }

    public RegistrationPage enterProvinceInRegistration(String province) {
        enterTextToElement(inputProvinceRegistration, province);
        return this;
    }

    public RegistrationPage enterZipCodeInRegistration(String zipCode) {
        enterTextToElement(inputZipCodeRegistration, zipCode);
        return this;
    }

    public RegistrationPage enterZipCodeInRegistration(Double zipCode) {
        enterTextToElement(inputZipCodeRegistration, zipCode);
        return this;
    }

    public void fillRequiredFieldsInRegisterFormAndSubmit(String title, String firstName, String lastName, String email,
                                          String password, Double contactTelephone, String houseNumberAndStreetName,
                                          String villageOrTownOrCity, String province, Double zipCode) {
        selectValueInDDTitle(title);
        enterFirstNameInRegistration(firstName);
        enterLastNameInRegistration(lastName);
        enterEmailInRegistration(email);
        enterPasswordInRegistration(password);
        enterContactTelephoneInRegistration(contactTelephone);
        enterHouseNumberAndStreetNameInRegistration(houseNumberAndStreetName);
        enterVillageOrTownOrCityInRegistration(villageOrTownOrCity);
        enterProvinceInRegistration(province);
        enterZipCodeInRegistration(zipCode);
        clickOnButtonRegister();
    }

    public void fillAllFieldsInRegisterFormAndSubmit(String title, String firstName, String lastName, String email,
                                                          String password, String contactTelephone, String apartmentOrFlat,
                                                          String houseNumberAndStreetName, String villageOrTownOrCity,
                                                          String province, String zipCode, String emailTick, String smsTick,
                                                          String saleEmailsTick) {
        selectValueInDDTitle(title);
        enterFirstNameInRegistration(firstName);
        enterLastNameInRegistration(lastName);
        enterEmailInRegistration(email);
        enterPasswordInRegistration(password);
        enterContactTelephoneInRegistration(contactTelephone);
        enterApartmentOrFlatInRegistration(apartmentOrFlat);
        enterHouseNumberAndStreetNameInRegistration(houseNumberAndStreetName);
        enterVillageOrTownOrCityInRegistration(villageOrTownOrCity);
        enterProvinceInRegistration(province);
        enterZipCodeInRegistration(zipCode);
        setCheckboxEmail(emailTick);
        setCheckboxSMS(smsTick);
        setCheckboxSaleEmails(saleEmailsTick);
        clickOnButtonRegister();
    }

    public AccountPage registrationWithInputtedData(String title, String firstName, String lastName, String email,
                                                    String password, String contactTelephone, String apartmentOrFlat,
                                                    String houseNumberAndStreetName, String villageOrTownOrCity,
                                                    String province, String zipCode, String emailTick, String smsTick,
                                                    String saleEmailsTick) {
        fillAllFieldsInRegisterFormAndSubmit(title, firstName, lastName, email, password, contactTelephone,
                apartmentOrFlat, houseNumberAndStreetName, villageOrTownOrCity, province, zipCode, emailTick,
                smsTick, saleEmailsTick);
        return new AccountPage(webDriver);
    }

    public AccountPage registrationWithDataFromExcel(String title, String firstName, String lastName, String email,
                                                 String password, Double contactTelephone, String houseNumberAndStreetName,
                                                 String villageOrTownOrCity, String province, Double zipCode) {
        fillRequiredFieldsInRegisterFormAndSubmit(title, firstName, lastName, email, password,
                contactTelephone, houseNumberAndStreetName, villageOrTownOrCity, province, zipCode);
        return new AccountPage(webDriver);
    }

    public RegistrationPage setCheckboxEmail(String value) {
//        new Actions(webDriver).moveToElement(checkboxEmail).perform();
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("document.getElementById('ChkByEmail').focus();");
        setCheckBoxValue(checkboxEmail, value);
        return this;
    }

    public RegistrationPage setCheckboxSaleEmails(String value) {
        setCheckBoxValue(checkboxSaleEmails, value);
        return this;
    }

    public RegistrationPage setCheckboxSMS(String value) {
        setCheckBoxValue(checkboxSMS, value);
        return this;
    }

//    public boolean isAlertExistingCustomerDisplayed(){
//        return isElementPresent(alertExistingCustomer);
//    }

    public ExistingCustomersPage registrationWithExistingEmail(String email) {
        enterEmailInRegistration(email);
        clickOnFieldPasswordInRegistration();
        return new ExistingCustomersPage(webDriver);
    }
}
