package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

@Log4j2
@Epic("E2E Tests")
@Feature("Cart Functionality")
@Owner("Zvezdina Aleksandra")
public class CartTest extends BaseTest {

    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Open Cart Page",
            description = "Проверка открытия страницы корзины и отображения заголовка 'Your Cart'"
    )
    public void shouldBeOnCartPage() {
        log.info("[TEST] Open Cart Page started");
        cartPage.open();
        String actualTitle = cartPage.getTitleYourCart();
        log.info("Cart title = {}", actualTitle);
        Assert.assertEquals(actualTitle, "Your Cart");
        log.info("[TEST] Open Cart Page finished successfully");
    }

    @Test(
            priority = 2,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину и корректности его отображения"
    )
    public void shouldContainCorrectProductInCart() {
        log.info("[TEST] Add Product To Cart started");
        SoftAssert softAssert = new SoftAssert();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        boolean isInCart = cartPage.isProductInCart("Sauce Labs Backpack");
        String price = cartPage.getItemPrices().get(0);
        log.info("Product in cart = {}, price = {}", isInCart, price);
        softAssert.assertTrue(isInCart, "Товар не отображается в корзине");
        softAssert.assertEquals(price, "$29.99", "Цена товара в корзине неверная");
        softAssert.assertAll();
        log.info("[TEST] Add Product To Cart finished");
    }

    @Test(priority = 3)
    public void shouldRemoveProductFromCart() {
        log.info("[TEST] Remove Product From Cart started");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");
        boolean isInCart = cartPage.isProductInCart("Sauce Labs Backpack");
        log.info("Product still in cart = {}", isInCart);
        Assert.assertFalse(isInCart);
        log.info("[TEST] Remove Product From Cart finished");
    }

    @Test(priority = 4)
    public void shouldBeEmptyCartAfterRemovingProduct() {
        log.info("[TEST] Empty Cart After Removal started");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");
        int count = cartPage.getProductsCount();
        log.info("Products count = {}", count);
        Assert.assertEquals(count, 0);
        log.info("[TEST] Empty Cart After Removal finished");
    }

    @Test(priority = 5)
    public void shouldContainMultipleProductsInCart() {
        log.info("[TEST] Multiple Products In Cart started");
        SoftAssert softAssert = new SoftAssert();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        boolean p1 = cartPage.isProductInCart("Sauce Labs Backpack");
        boolean p2 = cartPage.isProductInCart("Sauce Labs Bike Light");
        log.info("Backpack in cart = {}, Bike Light in cart = {}", p1, p2);
        softAssert.assertTrue(p1);
        softAssert.assertTrue(p2);
        softAssert.assertAll();
        log.info("[TEST] Multiple Products In Cart finished");
    }
    @Test(priority = 6)
    public void shouldDisplayActionButtonsInCart() {
        log.info("[TEST] Action Buttons Visibility started");
        SoftAssert softAssert = new SoftAssert();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        boolean checkoutVisible = cartPage.isCheckoutButtonDisplayed();
        boolean continueVisible = cartPage.isContinueShoppingButtonDisplayed();
        log.info("Checkout button = {}, Continue button = {}", checkoutVisible, continueVisible);
        softAssert.assertTrue(checkoutVisible);
        softAssert.assertTrue(continueVisible);
        softAssert.assertAll();
        log.info("[TEST] Action Buttons Visibility finished");
    }
    @Test(priority = 7)
    public void shouldNavigateToCheckoutPage() {
        log.info("[TEST] Navigate To Checkout started");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        String url = driver.getCurrentUrl();
        log.info("Current URL = {}", url);
        Assert.assertTrue(url.contains("checkout"));
        log.info("[TEST] Navigate To Checkout finished");
    }
    @Test(priority = 8)
    public void shouldReturnToProductsPageFromCart() {
        log.info("[TEST] Return To Products started");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShopping();
        String url = driver.getCurrentUrl();
        log.info("Current URL = {}", url);
        Assert.assertTrue(url.contains("inventory"));
        log.info("[TEST] Return To Products finished");
    }
}