package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Authentication Tests")
@Feature("Login Functionality")
@Owner("Zvezdina Aleksandra")
public class LoginTest extends BaseTest {

    // Проверка: успешный логин с валидными данными
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Successful Login",
            description = "Проверка успешного входа в систему с валидными учетными данными"
    )
    @Description("Проверка успешной авторизации пользователя")
    @Story("Позитивный сценарий логина")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithPositiveCred() {

        loginPage.open();
        loginPage.login(user, password);

        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    // NEGATIVE TESTS
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
            description = "Проверка негативных сценариев логина с невалидными данными",
            dataProvider = "negativeLoginData"
    )
    @Description("Проверка ошибок авторизации при некорректных данных")
    @Story("Негативные сценарии логина")
    @Severity(SeverityLevel.NORMAL)
    public void checkNegativeLogin(String username, String password, String expectedError) {

        loginPage.open();
        loginPage.login(username, password);

        Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
    }
}