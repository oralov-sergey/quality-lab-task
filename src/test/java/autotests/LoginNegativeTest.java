package autotests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;


public class LoginNegativeTest extends TestBase {

    final String URL = "https://tt-develop.quality-lab.ru";
    final String USER_FIELD_LOCATOR = "//input[@id='username']";
    final String USER = "TestUser";
    final String PASSWORD = "Password";
    final String PASSWORD_FIELD_LOCATOR = "//input[@name='_password']";
    final String LOG_IN_BUTTON_LOCATOR = "//input[@value='Войти']";
    public final String MESSAGE_LOCATOR = "(//div[contains(.,'Invalid credentials.')])[8]";

    @Test
    void incorrectUserNameAndPassword() {
        driver.get(URL);
        driver.findElement(By.xpath(USER_FIELD_LOCATOR)).sendKeys(USER);
        driver.findElement(By.xpath(PASSWORD_FIELD_LOCATOR)).sendKeys(PASSWORD);
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.xpath(MESSAGE_LOCATOR));
        });
        driver.findElement(By.xpath(LOG_IN_BUTTON_LOCATOR)).click();
        try {
            driver.findElement(By.xpath(MESSAGE_LOCATOR));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        assertEquals(USER,driver.findElement(By.xpath(USER_FIELD_LOCATOR)).getAttribute("value"));
        assertEquals("Пароль",driver.findElement(By.xpath(PASSWORD_FIELD_LOCATOR)).getAttribute("placeholder"));
        }

    @Test
    void DoNotEnterUserNameAndPassword() {
        driver.get(URL);
        driver.findElement(By.xpath(LOG_IN_BUTTON_LOCATOR)).click();
        try {
            driver.findElement(By.xpath(MESSAGE_LOCATOR));
            fail();
        } catch (NoSuchElementException ignored) {
        }
        assertEquals("https://tt-develop.quality-lab.ru/login", driver.getCurrentUrl());
    }


}
