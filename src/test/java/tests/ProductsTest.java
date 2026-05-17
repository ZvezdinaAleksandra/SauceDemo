package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {
    // Проверка страницы
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Products Page Is Opened",
            description = "Проверка открытия страницы продуктов и отображения заголовка"
    )
    //public void shouldBeOnProductsPage() {
    // productsPage.open();
    // Assert.assertEquals(productsPage.getTitle(), "Products"); }

    //Для проверки Retry
    public void shouldBeOnProductsPage() {
        productsPage.open();

        if (Math.random() < 0.7) {
            Assert.fail("Random fail to test retry");
        }

        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    // Проверка: карточка товара
    @Test(
            priority = 2,
            groups = {"regression"},
            testName = "Product Card Display",
            description = "Проверка отображения карточки товара с названием и ценой"
    )
    public void shouldDisplayProductCardWithNameAndPrice() {
        productsPage.open();

        Assert.assertTrue(productsPage.isProductDisplayed("Sauce Labs Backpack"));
        Assert.assertEquals(
                productsPage.getProductPrice("Sauce Labs Backpack"),
                "$29.99"
        );
    }
    // Проверка: добавление товара
    @Test(
            priority = 3,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину и обновления badge"
    )
    public void shouldAddProductToCart() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(productsPage.getCartBadgeText(), "1");
    }
    // Проверка: кнопка Remove
    @Test(
            priority = 4,
            groups = {"regression"},
            testName = "Remove Button Appears",
            description = "Проверка смены кнопки Add to Cart на Remove после добавления товара"
    )
    public void shouldChangeAddToCartButtonToRemove() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isRemoveButtonDisplayed("Sauce Labs Backpack"));
    }
    // Проверка: добавлено два товара
    @Test(
            priority = 5,
            groups = {"regression"},
            testName = "Cart Badge Increases",
            description = "Проверка увеличения счетчика корзины при добавлении нескольких товаров"
    )
    public void shouldIncreaseCartBadgeWhenAddingTwoProducts() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(productsPage.getCartBadgeText(), "2");
    }
    // Проверка: удаление товара
    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Badge Decreases After Removal",
            description = "Проверка обновления счетчика корзины после удаления товара"
    )
    public void shouldDecreaseCartBadgeWhenRemovingProduct() {
        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProduct("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isCartBadgeEmpty());
    }
    // Проверка: переход в корзину
    @Test(
            priority = 7,
            groups = {"smoke", "regression"},
            testName = "Navigate To Cart",
            description = "Проверка перехода на страницу корзины"
    )
    public void shouldNavigateToCartPage() {
        productsPage.open();
        productsPage.clickShoppingCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }
    // Проверка: количество товаров
    @Test(
            priority = 8,
            groups = {"regression"},
            testName = "All Products Are Displayed",
            description = "Проверка отображения всех товаров на странице продуктов"
    )
    public void shouldDisplayAllProductsOnPage() {
        productsPage.open();

        Assert.assertEquals(productsPage.getProductsCount(), 6);
    }
}