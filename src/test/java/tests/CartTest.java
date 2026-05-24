package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

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
    @Description("Проверка открытия страницы корзины и отображения заголовка 'Your Cart'")
    @Story("Открытие корзины")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    public void shouldBeOnCartPage() {
        cartPage.open();
        Assert.assertEquals(cartPage.getTitleYourCart(), "Your Cart");
    }

    @Test(
            priority = 2,
            groups = {"smoke", "regression"},
            testName = "Add Product To Cart",
            description = "Проверка добавления товара в корзину и корректности его отображения"
    )
    @Description("Проверка добавления товара и его данных в корзине")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(
            priority = 3,
            groups = {"regression"},
            testName = "Remove Product From Cart",
            description = "Проверка удаления товара из корзины"
    )
    @Description("Удаление товара через кнопку Remove")
    @Story("Удаление товара")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRemoveProductFromCart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
    }

    @Test(
            priority = 4,
            groups = {"regression"},
            testName = "Empty Cart After Removal",
            description = "Проверка, что корзина пустая после удаления товара"
    )
    @Description("Корзина становится пустой после удаления товара")
    @Story("Очистка корзины")
    @Severity(SeverityLevel.NORMAL)
    public void shouldBeEmptyCartAfterRemovingProduct() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickRemove("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getProductsCount(), 0);
    }

    @Test(
            priority = 5,
            groups = {"regression"},
            testName = "Add Multiple Products To Cart",
            description = "Проверка добавления нескольких товаров в корзину"
    )
    @Description("Добавление нескольких товаров и проверка их отображения")
    @Story("Множественное добавление товаров")
    @Severity(SeverityLevel.NORMAL)
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

    @Test(
            priority = 6,
            groups = {"regression"},
            testName = "Cart Action Buttons Visibility",
            description = "Проверка отображения кнопок Checkout и Continue Shopping"
    )
    @Description("Проверка кнопок управления корзиной")
    @Story("UI элементов корзины")
    @Severity(SeverityLevel.MINOR)
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

    @Test(
            priority = 7,
            groups = {"smoke", "regression", "e2e"},
            testName = "Navigate To Checkout",
            description = "Проверка перехода на Checkout страницу"
    )
    @Description("Переход из корзины в оформление заказа")
    @Story("Checkout переход")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldNavigateToCheckoutPage() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }

    @Test(
            priority = 8,
            groups = {"regression"},
            testName = "Return To Products From Cart",
            description = "Проверка возврата на страницу товаров"
    )
    @Description("Переход назад к товарам через Continue Shopping")
    @Story("Навигация назад")
    @Severity(SeverityLevel.NORMAL)
    public void shouldReturnToProductsPageFromCart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShopping();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory")
        );
    }
}