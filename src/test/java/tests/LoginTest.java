package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    // Проверка: успешный логин с валидными данными
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Successful Login",
            description = "Проверка успешного входа в систему с валидными учетными данными"
    )
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    //NEGATIVE TESTS

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
            testName = "Negative Login Scenarios",
            description = "Проверка негативных сценариев логина с различными невалидными данными",
            dataProvider = "negativeLoginData"
    )
    public void checkNegativeLogin(String username, String password, String expectedError) {

        loginPage.open();
        loginPage.login(username, password);

        Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
    }
}