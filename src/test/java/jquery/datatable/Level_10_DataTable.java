package jquery.datatable;

import actions.commons.BaseTest;
import actions.pageObjects.jquery.datatable.HomePageObject;
import actions.pageObjects.jquery.datatable.PageGeneratorManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class Level_10_DataTable extends BaseTest {
    HomePageObject homePage;
    List<String> actualAllRowValues;
    List<String> expectedAllRowValues;

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

/*    @Test
    public void DataTable_01_Paging() {
        homePage.openPaginationByPageNumber("10");
        Assert.assertTrue(homePage.isActivePageByPageNumber("10"));

        homePage.openPaginationByPageNumber("12");
        Assert.assertTrue(homePage.isActivePageByPageNumber("12"));

        homePage.openPaginationByPageNumber("19");
        Assert.assertTrue(homePage.isActivePageByPageNumber("19"));

    }

    @Test
    public void DataTable_02_Searching() {
        homePage.enterToSearchTextBoxByLabel("Females", "624");
        homePage.enterToSearchTextBoxByLabel("Country", "Seychelles");
        homePage.enterToSearchTextBoxByLabel("Males", "651");
        homePage.enterToSearchTextBoxByLabel("Total", "1270");
    }

    @Test
    public void DataTable_03_GetRowValues() {
        actualAllRowValues = homePage.getRowValuesAllPage();
        //Assert.assertEquals(actualAllRowValues, expectedAllRowValues);
    }*/

    @Test
    public void DataTable_04_Action_At_Any_Row() {
        homePage.clickToLoadDataButton();

        homePage.enterToTextboxByColumnNameAtRowNumber("Album", "1", "test album");
        homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "2", "value");
        homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1234");
        homePage.enterToTextboxByColumnNameAtRowNumber("Price", "4", "11");

        homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Japan");
        homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Korea");
        homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "US");

        homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
        homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
        homePage.sleepInSecond(2);

        homePage.clikToIconByNameandRownumber("2", "Insert Row Above");
        homePage.sleepInSecond(2);
        homePage.clikToIconByNameandRownumber("1", "Move Down");
        homePage.sleepInSecond(2);
        homePage.clikToIconByNameandRownumber("3", "Move Up");
        homePage.sleepInSecond(2);
        homePage.clikToIconByNameandRownumber("5", "Remove Current Row");
        homePage.sleepInSecond(2);


    }

}
