package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends AuthBaseTest {

    // Проверка перехода на страницу корзины
    @Test
    public void shouldBeOnCartPage () {
        cartPage.open();
        Assert.assertEquals(cartPage.getTitleYourCart(), "Your Cart");
    }
    // Проверка, что товар отображается в корзине
    @Test
    public void shouldContainCorrectProductInCart() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        Assert.assertEquals(cartPage.getItemPrices().get(0), "$29.99");
    }
    // Проверка удаления товара через кнопку Remove
    @Test
    public void shouldRemoveProductFromCart() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
    }
    // Проверка, что корзина пустая после удаления товара
    @Test
    public void shouldBeEmptyCartAfterRemovingProduct() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getProductsCount(), 0);
    }
    // Проверка добавления нескольких товаров
    @Test
    public void shouldContainMultipleProductsInCart() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"));
    }
    // Проверка кнопок корзины
    @Test
    public void shouldDisplayActionButtonsInCart() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        Assert.assertTrue(cartPage.isCheckoutButtonDisplayed());
        Assert.assertTrue(cartPage.isContinueShoppingButtonDisplayed());
    }
    // Проверка перехода на checkout страницу
    @Test
    public void shouldNavigateToCheckoutPage() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }
    // Проверка возврата на страницу товаров через Continue Shopping
    @Test
    public void shouldReturnToProductsPageFromCart() {

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShopping();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
}