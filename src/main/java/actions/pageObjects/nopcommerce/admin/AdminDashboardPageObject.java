package actions.pageObjects.nopcommerce.admin;

import actions.commons.BasePage;
import nopcommerce.admin.AdminDashboardPageUI;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPageObject extends BasePage {
    private WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplayed() {
        waitForElementVisible(driver, AdminDashboardPageUI.DAshBOARD_HEADER);
        return isElementDisplayed(driver, AdminDashboardPageUI.DAshBOARD_HEADER);
    }
}
