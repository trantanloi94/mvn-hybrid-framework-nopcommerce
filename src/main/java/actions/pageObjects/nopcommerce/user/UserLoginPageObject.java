package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import nopcommerce.user.UserHomePageUI;
import nopcommerce.user.UserLoginPageUI;
import org.openqa.selenium.WebDriver;

public class UserLoginPageObject extends BasePage {

    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementClickable(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementClickable(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public UserHomePageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public UserHomePageObject loginAsUser(String emailAddress, String password){
        inputToEmailTextbox(emailAddress);
        inputToPasswordTextbox(password);
        return clickToLoginButton();
    }

    public String getLoginWasUnsuccessfulErrorMessage() {
        waitForElementVisible(driver, UserLoginPageUI.LOGIN_UNSUCCESSFUL_MESSAGE);
        return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESSFUL_MESSAGE);
    }

}
