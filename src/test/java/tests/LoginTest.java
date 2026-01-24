package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

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
}
