package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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
        return By.xpath(
                "//div[contains(@class,'inventory_item_name') and normalize-space()='" + name + "']"
        );
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
        log.info("[STEP] Открытие страницы товаров");
        driver.get(BASE_URL + "inventory.html");
        log.info("Страница товаров успешно открыта");
    }
    // Перейти в корзину
    @Step("Открыть корзину")
    public void openCart() {
        log.info("[STEP] Открытие корзины");
        driver.findElement(shoppingCart).click();
        log.info("Корзина успешно открыта");
    }
    // Клик по иконке корзины
    @Step("Нажать на иконку корзины")
    public void clickShoppingCart() {
        log.info("[STEP] Нажатие на иконку корзины");
        driver.findElement(shoppingCart).click();
        log.info("Переход в корзину выполнен");
    }
    // Добавить товар в корзину по имени
    @Step("Добавить товар '{productName}' в корзину")
    public void addProductToCart(String productName) {
        log.info("[STEP] Добавление товара в корзину: {}", productName);
        driver.findElement(
                By.xpath(String.format(addToCartPattern, productName))
        ).click();
        log.info("Товар '{}' успешно добавлен в корзину", productName);
    }
    // Удалить товар из корзины по имени
    @Step("Удалить товар '{productName}' из корзины")
    public void removeProduct(String productName) {
        log.info("[STEP] Удаление товара из корзины: {}", productName);
        driver.findElement(
                By.xpath(String.format(removeFromCartPattern, productName))
        ).click();
        log.info("Товар '{}' успешно удален из корзины", productName);
    }
    // Получить заголовок страницы Products
    @Step("Получить заголовок страницы товаров")
    public String getTitle() {
        String pageTitle = driver.findElement(title).getText();
        log.info("Заголовок страницы: {}", pageTitle);
        return pageTitle;
    }
    // Проверка отображения товара на странице
    @Step("Проверить отображение товара '{name}'")
    public boolean isProductDisplayed(String name) {
        boolean isDisplayed = driver.findElements(productName(name)).size() > 0;
        log.info("Товар '{}' отображается: {}", name, isDisplayed);
        return isDisplayed;
    }
    // Получить цену товара по имени
    @Step("Получить цену товара '{name}'")
    public String getProductPrice(String name) {
        String price = driver.findElement(productPrice(name)).getText();
        log.info("Цена товара '{}': {}", name, price);
        return price;
    }
    // Проверка отображения кнопки Remove у товара
    @Step("Проверить отображение кнопки Remove для товара '{productName}'")
    public boolean isRemoveButtonDisplayed(String productName) {
        boolean isDisplayed = driver.findElements(
                By.xpath(String.format(removeFromCartPattern, productName))
        ).size() > 0;
        log.info(
                "Кнопка Remove для товара '{}' отображается: {}",
                productName,
                isDisplayed
        );
        return isDisplayed;
    }
    // Получить значение бейджа корзины
    @Step("Получить значение бейджа корзины")
    public String getCartBadgeText() {
        String badgeText = driver.findElement(cartBadge).getText();
        log.info("Значение бейджа корзины: {}", badgeText);
        return badgeText;
    }
    // Получить общее количество товаров на странице
    @Step("Получить количество товаров на странице")
    public int getProductsCount() {
        int productsCount = driver.findElements(products).size();
        log.info("Количество товаров на странице: {}", productsCount);
        return productsCount;
    }
    // Проверка: корзина пустая
    @Step("Проверить, что корзина пустая")
    public boolean isCartBadgeEmpty() {
        boolean isEmpty = driver.findElements(cartBadge).isEmpty();
        log.info("Корзина пустая: {}", isEmpty);
        return isEmpty;
    }
}