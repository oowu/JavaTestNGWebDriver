package uiAutomation.pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    public static By searchInput = By.xpath("//input[@class='gLFyf gsfi']");
    public static By submitButton = By.xpath("//input[@value='Google Search']");


    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void open() {
        String url = "http://google.com";
        driver.get(url);
    }

    public void typeRequestInSearchInput(String queryString) {
        driver.findElement(searchInput).sendKeys(queryString);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitSearch() {
        driver.findElement(submitButton).click();
    }
}