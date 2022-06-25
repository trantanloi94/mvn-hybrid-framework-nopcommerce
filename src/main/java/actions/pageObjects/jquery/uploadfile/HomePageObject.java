package actions.pageObjects.jquery.uploadfile;

import actions.commons.BasePage;
import jquery.uploadfile.HomePageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
        for (WebElement startButton :
                startButtons) {
            startButton.click();
            sleepInSecond(2);
        }
    }

    public boolean isFileUploaded(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED, fileName);
    }

    public boolean isFileUploadedAsImage(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED, fileName);
        return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_AS_IMAGE, fileName);
    }
}
