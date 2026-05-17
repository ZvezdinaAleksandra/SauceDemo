package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E2E Tests")
@Feature("Products Functionality")
@Owner("Zvezdina Aleksandra")
public class ProductsTest extends BaseTest {

    // Проверка страницы
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Products Page Is Opened",
            description = "Проверка открытия страницы продуктов и отображения заголовка"
    )
    @Description("Проверка загрузки страницы Products")
    @Story("Открытие страницы продуктов")
    @Severity(SeverityLevel.CRITICAL)
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
    @Description("Проверка отображения данных товара")
    @Story("Отображение карточки товара")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Добавление товара в корзину")
    @Story("Добавление товаров")
    @Severity(SeverityLevel.CRITICAL)
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
            description = "Проверка смены кнопки Add to Cart на Remove"
    )
    @Description("Проверка кнопки Remove после добавления товара")
    @Story("Изменение состояния кнопки")
    @Severity(SeverityLevel.NORMAL)
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

    // Проверка: удаление товара
    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Badge Decreases After Removal",
            description = "Проверка обновления счетчика корзины после удаления товара"
    )
    @Description("Удаление товара из корзины")
    @Story("Удаление товаров")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Переход в корзину")
    @Story("Навигация")
    @Severity(SeverityLevel.CRITICAL)
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
            description = "Проверка отображения всех товаров на странице"
    )
    @Description("Проверка количества товаров")
    @Story("UI товаров")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayAllProductsOnPage() {
        productsPage.open();

        Assert.assertEquals(productsPage.getProductsCount(), 6);
    }
}