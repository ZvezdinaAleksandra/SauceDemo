package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutInformationTest extends BaseTest {
    // Проверка: успешное заполнение формы
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"},
            testName = "Successful Checkout Info Fill",
            description = "Проверка успешного заполнения формы checkout information и перехода на следующий шаг"
    )
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
            description = "Проверка возврата в корзину при нажатии кнопки Cancel на шаге checkout information"
    )
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