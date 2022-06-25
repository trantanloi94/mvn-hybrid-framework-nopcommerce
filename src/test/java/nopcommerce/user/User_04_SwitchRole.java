package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.UserCustomerInfoPageObject;
import actions.pageObjects.nopcommerce.user.UserHomePageObject;
import actions.pageObjects.nopcommerce.user.UserLoginPageObject;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class User_04_SwitchRole extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, userEmailAddress, userPassword, adminEmailAddress, adminPassword;

    // Declare + Init
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

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

        //Register successfully
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

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        Random ran = new Random();
        return ran.nextInt(99999);
    }

    @Test
    public void Role_01_User_To_Admin() {

//        userHomePage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
//        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.openMyAccountPage();

        userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

        userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

    }

    @Test
    public void Role_02_Admin_To_User() {
        adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        userLoginPage = userHomePage.openLoginPage();

        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

}
