package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    // Заголовок страницы Checkout Overview
    private final By title = By.cssSelector("[data-test='title']");
    // Список товаров в заказе
    private final By cartItems = By.className("inventory_item_name");
    // Блок с информацией о пользователе (имя, адрес и т.д.)
    private final By userInfo = By.className("summary_info");
    // Кнопка завершения заказа (Finish)
    private final By finishButton = By.id("finish");
    // Кнопка отмены заказа (Cancel)
    private final By cancelButton = By.id("cancel");
    // Подитог (subtotal)
    private final By subtotal = By.cssSelector(".summary_subtotal_label");
    // Налог (tax)
    private final By tax = By.cssSelector(".summary_tax_label");
    // Общая сумма заказа (total)
    private final By total = By.cssSelector(".summary_total_label");
    // Завершить оформление заказа
    @Step("Нажать кнопку Finish")
    public void clickFinish() {
        log.info("[STEP] Нажатие кнопки Finish");
        driver.findElement(finishButton).click();
        log.info("Кнопка Finish нажата");
    }
    // Отменить оформление заказа
    @Step("Нажать кнопку Cancel")
    public void clickCancel() {
        log.info("[STEP] Нажатие кнопки Cancel");
        driver.findElement(cancelButton).click();
        log.info("Кнопка Cancel нажата");
    }
    // Получить количество товаров в заказе
    @Step("Получить количество товаров в заказе")
    public int getItemsCount() {
        int itemsCount = driver.findElements(cartItems).size();
        log.info("Количество товаров в заказе: {}", itemsCount);
        return itemsCount;
    }
    // Проверка отображения блока с информацией о пользователе
    @Step("Проверить отображение информации о пользователе")
    public boolean isUserInfoDisplayed() {
        boolean isDisplayed = driver.findElements(userInfo).size() > 0;
        log.info("Блок информации о пользователе отображается: {}", isDisplayed);
        return isDisplayed;
    }
    // Получить текст subtotal
    @Step("Получить текст подитога заказа")
    public String getSubtotalText() {
        String subtotalText = driver.findElement(subtotal).getText();
        log.info("Subtotal заказа: {}", subtotalText);
        return subtotalText;
    }
    // Получить текст налога
    @Step("Получить текст налога заказа")
    public String getTaxText() {
        String taxText = driver.findElement(tax).getText();
        log.info("Tax заказа: {}", taxText);
        return taxText;
    }
    // Получить итоговую сумму заказа
    @Step("Получить итоговую сумму заказа")
    public String getTotalText() {
        String totalText = driver.findElement(total).getText();
        log.info("Total заказа: {}", totalText);
        return totalText;
    }
}