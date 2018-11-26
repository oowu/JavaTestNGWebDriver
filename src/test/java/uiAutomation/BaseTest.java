package uiAutomation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass
    public void setup(){
        if(System.getProperty("os.name").startsWith("Windows")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\win64\\geckodriver.exe");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\win64\\chromedriver.exe");
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\win64\\MicrosoftWebDriver.exe");
        }
        if(System.getProperty("os.name").startsWith("Mac")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/macOS/geckodriver");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/macOS/chromedriver");
        }
    }

    @Parameters(value = "browser")
    @AfterMethod
    public void afterMethod(@Optional("FF") String browser, ITestResult result) {
        if(!result.isSuccess()){
            takeScreenshot(result);
        }

        driver.quit();
    }

    private void takeScreenshot(ITestResult result){
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String fileName = "/" + result.getInstanceName() + "/" + result.getName() + ".png";
            String filePath = System.getProperty("user.dir")
                    + "/screenshots/" + fileName.replace(" ", "");
            File destFile = new File(filePath);
            FileUtils.copyFile(screenshot,destFile);
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameters(value = "browser")
    @BeforeMethod
    public void openBrowser(@Optional("FF") String browser) {
        switch (browser){
            case "FF" : driver = new FirefoxDriver(); break;
            case "Chrome" : driver = new ChromeDriver(); break;
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void navigateToTheURL(String url) {
        driver.get(url);
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).
                        executeScript("return document.readyState").equals("complete");
            }
        };

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void clickOntheLink(String text) {
        WebElement element = driver.findElement(By.linkText(text));
        element.click();
    }

    public static void sleepFor(int i) {
        //try catch block Java
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void switchOverToNewWindow(String originalHandle){
        sleepFor(1);
        for(String each : driver.getWindowHandles()){
            if(!each.contains(originalHandle)){
                driver.switchTo().window(each);
            }
        }
    }

    public void assertThatTextIsPresented(String expectedText) {
        String pageSource = driver.getPageSource();
        boolean isPresented = pageSource.contains(expectedText);
        Assert.assertTrue(isPresented);
    }

    public void printOutAllTheTitles() {
        Set<String> handles = driver.getWindowHandles();
        for(String eachHandle :  handles){
            driver.switchTo().window(eachHandle);
            System.out.println(driver.getTitle());
        }
    }

    public void closeLastOpenedWindow() {
        driver.close();
    }

    public void sendKeysTo(By element, String charSequence){
        //create webElement object by using findElement() which receives 'element'
        WebElement webElement = driver.findElement(element);

        //type charSequence
        webElement.sendKeys(charSequence);
    }

    public void waitFor(By element){
        //create object 'wait' with field 'driver' and 10 seconds of timeout
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //wait until element will presented on the page
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public boolean isVisible(By logoutButton) {
        return driver.findElement(logoutButton).isDisplayed();
    }

}