package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutCompletePage extends BasePage {
    // Заголовок "Thank you for your order!"
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    // Подтверждение заказа
    private final By completeText = By.cssSelector("[data-test='complete-text']");
    // Back Home кнопка
    private final By backHomeButton = By.id("back-to-products");
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    // Возврат на страницу товаров после завершения заказа
    @Step("Нажать кнопку Back Home")
    public void clickBackHome() {
        log.info("[STEP] Clicking Back Home button");
        driver.findElement(backHomeButton).click();
        log.info("Back Home button clicked");
    }
    // Проверка успешного заказа
    @Step("Проверить отображение сообщения об успешном заказе")
    public boolean isSuccessMessageDisplayed() {
        log.info("[STEP] Checking success message visibility");
        boolean result = driver.findElements(completeHeader).size() > 0;
        log.info("Success message displayed: {}", result);
        return result;
    }
    // Получение текста сообщения об успешном завершении заказа
    @Step("Получить текст сообщения об успешном завершении заказа")
    public String getSuccessMessageText() {
        log.info("[STEP] Getting success message text");
        String message = driver.findElement(completeHeader).getText();
        log.info("Success message text: {}", message);
        return message;
    }
    // Получение текста подтверждения
    @Step("Получить текст подтверждения заказа")
    public String getCompleteText() {
        log.info("[STEP] Getting complete order text");
        String text = driver.findElement(completeText).getText();
        log.info("Complete order text: {}", text);
        return text;
    }
}