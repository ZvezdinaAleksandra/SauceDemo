package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutCompleteTest extends AuthBaseTest {

    private void goToCompletePage() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();
        checkoutOverviewPage.clickFinish();
    }
    // Проверка: отображение сообщения об успешном оформлении заказа
    @Test
    public void checkSuccessMessageDisplayed() {
        goToCompletePage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                checkoutCompletePage.isSuccessMessageDisplayed(),
                "Success message is not displayed"
        );
        softAssert.assertEquals(
                checkoutCompletePage.getSuccessMessageText(),
                "Thank you for your order!",
                "Success message text is incorrect"
        );
        softAssert.assertAll();
    }
    // Проверка: отображение текста подтверждения завершения заказа
    @Test
    public void checkCompleteOrderText() {
        goToCompletePage();
        Assert.assertTrue(
                checkoutCompletePage.getCompleteText().contains("Your order has been dispatched"),
                "Order confirmation text is missing"
        );
    }
    // Проверка: кнопка Back Home возвращает пользователя на страницу товаров
    @Test
    public void checkBackHomeReturnsToProducts() {
        goToCompletePage();
        checkoutCompletePage.clickBackHome();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "User was not redirected to Products page"
        );
    }
}