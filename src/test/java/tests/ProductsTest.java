package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends AuthBaseTest {
    // проверка страницы
    @Test
    public void shouldBeOnProductsPage() {
        productsPage.open();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    // Проверка: карточка товара
    @Test
    public void shouldDisplayProductCardWithNameAndPrice() {
        productsPage.open();

        Assert.assertTrue(productsPage.isProductDisplayed("Sauce Labs Backpack"));
        Assert.assertEquals(
                productsPage.getProductPrice("Sauce Labs Backpack"),
                "$29.99"
        );
    }
    // Проверка: добавление товара
    @Test
    public void shouldAddProductToCart() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(productsPage.getCartBadgeText(), "1");
    }
    // Проверка: кнопка Remove
    @Test
    public void shouldChangeAddToCartButtonToRemove() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isRemoveButtonDisplayed("Sauce Labs Backpack"));
    }
    // Проверка: добавлено два товара
    @Test
    public void shouldIncreaseCartBadgeWhenAddingTwoProducts() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(productsPage.getCartBadgeText(), "2");
    }
    // Проверка: удаление товара
    @Test
    public void shouldDecreaseCartBadgeWhenRemovingProduct() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProduct("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isCartBadgeEmpty());
    }
    // Проверка: переход в корзину
    @Test
    public void shouldNavigateToCartPage() {
        productsPage.open();
        productsPage.clickShoppingCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }
    // Проверка: количество товаров
    @Test
    public void shouldDisplayAllProductsOnPage() {
        productsPage.open();

        Assert.assertEquals(productsPage.getProductsCount(), 6);
    }
}