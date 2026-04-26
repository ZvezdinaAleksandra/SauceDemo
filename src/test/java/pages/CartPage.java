package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
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
    // Кнопка Remove для конкретного товара
    private By removeButton(String productName) {
        return By.xpath(
                "//div[@class='inventory_item_name' and normalize-space()='" + productName + "']" +
                        "/ancestor::div[@class='cart_item']" +
                        "//button[contains(text(),'Remove')]"
        );
    }
    // Открыть страницу корзины
    public void open() {
        driver.get(BASE_URL + "cart.html");
    }
    // Нажать "Continue Shopping"
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }
    // Нажать "Checkout"
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
    // Удалить товар из корзины
    public void clickRemove(String productName) {
        driver.findElement(removeButton(productName)).click();
    }
    // Получить заголовок страницы
    public String getTitleYourCart() {
        return driver.findElement(titleYourCart).getText();
    }
    // Проверка: товар есть в корзине
    public boolean isProductInCart(String productName) {
        return driver.findElements(
                By.xpath("//div[@class='inventory_item_name' and normalize-space()='" + productName + "']")
        ).size() > 0;
    }
    // Получить список цен товаров
    public List<String> getItemPrices() {
        return driver.findElements(cartItemPrice)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    // Получить текст бейджа корзины
    public String getCartBadgeText() {
        return driver.findElement(shoppingBadge).getText();
    }
    // Количество товаров в корзине
    public int getProductsCount() {
        return driver.findElements(cartItems).size();
    }
    // Проверка отображения кнопки Checkout
    public boolean isCheckoutButtonDisplayed() {
        return driver.findElements(checkoutButton).size() > 0;
    }
    // Проверка отображения кнопки Continue Shopping
    public boolean isContinueShoppingButtonDisplayed() {
        return driver.findElements(continueShoppingButton).size() > 0;
    }
}