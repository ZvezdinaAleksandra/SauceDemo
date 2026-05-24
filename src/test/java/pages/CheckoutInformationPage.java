package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    // Ввод фамилии
    @Step("Ввести фамилию: {lastName}")
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    // Ввод индекса
    @Step("Ввести почтовый индекс: {zip}")
    public void enterZipCode(String zip) {
        driver.findElement(zipCodeInput).clear();
        driver.findElement(zipCodeInput).sendKeys(zip);
    }

    // Заполнение всей формы
    @Step("Заполнить форму данными пользователя")
    public void fillForm(String firstName, String lastName, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zip);
    }

    // Нажатие кнопки Continue
    @Step("Нажать кнопку Continue")
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    // Нажатие Cancel
    @Step("Нажать кнопку Cancel")
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    // Получить текст ошибки
    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    // Проверка отображения ошибки на странице
    @Step("Проверить отображение сообщения об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
    }
}