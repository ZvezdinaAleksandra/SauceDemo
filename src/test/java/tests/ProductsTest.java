package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Retry;

@Epic("E2E Tests")
@Feature("Products Functionality")
@Owner("Zvezdina Aleksandra")
public class ProductsTest extends BaseTest {

    // контролируемый flaky счетчик
    private static int attempt = 0;

    // Проверка страницы (flaky + retry)
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            retryAnalyzer = Retry.class,
            testName = "Products Page Is Opened",
            description = "Проверка открытия страницы продуктов и отображения заголовка"
    )
    @Description("Проверка загрузки страницы Products")
    @Story("Открытие страницы продуктов")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldBeOnProductsPage() {

        productsPage.open();

        // контролируемый flaky сценарий (только demo retry)
        attempt++;

        if (attempt < 3) {
            throw new RuntimeException("Simulated flaky failure for retry demo");
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
    @Description("Проверка отображения данных товара")
    @Story("Отображение карточки товара")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDisplayProductCardWithNameAndPrice() {

        productsPage.open();

        Assert.assertTrue(productsPage.isProductDisplayed("Sauce Labs Backpack"));
        Assert.assertEquals(productsPage.getProductPrice("Sauce Labs Backpack"), "$29.99");
    }

    // Добавление товара
    @Test(
            priority = 3,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину"
    )
    @Description("Добавление товара в корзину")
    @Story("Добавление товаров")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldAddProductToCart() {

        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(productsPage.getCartBadgeText(), "1");
    }

    // Кнопка Remove
    @Test(
            priority = 4,
            groups = {"regression"},
            testName = "Remove Button Appears",
            description = "Проверка смены кнопки Add to Cart на Remove"
    )
    @Description("Проверка кнопки Remove")
    @Story("Изменение состояния кнопки")
    @Severity(SeverityLevel.NORMAL)
    public void shouldChangeAddToCartButtonToRemove() {

        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isRemoveButtonDisplayed("Sauce Labs Backpack"));
    }

    // Добавление 2 товаров
    @Test(
            priority = 5,
            groups = {"regression"},
            testName = "Cart Badge Increases",
            description = "Проверка увеличения счетчика корзины"
    )
    @Description("Добавление нескольких товаров")
    @Story("Работа корзины")
    @Severity(SeverityLevel.NORMAL)
    public void shouldIncreaseCartBadgeWhenAddingTwoProducts() {

        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(productsPage.getCartBadgeText(), "2");
    }

    // Удаление товара
    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Badge Decreases After Removal",
            description = "Проверка обновления счетчика корзины"
    )
    @Description("Удаление товара")
    @Story("Удаление товаров")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDecreaseCartBadgeWhenRemovingProduct() {

        productsPage.open();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProduct("Sauce Labs Backpack");

        Assert.assertTrue(productsPage.isCartBadgeEmpty());
    }

    // Переход в корзину
    @Test(
            priority = 7,
            groups = {"smoke", "regression"},
            testName = "Navigate To Cart",
            description = "Проверка перехода в корзину"
    )
    @Description("Переход в корзину")
    @Story("Навигация")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldNavigateToCartPage() {

        productsPage.open();
        productsPage.clickShoppingCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    // Проверка всех товаров
    @Test(
            priority = 8,
            groups = {"regression"},
            testName = "All Products Are Displayed",
            description = "Проверка отображения всех товаров"
    )
    @Description("Проверка количества товаров")
    @Story("UI товаров")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayAllProductsOnPage() {

        productsPage.open();

        Assert.assertEquals(productsPage.getProductsCount(), 6);
    }
}