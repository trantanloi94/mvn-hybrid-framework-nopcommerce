package actions.commons;

import java.io.File;

public class GlobalConstants {
    public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final int LONG_TIMEOUT = 20;
    public static final int SHORT_TIMEOUT = 5;

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;

    public static final String JAVA_VERSION = System.getProperty("java.version");

}
