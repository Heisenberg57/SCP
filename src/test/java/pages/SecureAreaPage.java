package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecureAreaPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logoutButton = By.cssSelector("a.button.secondary.radius");
    private By flashMessage = By.id("flash");

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public boolean isLogoutButtonVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
    }

    public void clickLogout(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public String getLogoutMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

}
