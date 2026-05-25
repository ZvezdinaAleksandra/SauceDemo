package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutInformationPage extends BasePage {

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }
    // Поля ввода данных пользователя
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By zipCodeInput = By.id("postal-code");
    // Кнопки управления
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    // Сообщение об ошибке
    private final By errorMessage = By.cssSelector("[data-test='error']");
    // Ввод имени
    @Step("Ввести имя: {firstName}")
    public void enterFirstName(String firstName) {
        log.info("Ввод имени: {}", firstName);
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
        log.info("Имя успешно введено");
    }
    // Ввод фамилии
    @Step("Ввести фамилию: {lastName}")
    public void enterLastName(String lastName) {
        log.info("Ввод фамилии: {}", lastName);
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        log.info("Фамилия успешно введена");
    }
    // Ввод индекса
    @Step("Ввести почтовый индекс: {zip}")
    public void enterZipCode(String zip) {
        log.info("Ввод почтового индекса: {}", zip);
        driver.findElement(zipCodeInput).clear();
        driver.findElement(zipCodeInput).sendKeys(zip);
        log.info("Почтовый индекс успешно введен");
    }
    // Заполнение всей формы
    @Step("Заполнить форму данными пользователя")
    public void fillForm(String firstName, String lastName, String zip) {
        log.info("[STEP] Заполнение checkout формы");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zip);
        log.info("Checkout форма успешно заполнена");
    }
    // Нажатие кнопки Continue
    @Step("Нажать кнопку Continue")
    public void clickContinue() {
        log.info("[STEP] Нажатие кнопки Continue");
        driver.findElement(continueButton).click();
        log.info("Кнопка Continue нажата");
    }
    // Нажатие Cancel
    @Step("Нажать кнопку Cancel")
    public void clickCancel() {
        log.info("[STEP] Нажатие кнопки Cancel");
        driver.findElement(cancelButton).click();
        log.info("Кнопка Cancel нажата");
    }
    // Получить текст ошибки
    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        String errorText = driver.findElement(errorMessage).getText();
        log.info("Текст ошибки: {}", errorText);
        return errorText;
    }
    // Проверка отображения ошибки на странице
    @Step("Проверить отображение сообщения об ошибке")
    public boolean isErrorDisplayed() {
        boolean isDisplayed = driver.findElements(errorMessage).size() > 0;
        log.info("Сообщение об ошибке отображается: {}", isDisplayed);
        return isDisplayed;
    }
}