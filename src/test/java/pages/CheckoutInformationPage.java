package pages;

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
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    // Ввод фамилии
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    // Ввод индекса
    public void enterZipCode(String zip) {
        driver.findElement(zipCodeInput).clear();
        driver.findElement(zipCodeInput).sendKeys(zip);
    }
    // Заполнение всей формы
    public void fillForm(String firstName, String lastName, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zip);
    }
    // Нажатие кнопки Continue
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
    // Нажатие Cancel
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }
    // Получить текст ошибки
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    // Проверка отображения ошибки на странице
    public boolean isErrorDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
    }
}

