package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

            public void open() {
                driver.get ("https://www.saucedemo.com");
            }
            public void login(String user,String password){
                driver.findElement(USERNAME_FIELD).sendKeys(user);
                driver.findElement(PASSWORD_FIELD).sendKeys(password);
                driver.findElement(LOGIN_BUTTON).click();
            }
            public String getErrorMessage () {
                return driver.findElement(ERROR_MESSAGE).getText();
            }
}
