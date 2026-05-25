package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private final By usernameField = By.xpath("//*[@data-test='username']");
    private final By passwordField = By.xpath("//*[@data-test='password']");
    private final By loginButton = By.xpath("//*[@data-test='login-button']");
    private final By errorMessage = By.xpath("//*[@data-test='error']");
    @Step("Открыть страницу логина")
    public void open() {
        log.info("[STEP] Открытие страницы логина");
        driver.get("https://www.saucedemo.com");
        log.info("Страница логина успешно открыта");
    }
    @Step("Войти в магазин с пользователем '{user}'")
    public void login(String user, String password) {
        log.info("[STEP] Выполнение логина пользователем: {}", user);
        log.info("Ввод username");
        driver.findElement(usernameField).sendKeys(user);
        log.info("Ввод password");
        driver.findElement(passwordField).sendKeys(password);
        log.info("Нажатие кнопки Login");
        driver.findElement(loginButton).click();
        log.info("Логин выполнен");
    }
    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        String errorText = driver.findElement(errorMessage).getText();
        log.info("Текст сообщения об ошибке: {}", errorText);
        return errorText;
    }
}