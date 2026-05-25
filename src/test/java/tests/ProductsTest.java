package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Retry;
@Log4j2
@Epic("E2E Tests")
@Feature("Products Functionality")
@Owner("Zvezdina Aleksandra")
public class ProductsTest extends BaseTest {
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            retryAnalyzer = Retry.class,
            testName = "Products Page Is Opened",
            description = "Проверка открытия страницы продуктов и отображения заголовка"
    )
    public void shouldBeOnProductsPage() {
        log.info("[TEST] shouldBeOnProductsPage START");
        productsPage.open();
        String title = productsPage.getTitle();
        log.info("Page title = {}", title);
        Assert.assertEquals(title, "Products");
        log.info("[TEST] shouldBeOnProductsPage END");
    }
    @Test(
            priority = 2,
            groups = {"regression"},
            testName = "Product Card Display",
            description = "Проверка отображения карточки товара"
    )
    public void shouldDisplayProductCardWithNameAndPrice() {
        productsPage.open();
        String product = "Sauce Labs Backpack";
        boolean isDisplayed = productsPage.isProductDisplayed(product);
        String price = productsPage.getProductPrice(product);
        log.info("Product = {}, displayed = {}, price = {}", product, isDisplayed, price);
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(price, "$29.99");
    }
    @Test(
            priority = 3,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину"
    )
    public void shouldAddProductToCart() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        String badge = productsPage.getCartBadgeText();
        log.info("Cart badge = {}", badge);
        Assert.assertEquals(badge, "1");
    }
    @Test(
            priority = 4,
            groups = {"regression"},
            testName = "Remove Button Appears",
            description = "Проверка появления кнопки Remove"
    )
    public void shouldChangeAddToCartButtonToRemove() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        boolean isRemoveVisible = productsPage.isRemoveButtonDisplayed("Sauce Labs Backpack");
        log.info("Remove button visible = {}", isRemoveVisible);
        Assert.assertTrue(isRemoveVisible);
    }
    @Test(
            priority = 5,
            groups = {"regression"},
            testName = "Cart Badge Increases",
            description = "Проверка увеличения бейджа корзины"
    )
    public void shouldIncreaseCartBadgeWhenAddingTwoProducts() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        String badge = productsPage.getCartBadgeText();
        log.info("Cart badge = {}", badge);
        Assert.assertEquals(badge, "2");
    }
    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Badge Decreases After Removal",
            description = "Проверка уменьшения бейджа после удаления товара"
    )
    public void shouldDecreaseCartBadgeWhenRemovingProduct() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProduct("Sauce Labs Backpack");
        boolean isEmpty = productsPage.isCartBadgeEmpty();
        log.info("Cart badge empty = {}", isEmpty);
        Assert.assertTrue(isEmpty);
    }
    @Test(
            priority = 7,
            groups = {"smoke", "regression"},
            testName = "Navigate To Cart",
            description = "Проверка перехода в корзину"
    )
    public void shouldNavigateToCartPage() {
        productsPage.open();
        productsPage.clickShoppingCart();
        String url = driver.getCurrentUrl();
        log.info("Current URL = {}", url);
        Assert.assertTrue(url.contains("cart"));
    }
    @Test(
            priority = 8,
            groups = {"regression"},
            testName = "All Products Are Displayed",
            description = "Проверка отображения всех товаров"
    )
    public void shouldDisplayAllProductsOnPage() {
        productsPage.open();
        int count = productsPage.getProductsCount();
        log.info("Products count = {}", count);
        Assert.assertEquals(count, 6);
    }
}