package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class User_03_SwitchPage extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, correctPassword, validEmailAddress;

    // Declare + Init
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserAddressPageObject addressPage;
    private UserMyProductReviewPageObject myProductReviewsPage;

    public User_03_SwitchPage() {
    }

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);

        firstName = "First Name";
        lastName = "Last Name";
        correctPassword = "123456";
        validEmailAddress = "ltt" + randomNumber() + "@gmail.com";

        //Register successfully
        registerPage = homePage.openRegisterPage();

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(validEmailAddress);
        registerPage.inputToPasswordTextbox(correctPassword);
        registerPage.inputToConfirmPasswordTextbox(correctPassword);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickToLogoutLinkAtUserPage(driver);
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
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(validEmailAddress);
        loginPage.inputToPasswordTextbox(correctPassword);

        homePage = loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void TC_02_Switch_Page() {
        customerInfoPage = homePage.openMyAccountPage();

        addressPage = customerInfoPage.openAddressPage(driver);

        myProductReviewsPage = addressPage.openMyProductReviewPage(driver);

        customerInfoPage = myProductReviewsPage.openCustomerInfoPageObject(driver);
        //Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

}
