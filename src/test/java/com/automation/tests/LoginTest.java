package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pojo.LoginData;
import com.automation.utils.DriverManager;
import com.automation.utils.JsonDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    String target_url = "https://www.saucedemo.com/";

    @BeforeClass
    public void open_website() {
        driver = DriverManager.getDriver("firefox");
        driver.get(target_url);
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        LoginData[] data = JsonDataReader.getLoginData("src/test/resources/testdata/loginData.json");
        Object[][] testData = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            testData[i][0] = data[i];
        }
        return testData;
    }

    @Test(dataProvider = "loginData")
    public void username_pass(LoginData loginData) {
        driver.get(target_url); // reset page for each data set
        loginPage.enterUsername(loginData.getUsername());
        loginPage.enterPassword(loginData.getPassword());
        loginPage.clickLogin();

        if (loginData.getExpectedResult().equals("success")) {
            Assert.assertFalse(loginPage.isErrorDisplayed(), "Expected login to succeed but error was shown");
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected login to fail but no error was shown");
        }
    }

    @AfterClass
    public void close_browser() {
        DriverManager.quitDriver();
    }
}
