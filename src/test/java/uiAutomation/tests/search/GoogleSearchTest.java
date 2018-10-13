package uiAutomation.tests.search;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiAutomation.BaseTest;
import uiAutomation.pages.google.MainPage;
import uiAutomation.pages.google.ResultPage;


public class GoogleSearchTest extends BaseTest {

    @Test
    public void test001() {
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = new ResultPage(driver);

        String queryString = "query";
        mainPage.open();

        mainPage.typeRequestInSearchInput(queryString);
        mainPage.submitSearch();

        resultPage.printOutAmountOfResult();

        String actualPageTitle = resultPage.getPageTitle();
        String expectedPageTitle = "query - Google Search";

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }
}