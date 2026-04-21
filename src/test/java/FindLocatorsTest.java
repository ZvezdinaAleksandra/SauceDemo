import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class FindLocatorsTest {
    @Test
    public void findLocator() {
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

        driver.findElements(By.id("root"));
        driver.findElements(By.id("login_button_container"));
        driver.findElements(By.id("login-button"));
        driver.findElements(By.id("password"));

        driver.findElements(By.name("viewport"));
        driver.findElements(By.name("user-name"));
        driver.findElements(By.name("password"));
        driver.findElements(By.name("description"));

        driver.findElements(By.className("primary_header"));
        driver.findElements(By.className("bm-burger-button"));
        driver.findElements(By.className("bm-menu-wrap"));
        driver.findElements(By.className("primary_header"));

        driver.findElements(By.tagName("noscript"));
        driver.findElements(By.tagName("script"));

        driver.findElements(By.linkText("Twitter"));
        driver.findElements(By.partialLinkText("Linked"));

        driver.findElements(By.xpath("//input[@id='username']"));
        driver.findElements(By.xpath("/button[contains(text(),'Remove')]"));
        driver.findElements(By.xpath("//title[text()='Swag Labs']"));
        driver.findElements(By.xpath("//span[contains(@data-test,'shopping-cart')]"));
        driver.findElements(By.xpath("//*[text()='Sauce Labs Bike Light']//ancestor::div"));
        driver.findElements(By.xpath("//div[@class='inventory_item']/descendant::button"));
        driver.findElements(By.xpath("//div/following::button"));
        driver.findElements(By.xpath("//button/parent::div"));
        driver.findElements(By.xpath("//span/preceding::button"));
        driver.findElements(By.xpath("//input[contains(@class,'input_error') and contains(@class,'form_input')]"));

        driver.findElements(By.cssSelector("#shopping_cart_container"));
        driver.findElements(By.cssSelector(".bm-item-list"));
        driver.findElements(By.cssSelector(".btn.btn_primary"));
        driver.findElements(By.cssSelector(".header_container .primary_header"));
        driver.findElements(By.cssSelector("span.shopping_cart_badge"));
        driver.findElements(By.cssSelector("[data-test='shopping-cart-badge']"));
        driver.findElements(By.cssSelector("div[class~='header_label']"));
        driver.findElements(By.cssSelector("[lang|='en']"));
        driver.findElements(By.cssSelector("[data-test^='add-to']"));
        driver.findElements(By.cssSelector("[data-test$='fleece-jacket']"));
        driver.findElements(By.cssSelector("[data-test*='sauce']"));


    }
}
