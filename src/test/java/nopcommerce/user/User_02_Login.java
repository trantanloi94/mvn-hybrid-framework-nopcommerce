package nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
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

public class User_02_Login extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, correctPassword, incorrectPassword, validEmailAddress, invalidEmailAddress, incorrectEmailAddress;

    // Declare + Init
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {

        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);

        firstName = "First Name";
        lastName = "Last Name";
        correctPassword = "123456";
        validEmailAddress = "ltt" + randomNumber() + "@gmail.com";

        incorrectPassword = "111111";
        invalidEmailAddress = "#$%&&&&mjg";
        incorrectEmailAddress = "ltt_test" + randomNumber() + "@gmail.com";

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
    public void TC_01_Login_With_Empty_Data() {
        loginPage = homePage.openLoginPage();

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(invalidEmailAddress);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
    }

    @Test
    public void TC_03_Login_With_Incorrect_Email() {
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(incorrectEmailAddress);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getLoginWasUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
    }

    @Test
    public void TC_04_Login_Without_Password() {
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(validEmailAddress);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getLoginWasUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_05_Login_With_Incorrect_Password() {
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(validEmailAddress);
        loginPage.inputToPasswordTextbox(incorrectPassword);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getLoginWasUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_06_Login_With_Valid_Account() {
        loginPage = homePage.openLoginPage();

        loginPage.inputToEmailTextbox(validEmailAddress);
        loginPage.inputToPasswordTextbox(correctPassword);

        homePage = loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

}
