package actions.commons;

import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObject;
import actions.pageObjects.nopcommerce.user.*;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static UserLoginPageObject getLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage(WebDriver driver){
        return new UserRegisterPageObject(driver);
    }

    public static UserHomePageObject getUserHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }

    public static UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver){
        return new UserCustomerInfoPageObject(driver);
    }

    public static UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
        return new UserMyProductReviewPageObject(driver);
    }

    public static UserAddressPageObject openAddressPage(WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    //Admin page (BO)
    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver){
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }

}
