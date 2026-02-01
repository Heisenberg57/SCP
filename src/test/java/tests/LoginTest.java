package tests;

import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void validLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.enterUsername(ConfigReader.get("username"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();

        SecureAreaPage securePage = new SecureAreaPage(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), "URL does not contain /secure after login");
        Assert.assertTrue(securePage.isLogoutButtonVisible(),"Logout button not visible after login");

        String message= loginPage.getFlashMessage();
        Assert.assertTrue(message.contains("secure area"),
                "Success message not found: " + message);
    }

    @Test
    public void invalidLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"User should remain on login page after invalid login");
        Assert.assertTrue(loginPage.isFlashMessageVisible(),"Error Message should be visible for invalid login");

        String message = loginPage.getFlashMessage();
        Assert.assertTrue(message.toLowerCase().contains("invalid"),"Expected invalid login message, got: " + message);
    }

    @Test
    public void emptyCredentialsTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"User should not be logged in with empty credentials");
        Assert.assertTrue(loginPage.isFlashMessageVisible(),"Error message should appear when submitting empty form");

    }

    @Test
    public void logoutFlowTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.enterUsername(ConfigReader.get("username"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();

        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);
        Assert.assertTrue(secureAreaPage.isLogoutButtonVisible(),"Logout Button is not visible after login");

        secureAreaPage.clickLogout();


        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"User should be redirected to login page after logout");

        String logoutMessage = secureAreaPage.getLogoutMessage();
        Assert.assertTrue(logoutMessage.toLowerCase().contains("logged out"),"Logout success message not shown: "+logoutMessage);
    }
}
