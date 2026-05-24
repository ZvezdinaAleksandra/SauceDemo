package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E2E Tests")
@Feature("Checkout Information Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutInformationTest extends BaseTest {

    // Проверка: успешное заполнение формы
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"},
            testName = "Successful Checkout Info Fill",
            description = "Проверка успешного заполнения формы checkout information и перехода на следующий шаг"
    )
    @Description("Проверка корректного заполнения формы и перехода на следующий шаг checkout")
    @Story("Успешное заполнение checkout формы")
    @Severity(SeverityLevel.CRITICAL)
    public void successCheckoutInformationFill() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));
    }

    // Проверка: ошибка, если поля не заполнены
    @Test(
            priority = 2,
            groups = {"regression"},
            testName = "Checkout Info Empty Fields Validation",
            description = "Проверка отображения ошибки при незаполненных полях формы checkout information"
    )
    @Description("Валидация обязательных полей checkout формы")
    @Story("Ошибка при пустых полях формы")
    @Severity(SeverityLevel.NORMAL)
    public void checkoutInformationEmptyFieldsError() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("", "", "");
        checkoutInformationPage.clickContinue();

        Assert.assertTrue(checkoutInformationPage.isErrorDisplayed());
        Assert.assertTrue(checkoutInformationPage.getErrorMessage().contains("Error"));
    }

    // Проверка: кнопка Cancel возвращает в корзину
    @Test(
            priority = 3,
            groups = {"smoke", "regression"},
            testName = "Cancel Checkout Info Returns To Cart",
            description = "Проверка возврата в корзину при нажатии кнопки Cancel"
    )
    @Description("Проверка кнопки Cancel на checkout information странице")
    @Story("Отмена оформления заказа")
    @Severity(SeverityLevel.MINOR)
    public void checkoutInformationCancelReturnsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.clickCancel();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}