package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.get("https://www.saucedemo.com");
    }

    @Step("Войти в магазин с пользователем '{user}'")
    public void login(String user, String password) {
        driver.findElement(usernameField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}