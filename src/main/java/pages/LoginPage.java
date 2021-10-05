package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//*[text()='Register Now']")
    private Button buttonRegisterNow;
    @FindBy(id = "SignInNow")
    private Button buttonSignIn;
    @FindBy(id = "EmailOrAccountNumber")
    private TextInput inputEmail;
    @FindBy(id = "Password")
    private TextInput inputPassword;
    @FindBy(xpath = ".//*[text()='Sorry, we have been unable to sign you in.']")
    private TextBlock alertError;
    @FindBy(xpath = ".//*[text()='Forgotten Password']")
    private Link linkForgottenPassword;
    @FindBy(id = "dialogWindowContent")
    private Form windowModal;
    @FindBy(id = "dialogWindowHeader")
    private TextBlock headerInWindowModal;
    @FindBy(id = "dialogWindowBody")
    private TextBlock bodyInWindowModal;
    @FindBy(id = "resetPassword")
    private Button buttonReset;
    @FindBy(xpath = ".//*[text()='Close']")
    private Button buttonClose;
    @FindBy(xpath = ".//form[contains(@action,'ForgottenPassword')]//a")
    private Link linkEdit;
    @FindBy(xpath = ".//*[text()='If you have an account with us, you will shortly receive an email explaining how to reset your password.']")
    private TextBlock alertReset;
    @FindBy(xpath = ".//h3[text()='Our Social Networks']")
    private TextBlock titleOurSocialNetworks;
    @FindBy(xpath = ".//*[@aria-label='facebook']")
    private Image labelFacebook;
    @FindBy(xpath = ".//*[@aria-label='twitter']")
    private Image labelTwitter;
    @FindBy(xpath = ".//*[@aria-label='instagram']")
    private Image labelInstagram;
    @FindBy(xpath = ".//*[@aria-label='pinterest']")
    private Image labelPinterest;
    @FindBy(xpath = ".//*[@aria-label='youtube']")
    private Image labelYoutube;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/Login";
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get(BASE_URL.replace("www","account") + getRelativeUrl());
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with Login page" + e);
            Assert.fail("Can not work with Login page");
        }
        return this;
    }

    public LoginPage checkIsRedirectionOnLoginPage(){
        checkUrlWithPattern();
        checkIsButtonSignInVisible();
        return this;
    }

    public boolean isButtonSignInPresent(){
        return isElementPresent(buttonSignIn);
    }

    public LoginPage checkIsButtonSignInVisible(){
        Assert.assertTrue("Button Sign In is not displayed", isButtonSignInPresent());
        return this;
    }

    public RegistrationPage clickOnButtonRegisterNow(){
        clickOnElement(buttonRegisterNow);
        return new RegistrationPage(webDriver);
    }

    public LoginPage enterEmailInSignIn(String email) {
        enterTextToElement(inputEmail, email);
        return this;
    }

    public LoginPage enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
        return this;
    }

    public AccountPage clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
        return new AccountPage(webDriver);
    }

    public boolean isAlertErrorPresent() {
        return isElementPresent(alertError);
    }

    public LoginPage clickOnLinkForgottenPassword() {
        clickOnElement(linkForgottenPassword);
        return this;
    }

    public boolean isWindowModalPresent() {
        return isElementPresent(windowModal);
    }

    public boolean isHeaderInWindowModalPreset() {
        return isElementPresent(headerInWindowModal);
    }

    public boolean isBodyInWindowModalPreset() {
        return isElementPresent(bodyInWindowModal);
    }

    public boolean isButtonResetPreset() {
        return isElementPresent(buttonReset);
    }

    public boolean isButtonClosePreset() {
        return isElementPresent(buttonClose);
    }

    private boolean isLinkEditPresent() {
        webDriver.switchTo().frame("iFrameModalWindow");
        return isElementPresent(linkEdit);
    }

    private boolean isInputtedEmailDisplayed(String email) {
        return isInputtedTextDisplayed(inputEmail, email);
    }

    public LoginPage checkWindowModal(String email) {
        isWindowModalPresent();
        isHeaderInWindowModalPreset();
        isBodyInWindowModalPreset();
        isButtonClosePreset();
        isButtonResetPreset();
        isInputtedEmailDisplayed(email);
        isLinkEditPresent();
        return this;
    }

    public LoginPage clickOnLinkEdit() {
        clickOnElementWithWait(linkEdit);
        return this;
    }

    public LoginPage checkIsEmailAddressOrCustomerNumberFieldEmpty() {
        isWebElementFieldEmpty(inputEmail);
        return this;
    }

    public LoginPage checkPlaceholderForEmailAddressOrCustomerNumberField() {
        isPlaceholderDisplayed(inputEmail, "Email Address or Customer Number");
        return this;
    }

    public LoginPage clickOnButtonReset() {
        clickOnElement(buttonReset);
        return this;
    }

    public LoginPage checkIsResetAlertDisplayed() {
        isElementPresent(alertReset);
        return this;
    }

    public LoginPage closeOpenedTab(){
        closeActiveTabAndSwitchToTabByNumber(0);
        return this;
    }

    public FacebookPage clickOnFacebookLabel() {
        scrollToElement(titleOurSocialNetworks);
        clickOnElement(labelFacebook);
        return new FacebookPage(webDriver);
    }

    public TwitterPage clickOnTwitterLabel() {
        clickOnElement(labelTwitter);
        return new TwitterPage(webDriver);
    }

    public InstagramPage clickOnInstagramLabel() {
        clickOnElement(labelInstagram);
        return new InstagramPage(webDriver);
    }

    public PinterestPage clickOnPinterestLabel() {
        clickOnElement(labelPinterest);
        return new PinterestPage(webDriver);
    }

    public YoutubePage clickOnYoutubeLabel() {
        clickOnElement(labelYoutube);
        return new YoutubePage(webDriver);
    }
}
