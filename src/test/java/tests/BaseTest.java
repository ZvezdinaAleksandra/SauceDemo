package tests;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.PropertyReader;
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

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod(alwaysRun = true, description = "Настройка браузера + авторизация")
    @Description("Настройка браузера + авторизация")
    public void setUp(ITestContext iTestContext) {

        log.info("=== START TEST SETUP ===");

        if (user == null || password == null) {
            throw new RuntimeException("USER or PASSWORD is null. Check GitHub Secrets / env variables");
        }

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new EdgeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // Page Objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        iTestContext.setAttribute("driver", driver);

        log.info("Opening application");

        driver.get("https://www.saucedemo.com/");

        log.info("Performing login with user: {}", user);

        loginPage.login(user, password);

        Assert.assertTrue(
                productsPage.getTitle().equals("Products"),
                "Login failed or Products page not loaded"
        );

        log.info("Login successful, setup completed");
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    public void tearDown() {

        log.info("Closing browser");

        if (driver != null) {
            driver.quit();
        }

        log.info("Browser closed");
    }
}