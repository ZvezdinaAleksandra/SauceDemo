package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@Epic("E2E Tests")
@Feature("Checkout Information Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutInformationTest extends BaseTest {
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"}
    )
    public void successCheckoutInformationFill() {
        log.info("[TEST] successCheckoutInformationFill START");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();
        String url = driver.getCurrentUrl();
        log.info("Current URL after continue = {}", url);
        Assert.assertTrue(url.contains("checkout-step-two"));
        log.info("[TEST] successCheckoutInformationFill END");
    }
    @Test(
            priority = 2,
            groups = {"regression"}
    )
    public void checkoutInformationEmptyFieldsError() {
        log.info("[TEST] checkoutInformationEmptyFieldsError START");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("", "", "");
        checkoutInformationPage.clickContinue();
        boolean errorDisplayed = checkoutInformationPage.isErrorDisplayed();
        String errorMessage = checkoutInformationPage.getErrorMessage();
        log.info("Error displayed = {}", errorDisplayed);
        log.info("Error message = {}", errorMessage);
        Assert.assertTrue(errorDisplayed);
        Assert.assertTrue(errorMessage.contains("Error"));
        log.info("[TEST] checkoutInformationEmptyFieldsError END");
    }

    @Test(
            priority = 3,
            groups = {"smoke", "regression"}
    )
    public void checkoutInformationCancelReturnsToCart() {
        log.info("[TEST] checkoutInformationCancelReturnsToCart START");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutInformationPage.clickCancel();
        String url = driver.getCurrentUrl();
        log.info("Current URL after cancel = {}", url);
        Assert.assertTrue(url.contains("cart.html"));
        log.info("[TEST] checkoutInformationCancelReturnsToCart END");
    }
}