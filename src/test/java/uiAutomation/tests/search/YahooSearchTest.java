package uiAutomation.tests.search;

import org.testng.annotations.Test;
import uiAutomation.BaseTest;
import uiAutomation.pages.yahoo.MainPage;
import uiAutomation.pages.yahoo.ResultPage;

public class YahooSearchTest extends BaseTest {

    @Test
    public void testSearch() throws Exception {
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = new ResultPage(driver);

        String query = "Portnov";

        mainPage.open();
        mainPage.typeRequestInSearchInput(query);
        mainPage.submitSearch();
        resultPage.printOutAmountOfResult();
    }

}
