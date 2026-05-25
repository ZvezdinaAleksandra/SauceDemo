package tests;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.TestListener;

import java.time.Duration;

@Log4j2
@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutInformationPage checkoutInformationPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod(alwaysRun = true, description = "Настройка браузера")
    public void setUp(ITestContext iTestContext) {
        log.info("=== START TEST SETUP ===");
        driver = new EdgeDriver();
        log.info("Browser started");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // Page Objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        iTestContext.setAttribute("driver", driver);
        log.info("Opening login page and performing login");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        log.info("Login successful, setup completed");
    }
    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown() {
        log.info("Closing browser");
        if (driver != null) {
            driver.quit();
        }
        log.info("Browser closed");
    }
}