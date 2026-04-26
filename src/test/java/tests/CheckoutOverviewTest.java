package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOverviewTest extends AuthBaseTest {

    private void goToOverview() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();
    }
    // Проверка: на странице Overview отображаются товары из корзины
    @Test
    public void checkOverviewPageElements() {
        goToOverview();

        Assert.assertTrue(checkoutOverviewPage.getItemsCount() > 0);
        Assert.assertTrue(checkoutOverviewPage.isUserInfoDisplayed());
    }
    // Проверка: кнопка Cancel возвращает пользователя на страницу товаров
    @Test
    public void checkCancelReturnsToCart() {
        goToOverview();
        checkoutOverviewPage.clickCancel();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
    // Проверка: после нажатия Finish происходит переход на страницу завершения заказа
    @Test
    public void checkFinishOrder() {
        goToOverview();
        checkoutOverviewPage.clickFinish();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
    }
}