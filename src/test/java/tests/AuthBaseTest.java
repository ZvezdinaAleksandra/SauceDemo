package tests;

import org.testng.annotations.BeforeMethod;

public class AuthBaseTest extends BaseTest{

        @BeforeMethod
        public void login() {
            loginPage.open();
            loginPage.login("standard_user","secret_sauce");
        }
    }
