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
import nopcommerce.common.RegisterANewAccount;

public class User_10_Share_Data extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, userEmailAddress, userPassword;

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

        userEmailAddress = RegisterANewAccount.userEmailAddress;
        userPassword = RegisterANewAccount.userPassword;

        userLoginPage = userHomePage.openLoginPage();

        userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        //userCustomerInfoPage = userHomePage.openMyAccountPage();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01() {
        Assert.assertTrue(true);
    }

    @Test
    public void TC_02() {
        Assert.assertTrue(true);
    }
}
