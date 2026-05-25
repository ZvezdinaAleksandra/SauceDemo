package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
@Log4j2
public class LoginPage extends BasePage {
    private final WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private final By usernameField = By.xpath("//*[@data-test='username']");
    private final By passwordField = By.xpath("//*[@data-test='password']");
    private final By loginButton = By.xpath("//*[@data-test='login-button']");
    private final By errorMessage = By.xpath("//*[@data-test='error']");

    @Step("Открыть страницу логина")
    public void open() {
        log.info("[STEP] Открытие страницы логина");
        driver.get("https://www.saucedemo.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        log.info("Страница логина успешно открыта");
    }
    @Step("Войти в магазин с пользователем '{user}'")
    public void login(String user, String password) {
        log.info("[STEP] Выполнение логина пользователем: {}", user);

        log.info("Ввод username");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(user);
        log.info("Ввод password");
        driver.findElement(passwordField).sendKeys(password);
        log.info("Нажатие кнопки Login");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        log.info("Логин выполнен");
    }
    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        String errorText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
        log.info("Текст сообщения об ошибке: {}", errorText);
        return errorText;
    }
}