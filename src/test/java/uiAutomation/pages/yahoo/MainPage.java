package uiAutomation.pages.yahoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    By searchInput = By.id("uh-search-box");
    By submitButton = By.id("uh-search-button");

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void open() {
        driver.get("http://yahoo.com");
    }

    public void typeRequestInSearchInput(String query) {
        driver.findElement(searchInput).sendKeys(query);
    }

    public void submitSearch() {
        driver.findElement(submitButton).click();
    }
}
