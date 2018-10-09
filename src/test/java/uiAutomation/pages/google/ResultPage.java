package uiAutomation.pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {

    public static By resultStats = By.id("resultStats");

    private WebDriver driver;

    public ResultPage(WebDriver driver){
        this.driver = driver;
    }

    public void printOutAmountOfResult() {
        String res = driver.findElement(resultStats).getText();
        System.out.println(res);
    }
}
