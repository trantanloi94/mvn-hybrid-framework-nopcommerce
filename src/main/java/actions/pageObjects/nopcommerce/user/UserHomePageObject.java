package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import nopcommerce.user.UserHomePageUI;
import org.openqa.selenium.WebDriver;

public class UserHomePageObject extends BasePage {

    // Global
    private WebDriver driver;

    //.this --> lay driver global gan = driver local
    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObject openRegisterPage() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public UserLoginPageObject openLoginPage() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.My_ACCOUNT_LINK);
        return isElementDisplayed(driver, UserHomePageUI.My_ACCOUNT_LINK);
    }

    public UserCustomerInfoPageObject openMyAccountPage() {
        waitForElementClickable(driver, UserHomePageUI.My_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.My_ACCOUNT_LINK);
        return PageGeneratorManager.openCustomerInfoPage(driver);
    }

}
