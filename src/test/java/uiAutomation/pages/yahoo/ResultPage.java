package uiAutomation.pages.yahoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultPage {

    By resultsList = By.id("results");
    By compPagination = By.className("compPagination");

    private WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void printOutAmountOfResult() {
        WebElement resultsListEl = driver.findElement(resultsList);
        WebElement compPaginationEL = resultsListEl.findElement(compPagination);
        WebElement results = compPaginationEL.findElement(By.tagName("span"));
        String resultsValue = results.getText();
        System.out.println(resultsValue);
    }
}
