package jquery.datatable;

public class HomePageUI {
    public static final String PAGINATION_BY_PAGE_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String ACTIVE_PAGE_BY_PAGE_NUMBER = "//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String SEARCH_TEXTBOX = "//div[text()='%s']/parent::div//following-sibling::input";
    public static final String TOTAL_PAGENUMBER ="//li[@class='qgrd-pagination-page']" ;
    public static final String PAGINATION_BY_INDEX = "//li[@class='qgrd-pagination-page'][%s]/a";
    public static final String ROW_EACH_PAGE ="//tr" ;

    public static final String COLUMN_INDEX_BY_NAME = "//tr//td[text()='%s']/preceding-sibling::td";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]/input";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]//td[%s]/select";
    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]/input";
    public static final String LOAD_DATA_BUTTON = "//button[@id='btnLoad']";
    public static final String ICON_BY_NAME_AND_ROW_INDEX = "//tbody/tr[%s]//button[@title='%s']";
}
