package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class User_05_Dynamic_Locator extends BaseTest {
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
    public void TC_01_Login_With_Valid_Account() {

        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        userCustomerInfoPage = userHomePage.openMyAccountPage();
    }

    @Test
    public void TC_02_Switch_Page() {
        userCustomerInfoPage = userHomePage.openMyAccountPage();

        userAddressPage = userCustomerInfoPage.openAddressPage(driver);

        userMyProductReviewsPage = userAddressPage.openMyProductReviewPage(driver);

        userCustomerInfoPage = userMyProductReviewsPage.openCustomerInfoPageObject(driver);
        //Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void TC_03_Dynamic_Page_01() {

        userAddressPage = (UserAddressPageObject) userMyProductReviewsPage.openSubPageOnMyAccountPage(driver, "Addresses");

        userCustomerInfoPage = (UserCustomerInfoPageObject) userAddressPage.openSubPageOnMyAccountPage(driver, "Customer info");

        userMyProductReviewsPage = (UserMyProductReviewPageObject) userCustomerInfoPage.openSubPageOnMyAccountPage(driver, "My product reviews");

        //Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void TC_03_Dynamic_Page_02() {
        userMyProductReviewsPage.openSubPageOnMyAccountPageSecondWay(driver, "Addresses");
        userAddressPage = PageGeneratorManager.openAddressPage(driver);

        userAddressPage.openSubPageOnMyAccountPageSecondWay(driver, "My product reviews");
        userMyProductReviewsPage = PageGeneratorManager.openMyProductReviewPage(driver);

        userMyProductReviewsPage.openSubPageOnMyAccountPageSecondWay(driver, "Customer info");
        userCustomerInfoPage = PageGeneratorManager.openCustomerInfoPage(driver);
    }

}
