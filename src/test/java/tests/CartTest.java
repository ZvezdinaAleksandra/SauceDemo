package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

public class CartTest extends BaseTest {
    // Smoke: открытие корзины
    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            testName = "Open Cart Page",
            description = "Проверка открытия страницы корзины и отображения заголовка 'Your Cart'"
    )
    public void shouldBeOnCartPage () {
        cartPage.open();
        Assert.assertEquals(cartPage.getTitleYourCart(), "Your Cart");
    }
    // Основной флоу: добавили товар-проверили
    @Test(
            priority = 2,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину и корректности его отображения (название и цена)"
    )
    public void shouldContainCorrectProductInCart() {
        SoftAssert softAssert = new SoftAssert();

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        softAssert.assertTrue(
                cartPage.isProductInCart("Sauce Labs Backpack"),
                "Товар не отображается в корзине"
        );
        softAssert.assertEquals(
                cartPage.getItemPrices().get(0),
                "$29.99",
                "Цена товара в корзине неверная"
        );
        softAssert.assertAll();
    }
    // Основной флоу: удаление
    @Test(
            priority = 3,
            groups = {"regression"},
            testName = "Remove Product From Cart",
            description = "Проверка удаления товара из корзины по кнопке Remove"
    )
    public void shouldRemoveProductFromCart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
    }
    // Проверка состояния после удаления
    @Test(
            priority = 4,
            groups = {"regression"},
            testName = "Empty Cart After Removal",
            description = "Проверка, что корзина становится пустой после удаления товара"
    )
    public void shouldBeEmptyCartAfterRemovingProduct() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getProductsCount(), 0);
    }
    // Доп: несколько товаров
    @Test(
            priority = 5,
            groups = {"regression"},
            testName = "Add Multiple Products To Cart",
            description = "Проверка добавления нескольких товаров в корзину и их одновременного отображения"
    )
    public void shouldContainMultipleProductsInCart() {
        SoftAssert softAssert = new SoftAssert();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        softAssert.assertTrue(
                cartPage.isProductInCart("Sauce Labs Backpack"),
                "Backpack отсутствует в корзине"
        );
        softAssert.assertTrue(
                cartPage.isProductInCart("Sauce Labs Bike Light"),
                "Bike Light отсутствует в корзине"
        );
        softAssert.assertAll();
    }
    // Кнопки
    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Action Buttons Visibility",
            description = "Проверка отображения кнопок Checkout и Continue Shopping в корзине"
    )
    public void shouldDisplayActionButtonsInCart() {
        SoftAssert softAssert = new SoftAssert();
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        softAssert.assertTrue(
                cartPage.isCheckoutButtonDisplayed(),
                "Кнопка Checkout не отображается"
        );
        softAssert.assertTrue(
                cartPage.isContinueShoppingButtonDisplayed(),
                "Кнопка Continue Shopping не отображается"
        );
        softAssert.assertAll();
    }
    // Переход в checkout
    @Test(
            priority = 7,
            groups = {"smoke", "regression", "e2e"},
            testName = "Navigate To Checkout",
            description = "Проверка перехода на страницу оформления заказа (Checkout) из корзины"
    )
    public void shouldNavigateToCheckoutPage() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }
    // Возврат к товарам
    @Test(
            priority = 8,
            groups = {"regression"},
            testName = "Return To Products From Cart",
            description = "Проверка возврата на страницу товаров при нажатии Continue Shopping"
    )
    public void shouldReturnToProductsPageFromCart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShopping();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory"));
    }
}