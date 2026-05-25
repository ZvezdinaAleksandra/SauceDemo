package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@Epic("E2E Tests")
@Feature("Checkout Overview Functionality")
@Owner("Zvezdina Aleksandra")
public class CheckoutOverviewTest extends BaseTest {
    private void goToOverview() {
        log.info("[FLOW] Start navigation to Overview page");
        productsPage.addProductToCart("Sauce Labs Backpack");
        log.info("Product added to cart");
        productsPage.clickShoppingCart();
        log.info("Cart opened");
        cartPage.clickCheckout();
        log.info("Checkout clicked");
        checkoutInformationPage.fillForm("Alex", "Ivanov", "12345");
        log.info("Checkout information filled");
        checkoutInformationPage.clickContinue();
        log.info("Moved to Overview page");
        log.info("[FLOW] Overview page reached");
    }
    @Test(
            priority = 1,
            groups = {"smoke", "regression", "e2e"}
    )
    public void checkOverviewPageElements() {
        log.info("[TEST] checkOverviewPageElements START");
        goToOverview();
        int itemsCount = checkoutOverviewPage.getItemsCount();
        boolean userInfoDisplayed = checkoutOverviewPage.isUserInfoDisplayed();
        log.info("Items count = {}", itemsCount);
        log.info("User info displayed = {}", userInfoDisplayed);
        Assert.assertTrue(itemsCount > 0, "No items in overview");
        Assert.assertTrue(userInfoDisplayed, "User info not displayed");
        log.info("[TEST] checkOverviewPageElements END");
    }
    @Test(
            priority = 2,
            groups = {"regression"}
    )
    public void checkCancelReturnsToCart() {
        log.info("[TEST] checkCancelReturnsToCart START");
        goToOverview();
        checkoutOverviewPage.clickCancel();
        String url = driver.getCurrentUrl();
        log.info("URL after cancel = {}", url);
        Assert.assertTrue(url.contains("inventory"));
        log.info("[TEST] checkCancelReturnsToCart END");
    }
    @Test(
            priority = 3,
            groups = {"smoke", "regression", "e2e"}
    )
    public void checkFinishOrder() {
        log.info("[TEST] checkFinishOrder START");
        goToOverview();
        checkoutOverviewPage.clickFinish();
        String url = driver.getCurrentUrl();
        log.info("URL after finish = {}", url);
        Assert.assertTrue(url.contains("checkout-complete"));
        log.info("[TEST] checkFinishOrder END");
    }
}