package nopcommerce.user;

import actions.commons.BaseTest;
import actions.pageObjects.nopcommerce.user.UserHomePageObject;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class User_01_Register_04_POM extends BaseTest {
    // Declare
    private WebDriver driver;
    private String firstName, lastName, password, emailAddress;

    // Declare + Init
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {

        driver = getBrowserDriver(browserName);

        homePage = new UserHomePageObject(driver);

        firstName = "First Name";
        lastName = "Last Name";
        password = "123456";
        emailAddress = "ltt" + randomNumber() + "@gmail.com";
    }

    @Test
    public void TC_01_Register_Empty_Data() {

        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {

        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox("test!$$%^^^&");
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

    }

    @Test
    public void TC_03_Register_Success() {
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLinkAtUserPage(driver);
        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");

    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("123");
        registerPage.inputToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\n" + "must have at least 6 characters");

    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObject(driver);

        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox("1111111");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");

    }

    public int randomNumber() {
        Random ran = new Random();
        return ran.nextInt(99999);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
