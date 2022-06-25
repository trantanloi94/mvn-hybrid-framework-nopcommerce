package actions.commons;

import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.UserAddressPageObject;
import actions.pageObjects.nopcommerce.user.UserCustomerInfoPageObject;
import actions.pageObjects.nopcommerce.user.UserHomePageObject;
import actions.pageObjects.nopcommerce.user.UserMyProductReviewPageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import jquery.uploadfile.BasePageJQueryUI;
import nopcommerce.user.BasePageUI;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    /*SELENIUM WEB BROWSER FUNCTION*/

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    protected void sendkeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    protected void switchToWindowByID(WebDriver driver, String windowID) {
        //lấy ra ID của tất cả các tab/window đang có
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
        //lấy ra ID của tất cả các tab/window đang có
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            //Switch to tab trước rồi kiểm tra sau
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    protected void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    /*SELENIUM WEB ELEMENT FUNCTION*/

    private By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    private WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(getByXpath(xpathLocator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(getByXpath(xpathLocator));
    }

    private String getDynamicLocator(String xpathLocator, String... dynamicValues) {
        xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
        return xpathLocator;
    }

    protected void clickToElement(WebDriver driver, String xpathLocator) {
        getWebElement(driver, xpathLocator).click();
    }

    protected void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
        getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).click();
    }

    protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = getWebElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }

    protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        select.selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)));
        select.selectByVisibleText(textItem);
    }

    protected String getSelectedItemOfDefaultDropdown(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdownList(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem) {
        //click on dropdown
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        //Wait list of elements load xong
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                //Scroll down if the item is in the bottom
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                //click on item
                item.click();
                break;
            }
        }
    }

    protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
        return getWebElement(driver, xpathLocator).getAttribute(attributeName);
    }

    protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
        return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).getText();
    }

    protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
        return getWebElement(driver, xpathLocator).getCssValue(propertyName);
    }

    protected String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementSize(WebDriver driver, String xpathLocator) {
        return getListWebElement(driver, xpathLocator).size();
    }

    protected int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
        return getListWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).size();
    }

    protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isDisplayed();
    }

    protected boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
        return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).isDisplayed();
    }

    protected boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, xpathLocator);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    protected void overrideImplicitTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isSelected();
    }

    protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getWebElement(driver, xpathLocator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpathLocator)).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)), key).perform();
    }

    //Javascript Executor
    protected void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void highlightElement(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, xpathLocator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
    }

    protected void scrollToElement(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
    }

    protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    //WAIT
    protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    protected void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
    }

    protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String xpathLocator, String dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
    }

    /*
     * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
     */
    protected void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
        overrideImplicitTimeout(driver, longTimeout);
    }

    protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
    }

    protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }

    protected void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
    }


    //My Account Menu
    public UserAddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.openAddressPage(driver);
    }

    public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        return PageGeneratorManager.openMyProductReviewPage(driver);
    }

    public UserCustomerInfoPageObject openCustomerInfoPageObject(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
        return PageGeneratorManager.openCustomerInfoPage(driver);
    }

    //Dynamic locator
    public BasePage openSubPageOnMyAccountPage(WebDriver driver, String pageName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_SUBPAGE_ON_MY_ACCOUNT_PAGE, pageName);
        clickToElement(driver, BasePageUI.DYNAMIC_SUBPAGE_ON_MY_ACCOUNT_PAGE, pageName);
        switch (pageName) {
            case "Customer info":
                return PageGeneratorManager.openCustomerInfoPage(driver);
            case "Addresses":
                return PageGeneratorManager.openAddressPage(driver);
            case "My product reviews":
                return PageGeneratorManager.openMyProductReviewPage(driver);
            default:
                throw new RuntimeException("Invalid subpage name");
        }
    }

    public void openSubPageOnMyAccountPageSecondWay(WebDriver driver, String pageName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_SUBPAGE_ON_MY_ACCOUNT_PAGE, pageName);
        clickToElement(driver, BasePageUI.DYNAMIC_SUBPAGE_ON_MY_ACCOUNT_PAGE, pageName);
    }

    //Log out
    public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
        clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
        clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    //Pattern Object
    public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    public void selectDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
        selectItemInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
    }

    public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
        checkToDefaultCheckboxOrRadio(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
        checkToDefaultCheckboxOrRadio(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
    }

    public String getTextboxValueByID(WebDriver driver, String textboxID) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
    }

}
