package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class User_09_Extent_Report_V5 extends BaseTest {
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


    @Test
    public void TC_01_Register(Method method) {

        //Register successfully
        ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");

        userRegisterPage = userHomePage.openRegisterPage();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
        userRegisterPage.inputToFirstNameTextbox(firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
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
        //Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");
        verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserPage(driver);


    }
    @Test
    public void TC_02_Login(Method method) {

        ExtentTestManager.startTest(method.getName(), "Login to system successfully");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
        userLoginPage = userHomePage.openLoginPage();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email/Password textbox");
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Verify 'My Account' link is displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        //verifyFalse(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.openMyAccountPage();


    }
}
