package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserCustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver){
        this.driver = driver;
    }
}
