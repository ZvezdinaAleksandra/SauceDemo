package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Log4j2
@Epic("E2E Tests")
@Feature("Checkout Complete Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutCompleteTest extends BaseTest {
    private void goToCompletePage() {
        log.info("[FLOW] Start checkout flow");
        productsPage.addProductToCart("Sauce Labs Backpack");
        log.info("Product added to cart");
        productsPage.clickShoppingCart();
        log.info("Opened cart page");
        cartPage.clickCheckout();
        log.info("Clicked Checkout");
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        log.info("Checkout form filled");
        checkoutInformationPage.clickContinue();
        log.info("Clicked Continue");
        checkoutOverviewPage.clickFinish();
        log.info("Clicked Finish - order created");
        log.info("[FLOW] Checkout flow completed");
    }
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"},
            testName = "Success Message Displayed"
    )
    public void checkSuccessMessageDisplayed() {
        log.info("[TEST START] checkSuccessMessageDisplayed");
        goToCompletePage();
        SoftAssert softAssert = new SoftAssert();
        boolean isDisplayed = checkoutCompletePage.isSuccessMessageDisplayed();
        String message = checkoutCompletePage.getSuccessMessageText();
        log.info("Success message displayed = {}", isDisplayed);
        log.info("Success message text = {}", message);
        softAssert.assertTrue(
                isDisplayed,
                "Success message is not displayed"
        );
        softAssert.assertEquals(
                message,
                "Thank you for your order!",
                "Success message text is incorrect"
        );
        softAssert.assertAll();
        log.info("[TEST END] checkSuccessMessageDisplayed");
    }
    @Test(
            priority = 2,
            groups = {"regression", "e2e"},
            testName = "Order Completion Text"
    )
    public void checkCompleteOrderText() {
        log.info("[TEST START] checkCompleteOrderText");
        goToCompletePage();
        String text = checkoutCompletePage.getCompleteText();
        log.info("Complete text = {}", text);
        Assert.assertTrue(text.contains("Your order has been dispatched"));
        log.info("[TEST END] checkCompleteOrderText");
    }
    @Test(
            priority = 3,
            groups = {"smoke", "regression", "e2e"},
            testName = "Back Home Navigation"
    )
    public void checkBackHomeReturnsToProducts() {
        log.info("[TEST START] checkBackHomeReturnsToProducts");
        goToCompletePage();
        checkoutCompletePage.clickBackHome();
        String url = driver.getCurrentUrl();
        log.info("Current URL after Back Home = {}", url);
        Assert.assertTrue(url.contains("inventory.html"));
        log.info("[TEST END] checkBackHomeReturnsToProducts");
    }
}