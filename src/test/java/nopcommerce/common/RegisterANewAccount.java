package nopcommerce.common;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.user.UserHomePageObject;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class RegisterANewAccount extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName;
    public static String userEmailAddress, userPassword;

    // Declare + Init
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;


    @Parameters({"browser"})
    @BeforeTest
    public void Register_A_New_Account(String browserName) {

        driver = getBrowserDriver(browserName);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);

        firstName = "First Name";
        lastName = "Last Name";
        userEmailAddress = "ltt" + randomNumber() + "@gmail.com";
        userPassword = "123456";

        userRegisterPage = userHomePage.openRegisterPage();

        userRegisterPage.inputToFirstNameTextbox(firstName);
        userRegisterPage.inputToLastNameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(userEmailAddress);
        userRegisterPage.inputToPasswordTextbox(userPassword);
        userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserPage(driver);
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
