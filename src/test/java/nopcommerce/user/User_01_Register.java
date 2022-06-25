package nopcommerce.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class User_01_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    String emailAddress;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        emailAddress = "ltt" + randomNumber() + "@gmail.com";

        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("First Name");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("!#$%^&*(");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12346");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Wrong email");

    }

    @Test
    public void TC_03_Register_Success(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("First Name");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

        driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

    }

    @Test
    public void TC_04_Register_Existing_Email(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("First Name");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']//li")).getText(), "The specified email already exists");

    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("First Name");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123");

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password must meet the following rules:\n" + "must have at least 6 characters");


    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password(){
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("First Name");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("111111");

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "The password and confirmation password do not match.");


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
