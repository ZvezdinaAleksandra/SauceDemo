package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    // Заголовок "Thank you for your order!"
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    // Подтверждение заказа
    private final By completeText = By.cssSelector("[data-test='complete-text']");
    // Back Home кнопка
    private final By backHomeButton = By.id("back-to-products");

    // Возврат на страницу товаров после завершения заказа
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
    // Проверка успешного заказа
    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(completeHeader).size() > 0;
    }
    // Получение текста сообщения об успешном завершении заказа
    public String getSuccessMessageText() {
        return driver.findElement(completeHeader).getText();
    }
    // Получение текста подтверждения
    public String getCompleteText() {
        return driver.findElement(completeText).getText();
    }
}