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

public class User_13_Pattern_Object extends BaseTest {
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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }


    @Test
    public void TC_01_Register(Method method) {

        //Register successfully
        ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");

        userRegisterPage = userHomePage.openRegisterPage();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        userRegisterPage.clickToRadioButtonByLabel(driver, "Female");

        userRegisterPage.selectDropdownByName(driver, "DateOfBirthDay", "10");
        userRegisterPage.selectDropdownByName(driver, "DateOfBirthMonth", "January");
        userRegisterPage.selectDropdownByName(driver, "DateOfBirthYear", "1994");

        userRegisterPage.clickToCheckboxByLabel(driver, "Newsletter");

        log.info("Register- Step 04: Enter Email is '" + userEmailAddress + "'");
        userRegisterPage.inputToTextboxByID(driver, "Email", userEmailAddress);

        log.info("Register- Step 05: Enter Password is '" + userPassword + "'");
        userRegisterPage.inputToTextboxByID(driver, "Password", userPassword);

        log.info("Register- Step 06: Enter confirm password is '" + userPassword + "'");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", userPassword);

        log.info("Register- Step 07: Click Register button");
        userRegisterPage.clickToButtonByText(driver, "Register");


        log.info("Register- Step 08: Verify that the success message is displayed");
        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserPage(driver);


    }

    @Test
    public void TC_02_Login(Method method) {

        ExtentTestManager.startTest(method.getName(), "Login to system successfully");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
        userLoginPage = userHomePage.openLoginPage();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email/Password textbox");
        //userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
        userLoginPage.inputToTextboxByID(driver, "Email", userEmailAddress);
        userLoginPage.inputToTextboxByID(driver, "Password", userPassword);
        userLoginPage.clickToButtonByText(driver,"Log in");

        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Verify 'My Account' link is displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void TC_03_My_Account(Method method) {
        userCustomerInfoPage = userHomePage.openMyAccountPage();
        Assert.assertEquals(userCustomerInfoPage.getTextboxValueByID(driver,"FirstName"), firstName);
        Assert.assertEquals(userCustomerInfoPage.getTextboxValueByID(driver,"LastName"), lastName);
        Assert.assertEquals(userCustomerInfoPage.getTextboxValueByID(driver,"Email"), userEmailAddress);

    }
}

