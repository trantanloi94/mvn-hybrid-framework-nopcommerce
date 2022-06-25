package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import nopcommerce.user.UserRegisterPageUI;
import org.openqa.selenium.WebDriver;

public class UserRegisterPageObject extends BasePage {

    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton(){
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public void inputToFirstNameTextbox(String firstName){
        waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName){
        waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);
    }

    public void inputToEmailTextbox(String email){
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPasswordTextbox(String password){
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword){
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public String getErrorMessageAtFirstNameTextbox(){
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtLastNameTextbox(){
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtEmailTextbox(){
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getErrorMessageAtPasswordTextbox(){
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getErrorMessageAtConfirmPasswordTextbox(){
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public String getRegisterSuccessMessage(){
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getExistingEmailErrorMessage(){
        waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }



//    public UserHomePageObject clickToLogoutLink(){
//        waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK);
//        clickToElement(driver, UserBasePageUI.LOGOUT_LINK);
//        return PageGeneratorManager.getHomePage(driver);
//    }

}
