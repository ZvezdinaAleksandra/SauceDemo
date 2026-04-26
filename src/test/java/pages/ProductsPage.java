package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Заголовок страницы
    private final By TITLE = By.cssSelector("[data-test='title']");
    // Корзина (иконка)
    private final By shoppingCart = By.cssSelector("[data-test='shopping-cart-link']");
    // Бейдж корзины
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    // Все карточки товаров
    private final By products = By.className("inventory_item");
    // Название товара
    private By productName(String name) {
        return By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space()='" + name + "']");
    }
    // Цена товара
    private By productPrice(String name) {
        return By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') and normalize-space()='" + name + "']]" +
                        "//div[@class='inventory_item_price']"
        );
    }
    // Add to cart кнопка
    private By addToCartButton(String productName) {
        return By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]" +
                        "//button"
        );
    }
    // Remove кнопка
    private By removeButton(String productName) {
        return By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]" +
                        "//button[text()='Remove']"
        );
    }
    // Открыть страницу товаров
    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }
    // Перейти в корзину
    public void openCart() {
        driver.findElement(shoppingCart).click();
    }
    // Клик по иконке корзины
    public void clickShoppingCart() {
        driver.findElement(shoppingCart).click();
    }
    // Добавить товар в корзину по имени
    public void addProductToCart(String productName) {
        driver.findElement(addToCartButton(productName)).click();
    }
    // Удалить товар из корзины по имени
    public void removeProduct(String productName) {
        driver.findElement(removeButton(productName)).click();
    }
    // Получить заголовок страницы Products
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    // Проверка отображения товара на странице
    public boolean isProductDisplayed(String name) {
        return driver.findElements(productName(name)).size() > 0;
    }
    // Получить цену товара по имени
    public String getProductPrice(String name) {
        return driver.findElement(productPrice(name)).getText();
    }
    // Проверка отображения кнопки Remove у товара
    public boolean isRemoveButtonDisplayed(String productName) {
        return driver.findElements(removeButton(productName)).size() > 0;
    }
    // Получить значение бейджа корзины (кол-во товаров)
    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }
    // Получить общее количество товаров на странице
    public int getProductsCount() {
        return driver.findElements(products).size();
    }
    // Проверка: корзина пустая (нет бейджа)
    public boolean isCartBadgeEmpty() {
        return driver.findElements(cartBadge).isEmpty();
    }
}