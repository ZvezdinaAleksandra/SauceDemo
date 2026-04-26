package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutInformationTest extends AuthBaseTest {


    // Проверка: успешное заполнение формы
    @Test
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
    @Test
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
    @Test
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