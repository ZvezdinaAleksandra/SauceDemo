import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class LocatorTest {

    @Test
    public void checkLocator() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");


        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // проверка логина
        Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed());

        // добавление товара
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        // переход в корзину
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();

        // проверка товара в корзине и стоимость
        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Bolt T-Shirt"
        );

        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_price")).getText(),
                "$15.99"
        );

        driver.quit();
    }
}
