package nopcommerce.common;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.user.UserHomePageObject;
import actions.pageObjects.nopcommerce.user.UserLoginPageObject;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

public class Cookie extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, userEmailAddress, userPassword;
    public static Set<org.openqa.selenium.Cookie> loggedInAsNormalUser;

    // Declare + Init
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;


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

        userLoginPage = userHomePage.openLoginPage();

        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

        loggedInAsNormalUser = userHomePage.getAllCookies(driver);

        for (org.openqa.selenium.Cookie cookie: Cookie.loggedInAsNormalUser){
            System.out.println("Cookie after get:" + cookie);
        }

        driver.quit();

    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
