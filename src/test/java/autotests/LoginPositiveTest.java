package autotests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPositiveTest extends TestBase {
    final String URL = "https://tt-develop.quality-lab.ru";
    final String USER_NAME = "Авто Пользователь";
    final String USER_NAME_FIELD_LOCATOR = "//input[@id='username']";
    final String USER_PASSWORD = "12345678";
    final String PASSWORD_FIELD_LOCATOR = "//input[@name='_password']";
    final String LOGIN_BUTTON_LOCATOR = "//input[@value='Войти']";
    final String PROFILE_IMG_LOCATOR = "(//div[@class='avatarCover'])[1]";
    final String PROFILE_USER_NAME_LOCATOR = "(//span[@class='m-card-user__name m--font-weight-500'])";
    final String ACTUAL_USER_EMAIL = "1241242@m.r";
    final String USER_EMAIL_LOCATOR = "//span[@class='m-card-user__email m--font-weight-300 m-link']";


    @Test
    void initTheAccountAndCheckUser() {
        driver.get(URL);
        driver.findElement(By.xpath(USER_NAME_FIELD_LOCATOR)).sendKeys(USER_NAME);
        driver.findElement(By.xpath(PASSWORD_FIELD_LOCATOR)).sendKeys(USER_PASSWORD);
        driver.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)).click();
        assertEquals("https://tt-develop.quality-lab.ru/report/group/edit", driver.getCurrentUrl());
        driver.findElement(By.xpath(PROFILE_IMG_LOCATOR)).click();
        assertTrue(driver.findElement(By.xpath(PROFILE_USER_NAME_LOCATOR)).getText().contains("Пользователь"));
        assertEquals(ACTUAL_USER_EMAIL, driver.findElement(By.xpath(USER_EMAIL_LOCATOR)).getText());
    }
}
