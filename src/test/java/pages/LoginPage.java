package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    //private WebDriverWait wait;
    private WaitUtils wait;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");
    private By errorMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait=new WaitUtils(driver);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/login");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        wait.waitForVisibility(usernameInput);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
       // wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.waitForClickable(loginButton);
        driver.findElement(loginButton).click();
    }

    public String getFlashMessage() {
//        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)
//        );

//        WebElement message = wait.waitForVisibility(flashMessage);
//
//        return message.getText();
        return wait.waitForVisibility(flashMessage).getText();

    }

    public boolean isFlashMessageVisible(){
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        return  wait.waitForVisibility(errorMessage).isDisplayed();
    }

    public boolean isAt(){
        return driver.getCurrentUrl().contains("/login");
    }

    public void loginAs(String username, String password){
        wait.waitForVisibility(usernameInput).clear();
        wait.waitForVisibility(usernameInput).sendKeys(username);

        wait.waitForVisibility(passwordInput).clear();
        wait.waitForVisibility(passwordInput).sendKeys(password);

        wait.waitForClickable(loginButton).click();
    }





}


