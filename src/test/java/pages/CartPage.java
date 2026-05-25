package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {
    // Заголовок страницы "Your Cart"
    private final By titleYourCart = By.cssSelector("[data-test='title']");
    // Кнопка "Continue Shopping"
    private final By continueShoppingButton = By.id("continue-shopping");
    // Кнопка "Checkout"
    private final By checkoutButton = By.id("checkout");
    // Все товары в корзине
    private final By cartItems = By.className("inventory_item_name");
    // Цены товаров в корзине
    private final By cartItemPrice = By.cssSelector(".inventory_item_price");
    // Бейдж корзины (количество товаров)
    private final By shoppingBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    // Кнопка Remove для конкретного товара
    private By removeButton(String productName) {
        return By.xpath(
                "//div[@class='inventory_item_name' and normalize-space()='" + productName + "']" +
                        "/ancestor::div[@class='cart_item']" +
                        "//button[contains(text(),'Remove')]"
        );
    }
    // Открыть страницу корзины
    @Step("Открыть страницу корзины")
    public void open() {
        log.info("[STEP] Opening Cart page");
        driver.get(BASE_URL + "cart.html");
        log.info("Cart page opened");
    }
    // Нажать "Continue Shopping"
    @Step("Нажать кнопку Continue Shopping")
    public void clickContinueShopping() {
        log.info("[STEP] Clicking Continue Shopping button");
        driver.findElement(continueShoppingButton).click();
        log.info("Continue Shopping button clicked");
    }
    // Нажать "Checkout"
    @Step("Нажать кнопку Checkout")
    public void clickCheckout() {
        log.info("[STEP] Clicking Checkout button");
        driver.findElement(checkoutButton).click();
        log.info("Checkout button clicked");
    }
    // Удалить товар из корзины
    @Step("Удалить товар '{productName}' из корзины")
    public void clickRemove(String productName) {
        log.info("[STEP] Removing product from cart: {}", productName);
        driver.findElement(removeButton(productName)).click();
        log.info("Product removed from cart: {}", productName);
    }
    // Получить заголовок страницы
    @Step("Получить заголовок страницы корзины")
    public String getTitleYourCart() {
        log.info("[STEP] Getting Cart page title");
        String title = driver.findElement(titleYourCart).getText();
        log.info("Cart page title: {}", title);
        return title;
    }
    // Проверка: товар есть в корзине
    @Step("Проверить наличие товара '{productName}' в корзине")
    public boolean isProductInCart(String productName) {
        log.info("[STEP] Checking product in cart: {}", productName);
        boolean result = driver.findElements(
                By.xpath("//div[@class='inventory_item_name' and normalize-space()='" + productName + "']")
        ).size() > 0;
        log.info("Product '{}' in cart: {}", productName, result);
        return result;
    }
    // Получить список цен товаров
    @Step("Получить список цен товаров в корзине")
    public List<String> getItemPrices() {
        log.info("[STEP] Getting item prices from cart");
        List<String> prices = driver.findElements(cartItemPrice)
                .stream()
                .map(WebElement::getText)
                .toList();
        log.info("Cart item prices: {}", prices);
        return prices;
    }
    // Получить текст бейджа корзины
    @Step("Получить значение бейджа корзины")
    public String getCartBadgeText() {
        log.info("[STEP] Getting cart badge text");
        String badge = driver.findElement(shoppingBadge).getText();
        log.info("Cart badge text: {}", badge);
        return badge;
    }
    // Количество товаров в корзине
    @Step("Получить количество товаров в корзине")
    public int getProductsCount() {
        log.info("[STEP] Getting products count in cart");
        int count = driver.findElements(cartItems).size();
        log.info("Products count in cart: {}", count);
        return count;
    }
    // Проверка отображения кнопки Checkout
    @Step("Проверить отображение кнопки Checkout")
    public boolean isCheckoutButtonDisplayed() {
        log.info("[STEP] Checking Checkout button visibility");
        boolean result = driver.findElements(checkoutButton).size() > 0;
        log.info("Checkout button displayed: {}", result);
        return result;
    }
    // Проверка отображения кнопки Continue Shopping
    @Step("Проверить отображение кнопки Continue Shopping")
    public boolean isContinueShoppingButtonDisplayed() {
        log.info("[STEP] Checking Continue Shopping button visibility");
        boolean result = driver.findElements(continueShoppingButton).size() > 0;
        log.info("Continue Shopping button displayed: {}", result);
        return result;
    }
}