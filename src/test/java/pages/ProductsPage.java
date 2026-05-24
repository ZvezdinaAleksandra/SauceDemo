package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Заголовок страницы
    private final By title = By.cssSelector("[data-test='title']");

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
    private final String addToCartPattern =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    // Remove кнопка
    private final String removeFromCartPattern =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']";

    // Открыть страницу товаров
    @Step("Открыть страницу товаров")
    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    // Перейти в корзину
    @Step("Открыть корзину")
    public void openCart() {
        driver.findElement(shoppingCart).click();
    }

    // Клик по иконке корзины
    @Step("Нажать на иконку корзины")
    public void clickShoppingCart() {
        driver.findElement(shoppingCart).click();
    }

    // Добавить товар в корзину по имени
    @Step("Добавить товар '{productName}' в корзину")
    public void addProductToCart(String productName) {
        driver.findElement(
                By.xpath(String.format(addToCartPattern, productName))
        ).click();
    }

    // Удалить товар из корзины по имени
    @Step("Удалить товар '{productName}' из корзины")
    public void removeProduct(String productName) {
        driver.findElement(
                By.xpath(String.format(removeFromCartPattern, productName))
        ).click();
    }

    // Получить заголовок страницы Products
    @Step("Получить заголовок страницы товаров")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    // Проверка отображения товара на странице
    @Step("Проверить отображение товара '{name}'")
    public boolean isProductDisplayed(String name) {
        return driver.findElements(productName(name)).size() > 0;
    }

    // Получить цену товара по имени
    @Step("Получить цену товара '{name}'")
    public String getProductPrice(String name) {
        return driver.findElement(productPrice(name)).getText();
    }

    // Проверка отображения кнопки Remove у товара
    @Step("Проверить отображение кнопки Remove для товара '{productName}'")
    public boolean isRemoveButtonDisplayed(String productName) {
        return driver.findElements(
                By.xpath(String.format(removeFromCartPattern, productName))
        ).size() > 0;
    }

    // Получить значение бейджа корзины (кол-во товаров)
    @Step("Получить значение бейджа корзины")
    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }

    // Получить общее количество товаров на странице
    @Step("Получить количество товаров на странице")
    public int getProductsCount() {
        return driver.findElements(products).size();
    }

    // Проверка: корзина пустая (нет бейджа)
    @Step("Проверить, что корзина пустая")
    public boolean isCartBadgeEmpty() {
        return driver.findElements(cartBadge).isEmpty();
    }
}