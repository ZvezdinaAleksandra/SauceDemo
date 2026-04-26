package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    // Проверка: успешный логин с валидными данными
    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    // Проверка: логин без пароля
    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user","");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }
    // Проверка: логин без username
    @Test
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("","secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }
    // Проверка: логин с невалидными данными должен быть отклонён
    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test","test");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any " +
                "user in this service");
    }
}
