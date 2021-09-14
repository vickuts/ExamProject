package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static org.hamcrest.CoreMatchers.containsString;

public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15, webDriverWait30;
    String value;
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected final String BASE_URL = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver)),
                this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DEFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
        webDriverWait30 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH());
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page ",
                BASE_URL + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page",
                webDriver.getCurrentUrl(),
                containsString(BASE_URL.substring(12, 22) + getRelativeUrl()));
    }

    public void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted to element " + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    public void enterTextToElement(WebElement webElement, Double text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(String.valueOf(text.intValue()));
            logger.info("'" + String.valueOf(text.intValue()) + "' was inputted to element " + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = "'" + ((TypifiedElement) webElement).getName() + "'";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
//            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElementWithWait(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isInputtedTextDisplayed(WebElement webElement, String text) {
        try {
            boolean state = webElement.getText().equalsIgnoreCase(text);
            if (state) {
                logger.info(text + " is displayed in " + getElementName(webElement));
            } else {
                logger.info(text + " is not displayed in " + getElementName(webElement));
            }
            return state;
        } catch (Exception e) {
            logger.info(text + " is not displayed in " + getElementName(webElement));
            return false;
        }
    }

    public boolean isWebElementFieldEmpty(WebElement webElement) {
        try {
            boolean state = webElement.getText().isEmpty();
            if (state){
                logger.info(getElementName(webElement) + " field is empty");
            } else {
                logger.info(getElementName(webElement) + " field is not empty");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + " field is not empty");
            return false;
        }
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " is present");
            } else {
                logger.info(getElementName(webElement) + " is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + " is not present");
            return false;
        }
    }

    protected void selectValueInDD(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void setCheckBoxValue(WebElement checkBox, String value) {
        try {
//            webDriverWait30.until(ExpectedConditions.elementToBeClickable(checkBox));
            switch (value.toLowerCase()) {
                case "check":
                    if (checkBox.isSelected()) {
                        logger.info("'" + checkBox + "' is already marked");
                    } else {
                        clickOnElementUsingJavascript(checkBox);
                        logger.info("'" + checkBox + "' was marked");
                    }
                    break;
                case "uncheck":
                    if (checkBox.isSelected()) {
                        clickOnElementUsingJavascript(checkBox);
                        logger.info("'" + checkBox + "' was unmarked");
                    } else {
                        logger.info("'" + checkBox + "' is already unmarked");
                    }
                    break;
                default:
                    logger.info("Incorrect '" + value + "' value was indicated for '" + checkBox + "'. " +
                            "Valid values are 'check' or 'uncheck'");
                    break;
            }
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

//    public void userOpensNewTab() {
//        ((JavascriptExecutor)webDriver).executeScript("window.open()");
//        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
//        webDriver.switchTo().window(tabs.get(1));
//    }

    public void clickOnElementUsingJavascript(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", webElement);
    }
//    WebElement element = driver.findElement(By.id("gbqfd"));
//    JavascriptExecutor executor = (JavascriptExecutor)driver;
//    executor.executeScript("arguments[0].click();", element);

//    JavascriptExecutor jse = (JavascriptExecutor)driver;
//    jse.executeScript("document.getElementById('gbqfb').click();");
}
