package pages;

import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends TestBase {
    public final String URL = "https://tt-develop.quality-lab.ru";
    public final TextInput USER_NAME_FIELD_LOCATOR = new TextInput($x("//input[@id='username']"));
    public final String INCORRECT_USER_NAME = "TestUser";
    public final String INCORRECT_PASSWORD = "Password";
    public final Button LOG_IN_BUTTON_LOCATOR = new Button($x("//input[@id='_submit']"));
    public final TextInput PASSWORD_FIELD_LOCATOR = new TextInput($x("//input[@name='_password']"));
    public final String CORRECT_USER_NAME = "Сергей Оралов";
    public final String CORRECT_USER_PASSWORD = "pwd4hotelRA";
    public final Button PROFILE_IMG_LOCATOR = new Button($x("(//div[@class='avatarCover'])[1]"));
    public final String ACTUAL_USER_EMAIL = "fake+518@quality-lab.ru";

    @Step("Открыть сайт: " + URL)
    public LoginPage openWebSite() {
        open(PropertiesReader.baseUrl);
        return this;
    }

    @Step("Ввести корректный логин: " + CORRECT_USER_NAME)
    public LoginPage enterName() {
        USER_NAME_FIELD_LOCATOR.sendKeys(PropertiesReader.correct_login);
        return new LoginPage();
    }

    @Step("Ввести корректный пароль: " + CORRECT_USER_PASSWORD)
    public LoginPage enterPassword() {
        PASSWORD_FIELD_LOCATOR.sendKeys(PropertiesReader.correct_password);
        return new LoginPage();
    }

    @Step("Ввести некорректный логин: " + INCORRECT_USER_NAME)
    public LoginPage enterIncorrectName() {
        USER_NAME_FIELD_LOCATOR.sendKeys(INCORRECT_USER_NAME);
        return new LoginPage();
    }

    @Step("Ввести некорректный пароль: " + INCORRECT_PASSWORD)
    public LoginPage enterIncorrectPassword() {
        PASSWORD_FIELD_LOCATOR.sendKeys(INCORRECT_PASSWORD);
        return new LoginPage();
    }

    @Step("Нажать кнопку входа.")
    public LoginPage clickSubmitButton() {
        LOG_IN_BUTTON_LOCATOR.click();
        return this;
    }

    @Step("Кликнуть по изображению профайла.")
    public LoginPage clickProfileImg() {
        PROFILE_IMG_LOCATOR.click();
        return this;
    }

    @Step("Ввести логин используя параметризацию")
    public LoginPage enterParamLogin(String login) {
        USER_NAME_FIELD_LOCATOR.sendKeys(login);
        return this;
    }

    @Step("Ввести пароль используя параметризацию")
    public LoginPage enterParamPassword(String password) {
        PASSWORD_FIELD_LOCATOR.sendKeys(password);
        return this;
    }
}
