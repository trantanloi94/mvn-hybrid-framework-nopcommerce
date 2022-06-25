package nopcommerce.user;

public class BasePageUI {
    public static final String ADDRESS_LINK = "//div[@class='master-wrapper-content']//a[text()='Addresses']";
    public static final String MY_PRODUCT_REVIEWS_LINK = "//div[@class='master-wrapper-content']//a[text()='My product reviews']";
    public static final String CUSTOMER_INFO_LINK = "//div[@class='master-wrapper-content']//a[text()='Customer info']";

    public static final String DYNAMIC_SUBPAGE_ON_MY_ACCOUNT_PAGE = "//div[@class='master-wrapper-content']//a[text()='%s']";

    public static final String LOGOUT_LINK_AT_USER_PAGE = "//a[@class='ico-logout']";
    public static final String LOGOUT_LINK_AT_ADMIN_PAGE = "//a[text()='Logout']";

    //Pattern Object
    public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
    public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
    public static final String DYNAMIC_CHECKBOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::input";
}
