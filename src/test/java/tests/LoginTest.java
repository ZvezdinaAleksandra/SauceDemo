package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
@Epic("Authentication Tests")
@Feature("Login Functionality")
@Owner("Zvezdina Aleksandra")
public class LoginTest extends BaseTest {
    @Test(
            priority = 1,
            groups = {"smoke", "regression"}
    )
    public void checkLoginWithPositiveCred() {
        log.info("[TEST] Positive login started");
        loginPage.open();
        log.info("Login page opened");
        loginPage.login(user, password);
        log.info("Login performed with valid credentials");
        String title = productsPage.getTitle();
        log.info("Products page title = {}", title);
        Assert.assertEquals(title, "Products");
        log.info("[TEST] Positive login finished");
    }
    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }
    @Test(
            priority = 2,
            groups = {"regression"},
            dataProvider = "negativeLoginData"
    )
    public void checkNegativeLogin(String username, String password, String expectedError) {
        log.info("[TEST] Negative login started | user={}, pass={}", username, password);
        loginPage.open();
        log.info("Login page opened");
        loginPage.login(username, password);
        log.info("Login attempt done | user={}", username);
        String actualError = loginPage.getErrorMessage();
        log.info("Expected error = {}", expectedError);
        log.info("Actual error = {}", actualError);
        Assert.assertEquals(actualError, expectedError);
        log.info("[TEST] Negative login finished | user={}", username);
    }
}