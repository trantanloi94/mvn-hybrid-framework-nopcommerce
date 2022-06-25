package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class User_07_Apply_Log4J extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, userEmailAddress, userPassword, adminEmailAddress, adminPassword;

    // Declare + Init
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private UserAddressPageObject userAddressPage;
    private UserMyProductReviewPageObject userMyProductReviewsPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {

        driver = getBrowserDriver(browserName);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);

        firstName = "First Name";
        lastName = "Last Name";
        userEmailAddress = "ltt" + randomNumber() + "@gmail.com";
        userPassword = "123456";

        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";



    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        Random ran = new Random();
        return ran.nextInt(99999);
    }

    @Test
    public void TC_01_Register_Login() {

        //Register successfully
        log.info("Register- Step 01: Navigate to Register page");
        userRegisterPage = userHomePage.openRegisterPage();

        log.info("Register- Step 02: Enter First Name is '" + firstName + "'");
        userRegisterPage.inputToFirstNameTextbox(firstName);

        log.info("Register- Step 03: Enter Last Name is '" + lastName + "'");
        userRegisterPage.inputToLastNameTextbox(lastName);

        log.info("Register- Step 04: Enter Email is '" + userEmailAddress + "'");
        userRegisterPage.inputToEmailTextbox(userEmailAddress);

        log.info("Register- Step 05: Enter Password is '" + userPassword + "'");
        userRegisterPage.inputToPasswordTextbox(userPassword);

        log.info("Register- Step 06: Enter confirm password is '" + userPassword + "'");
        userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

        log.info("Register- Step 07: Click Register button");
        userRegisterPage.clickToRegisterButton();

        //.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "");
        log.info("Register- Step 08: Verify that the success message is displayed");
        verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserPage(driver);

        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

        //Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        verifyTrue(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.openMyAccountPage();
    }
}
