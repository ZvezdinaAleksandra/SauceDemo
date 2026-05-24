package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    // Завершить оформление заказа (переход на страницу завершения)
    @Step("Нажать кнопку Finish")
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    // Отменить оформление заказа (возврат на предыдущий шаг)
    @Step("Нажать кнопку Cancel")
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    // Получить количество товаров в заказе
    @Step("Получить количество товаров в заказе")
    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }

    // Проверка отображения блока с информацией о пользователе
    @Step("Проверить отображение информации о пользователе")
    public boolean isUserInfoDisplayed() {
        return driver.findElements(userInfo).size() > 0;
    }

    // Получить текст subtotal (подитог)
    @Step("Получить текст подитога заказа")
    public String getSubtotalText() {
        return driver.findElement(subtotal).getText();
    }

    // Получить текст налога (tax)
    @Step("Получить текст налога заказа")
    public String getTaxText() {
        return driver.findElement(tax).getText();
    }

    // Получить итоговую сумму заказа (total)
    @Step("Получить итоговую сумму заказа")
    public String getTotalText() {
        return driver.findElement(total).getText();
    }
}