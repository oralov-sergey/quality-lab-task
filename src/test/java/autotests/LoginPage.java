package autotests;

import org.junit.jupiter.api.Test;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class LoginPage extends TestBase {
    final String URL = "https://tt-develop.quality-lab.ru";
    final TextInput USER_NAME_FIELD_LOCATOR = new TextInput($x("//input[@id='username']"));
    final String INCORRECT_USER_NAME = "TestUser";
    final String INCORRECT_PASSWORD = "Password";
    final Button LOG_IN_BUTTON_LOCATOR = new Button($x("//input[@id='_submit']"));
    final TextInput PASSWORD_FIELD_LOCATOR = new TextInput($x("//input[@name='_password']"));
    final String CORRECT_USER_NAME = "Авто Пользователь";
    final String CORRECT_USER_PASSWORD = "12345678";
    final Button PROFILE_IMG_LOCATOR = new Button($x("(//div[@class='avatarCover'])[1]"));
    final String ACTUAL_USER_EMAIL = "1241242@m.r";

    public LoginPage openWebSite() {
        open(props.getUrl());
        return this;
    }

    public LoginPage enterName(String name) {
        USER_NAME_FIELD_LOCATOR.sendKeys(name);
        return new LoginPage();
    }

    public LoginPage enterPassword(String password) {
        PASSWORD_FIELD_LOCATOR.sendKeys(password);
        return new LoginPage();
    }

    public LoginPage clickSubmitButton() {
        LOG_IN_BUTTON_LOCATOR.click();
        return this;
    }

    public LoginPage clickProfileImg() {
        PROFILE_IMG_LOCATOR.click();
        return this;
    }


}
