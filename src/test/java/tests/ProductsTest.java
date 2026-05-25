package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@Epic("E2E Tests")
@Feature("Products Functionality")
@Owner("Zvezdina Aleksandra")
public class ProductsTest extends BaseTest {

    @Test(
            priority = 1,
            groups = {"smoke", "regression"}
    )
    public void shouldBeOnProductsPage() {
        log.info("[TEST] shouldBeOnProductsPage START");
        productsPage.open();
        log.info("Products page opened");
        String title = productsPage.getTitle();
        log.info("Page title = {}", title);
        Assert.assertEquals(title, "Products");
        log.info("[TEST] shouldBeOnProductsPage END");
    }
    @Test(
            priority = 2,
            groups = {"regression"}
    )
    public void shouldDisplayProductCardWithNameAndPrice() {
        log.info("[TEST] shouldDisplayProductCardWithNameAndPrice START");
        productsPage.open();
        String product = "Sauce Labs Backpack";
        boolean isDisplayed = productsPage.isProductDisplayed(product);
        String price = productsPage.getProductPrice(product);
        log.info("Product = {}, displayed = {}, price = {}", product, isDisplayed, price);
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(price, "$29.99");
        log.info("[TEST] shouldDisplayProductCardWithNameAndPrice END");
    }
    @Test(
            priority = 3,
            groups = {"smoke", "regression"}
    )
    public void shouldAddProductToCart() {
        log.info("[TEST] shouldAddProductToCart START");
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        String badge = productsPage.getCartBadgeText();
        log.info("Cart badge = {}", badge);
        Assert.assertEquals(badge, "1");
        log.info("[TEST] shouldAddProductToCart END");
    }
    @Test(
            priority = 4,
            groups = {"regression"}
    )
    public void shouldChangeAddToCartButtonToRemove() {
        log.info("[TEST] shouldChangeAddToCartButtonToRemove START");
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        boolean isRemoveVisible = productsPage.isRemoveButtonDisplayed("Sauce Labs Backpack");
        log.info("Remove button visible = {}", isRemoveVisible);
        Assert.assertTrue(isRemoveVisible);
        log.info("[TEST] shouldChangeAddToCartButtonToRemove END");
    }
    @Test(
            priority = 5,
            groups = {"regression"}
    )
    public void shouldIncreaseCartBadgeWhenAddingTwoProducts() {
        log.info("[TEST] shouldIncreaseCartBadgeWhenAddingTwoProducts START");
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        String badge = productsPage.getCartBadgeText();
        log.info("Cart badge = {}", badge);
        Assert.assertEquals(badge, "2");
        log.info("[TEST] shouldIncreaseCartBadgeWhenAddingTwoProducts END");
    }
    @Test(
            priority = 6,
            groups = {"regression"}
    )
    public void shouldDecreaseCartBadgeWhenRemovingProduct() {
        log.info("[TEST] shouldDecreaseCartBadgeWhenRemovingProduct START");
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProduct("Sauce Labs Backpack");
        boolean isEmpty = productsPage.isCartBadgeEmpty();
        log.info("Cart badge empty = {}", isEmpty);
        Assert.assertTrue(isEmpty);
        log.info("[TEST] shouldDecreaseCartBadgeWhenRemovingProduct END");
    }
    @Test(
            priority = 7,
            groups = {"smoke", "regression"}
    )
    public void shouldNavigateToCartPage() {
        log.info("[TEST] shouldNavigateToCartPage START");
        productsPage.open();
        productsPage.clickShoppingCart();
        String url = driver.getCurrentUrl();
        log.info("Current URL = {}", url);
        Assert.assertTrue(url.contains("cart"));
        log.info("[TEST] shouldNavigateToCartPage END");
    }
    @Test(
            priority = 8,
            groups = {"regression"}
    )
    public void shouldDisplayAllProductsOnPage() {
        log.info("[TEST] shouldDisplayAllProductsOnPage START");
        productsPage.open();
        int count = productsPage.getProductsCount();
        log.info("Products count = {}", count);
        Assert.assertEquals(count, 6);
        log.info("[TEST] shouldDisplayAllProductsOnPage END");
    }
}