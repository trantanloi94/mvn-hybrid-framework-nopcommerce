package actions.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected final Log log;

    protected BaseTest(){
        log= LogFactory.getLog(getClass());
    }

    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        if (browserList == BrowserList.CHROME) {
            //System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserList == BrowserList.HEADLESS_CHROME) {
            //System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserList == BrowserList.FIREFOX) {
            //System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserList == BrowserList.HEADLESS_FIREFOX) {
            //System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else if (browserList == BrowserList.EDGE) {
            //System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid browser name");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //driver.get("https://demo.nopcommerce.com/");

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        if (browserList == BrowserList.CHROME) {
            //System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserList == BrowserList.HEADLESS_CHROME) {
            //System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserList == BrowserList.FIREFOX) {
            //System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserList == BrowserList.HEADLESS_FIREFOX) {
            //System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else if (browserList == BrowserList.EDGE) {
            //System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid browser name");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(appUrl);
        return driver;
    }

    //Get driver for ReportNG screenshot
    public WebDriver getDriverInstance(){
        return this.driver;
    }

    protected int randomNumber() {
        Random ran = new Random();
        return ran.nextInt(99999);
    }

    //Assert - verify
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;
            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (driver != null) {
                //IE
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
