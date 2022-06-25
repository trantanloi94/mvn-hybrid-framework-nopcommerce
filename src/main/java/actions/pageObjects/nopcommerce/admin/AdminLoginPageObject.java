package actions.pageObjects.nopcommerce.admin;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import nopcommerce.admin.AdminLoginPageUI;
import org.openqa.selenium.WebDriver;

public class AdminLoginPageObject extends BasePage {
    private WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
        inputToEmailTextbox(emailAddress);
        inputToPasswordTextbox(password);
        return clickToLoginButton();
    }

    private AdminDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getDashboardPage(driver);
    }

    private void inputToPasswordTextbox(String password) {
        waitForElementClickable(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    private void inputToEmailTextbox(String emailAddress) {
        waitForElementClickable(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }
}
