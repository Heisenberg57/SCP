package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class SecureAreaPage {
    private WebDriver driver;
    //private WebDriverWait wait;
    private WaitUtils waitUt;

    private By logoutButton = By.cssSelector("a.button.secondary.radius");
    private By flashMessage = By.id("flash");

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUt = new WaitUtils(driver);

    }

    public boolean isLogoutButtonVisible(){
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
        return waitUt.waitForVisibility(logoutButton).isDisplayed();

    }

    public void clickLogout(){
        //wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        waitUt.waitForClickability(logoutButton);
    }

    public String getLogoutMessage(){
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
        return waitUt.waitForVisibility(flashMessage).getText();
    }

}
