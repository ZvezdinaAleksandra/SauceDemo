package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        driver.get("https://www.saucedemo.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    @Step("Войти в магазин с пользователем '{user}'")
    public void login(String user, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(user);

        driver.findElement(passwordField).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }
}