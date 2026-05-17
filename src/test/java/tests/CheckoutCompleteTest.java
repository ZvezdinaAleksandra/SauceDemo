package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic("E2E Tests")
@Feature("Checkout Complete Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutCompleteTest extends BaseTest {

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
    @Description("Проверка финального экрана успешного заказа")
    @Story("Отображение success message")
    @Severity(SeverityLevel.CRITICAL)
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
    @Description("Проверка текста подтверждения после оформления заказа")
    @Story("Текст завершения заказа")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Проверка кнопки Back Home и редиректа на каталог")
    @Story("Навигация после завершения заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void checkBackHomeReturnsToProducts() {
        goToCompletePage();

        checkoutCompletePage.clickBackHome();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
        );
    }
}