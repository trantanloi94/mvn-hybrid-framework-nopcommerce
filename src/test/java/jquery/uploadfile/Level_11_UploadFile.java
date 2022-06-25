package jquery.uploadfile;

import actions.commons.BaseTest;
import actions.pageObjects.jquery.uploadfile.HomePageObject;
import actions.pageObjects.jquery.uploadfile.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_11_UploadFile extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    String firstFileName = "image01.jpg";
    String secondFileName = "image02.jpg";
    String thirdFileName = "image03.jpg";

    String[] multipleFileNames = {firstFileName, secondFileName, thirdFileName};

    @Parameters({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }


    //@Test
    public void Upload_File_01_One_File_PerTime() {
        homePage.uploadMultipleFiles(driver, firstFileName);
        Assert.assertTrue(homePage.isFileLoadedByName(firstFileName));

        homePage.clickToStartButton();
        Assert.assertTrue(homePage.isFileUploaded(firstFileName));

        Assert.assertTrue(homePage.isFileUploadedAsImage(firstFileName));

    }

    @Test
    public void Upload_File_02_Multiple_Files_PerTime() {
        homePage.uploadMultipleFiles(driver, multipleFileNames);
        Assert.assertTrue(homePage.isFileLoadedByName(firstFileName));
        Assert.assertTrue(homePage.isFileLoadedByName(secondFileName));
        Assert.assertTrue(homePage.isFileLoadedByName(thirdFileName));

        homePage.clickToStartButton();
        Assert.assertTrue(homePage.isFileUploaded(firstFileName));
        Assert.assertTrue(homePage.isFileUploaded(secondFileName));
        Assert.assertTrue(homePage.isFileUploaded(thirdFileName));

        Assert.assertTrue(homePage.isFileUploadedAsImage(firstFileName));
        Assert.assertTrue(homePage.isFileUploadedAsImage(secondFileName));
        Assert.assertTrue(homePage.isFileUploadedAsImage(thirdFileName));

    }

}
