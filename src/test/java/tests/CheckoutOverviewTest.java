package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E2E Tests")
@Feature("Checkout Overview Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutOverviewTest extends BaseTest {

    private void goToOverview() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();
    }

    // Проверка: на странице Overview отображаются товары из корзины
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"},
            testName = "Overview Items And User Info Display",
            description = "Проверка отображения товаров и информации пользователя на странице Overview"
    )
    @Description("Проверка корректного отображения товаров и user info на Overview странице")
    @Story("Отображение данных заказа на Overview")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOverviewPageElements() {
        goToOverview();

        Assert.assertTrue(checkoutOverviewPage.getItemsCount() > 0);
        Assert.assertTrue(checkoutOverviewPage.isUserInfoDisplayed());
    }

    // Проверка: кнопка Cancel возвращает пользователя на страницу товаров
    @Test(
            priority = 2,
            groups = {"regression"},
            testName = "Cancel From Overview Returns To Products",
            description = "Проверка возврата на страницу товаров при нажатии Cancel"
    )
    @Description("Проверка кнопки Cancel на странице Overview")
    @Story("Отмена оформления заказа на Overview")
    @Severity(SeverityLevel.NORMAL)
    public void checkCancelReturnsToCart() {
        goToOverview();
        checkoutOverviewPage.clickCancel();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    // Проверка: завершение заказа
    @Test(
            priority = 3,
            groups = {"smoke", "regression", "e2e"},
            testName = "Finish Order Navigation",
            description = "Проверка перехода на страницу завершения заказа после нажатия Finish"
    )
    @Description("Проверка завершения заказа через кнопку Finish")
    @Story("Финализация заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void checkFinishOrder() {
        goToOverview();
        checkoutOverviewPage.clickFinish();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
    }
}