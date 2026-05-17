package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutCompleteTest extends BaseTest{

    private void goToCompletePage() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        checkoutInformationPage.clickContinue();
        checkoutOverviewPage.clickFinish();
    }
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"},
            testName = "Success Message Displayed",
            description = "Проверка успешного оформления заказа и отображения сообщения"
    )
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
    @Test(
            priority = 2,
            groups = {"regression", "e2e"},
            testName = "Order Completion Text",
            description = "Проверка текста завершения заказа"
    )
    public void checkCompleteOrderText() {
        goToCompletePage();

        Assert.assertTrue(
                checkoutCompletePage.getCompleteText()
                        .contains("Your order has been dispatched")
        );
    }
    @Test(
            priority = 3,
            groups = {"smoke", "regression", "e2e"},
            testName = "Back Home Navigation",
            description = "Проверка возврата на страницу товаров после завершения заказа"
    )
    public void checkBackHomeReturnsToProducts() {
        goToCompletePage();

        checkoutCompletePage.clickBackHome();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
        );
    }
}