package actions.pageObjects.jquery.datatable;

import actions.commons.BasePage;
import jquery.datatable.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPaginationByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, pageNumber);
    }

    public boolean isActivePageByPageNumber(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.ACTIVE_PAGE_BY_PAGE_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.ACTIVE_PAGE_BY_PAGE_NUMBER, pageNumber);
    }

    public void enterToSearchTextBoxByLabel(String headerLabel, String value) {
        waitForElementVisible(driver, HomePageUI.SEARCH_TEXTBOX, headerLabel);
        sendkeyToElement(driver, HomePageUI.SEARCH_TEXTBOX, value, headerLabel);
        pressKeyToElement(driver, HomePageUI.SEARCH_TEXTBOX, Keys.ENTER, headerLabel);
    }

    public List<String> getRowValuesAllPage() {
        int totalPagNumber = getElementSize(driver, HomePageUI.TOTAL_PAGENUMBER);

        List<String> allRowValuesOfAllPage = new ArrayList<String>();

        for (int i = 1; i <= totalPagNumber; i++) {
            clickToElement(driver, HomePageUI.PAGINATION_BY_INDEX, String.valueOf(i));

            //Get text moi row cua page dua vao array list
            List<WebElement> rowValuesEachPage = getListWebElement(driver, HomePageUI.ROW_EACH_PAGE);
            for (WebElement eachRow : rowValuesEachPage) {
                allRowValuesOfAllPage.add(eachRow.getText());
            }
        }

        //In ra values get duoc
        for (String value : allRowValuesOfAllPage) {
            System.out.println(value);
        }
        return allRowValuesOfAllPage;
    }

    public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
        //column index by column name
        int columIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        //Sendkey to column number?
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
        sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowNumber, String.valueOf(columIndex));
    }

    public void selectDropdownByColumnNameAtRowNumber(String columnName, String rowNumber, String valueToSelect) {
        int columIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
        selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToSelect, rowNumber, String.valueOf(columIndex));
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver,HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        //column index by column name
        int columIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        //Sendkey to column number?
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
        checkToDefaultCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
    }

    public void uncheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        //column index by column name
        int columIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        //Sendkey to column number?
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
        uncheckToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columIndex));
    }

    public void clikToIconByNameandRownumber(String rowNumber, String iconName) {
        waitForElementClickable(driver,HomePageUI.ICON_BY_NAME_AND_ROW_INDEX, rowNumber, iconName);
        clickToElement(driver, HomePageUI.ICON_BY_NAME_AND_ROW_INDEX, rowNumber, iconName);
    }
}
