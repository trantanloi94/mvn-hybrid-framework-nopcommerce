package nopcommerce.user;/*
package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class User_01_Register_Apply_BasePage_01 {
    WebDriver driver;

    //Declare
    BasePage basePage;
    // BasePage: Class
    // basePage: Object
    String projectPath = System.getProperty("user.dir");

    String emailAddress;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        //Initial
        basePage = new BasePage();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        emailAddress = "ltt" + randomNumber() + "@gmail.com";
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals( basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "First Name");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Last Name");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", "!#$%^&*(");
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Email-error']"), "Wrong email");

    }

    @Test
    public void TC_03_Register_Success(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "First Name");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Last Name");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"), "Your registration completed");

        basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
        basePage.clickToElement(driver, "//a[@class='ico-logout']");

    }

    @Test
    public void TC_04_Register_Existing_Email(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "First Name");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Last Name");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");

    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "First Name");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Last Name");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Password-error']"), "Password must meet the following rules:\n" + "must have at least 6 characters");

    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "First Name");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Last Name");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123123");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='ConfirmPassword-error']"),"The password and confirmation password do not match.");

    }


    public int randomNumber(){
        Random ran = new Random();
        return ran.nextInt(99999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
*/
