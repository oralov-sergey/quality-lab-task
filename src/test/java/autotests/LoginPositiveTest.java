package autotests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPositiveTest extends TestBase {

    final TextBlock PROFILE_USER_NAME_LOCATOR = new TextBlock($x("(//span[@class='m-card-user__name m--font-weight-500'])"));
    final TextBlock USER_EMAIL_LOCATOR = new TextBlock($x("//span[@class='m-card-user__email m--font-weight-300 m-link']"));

    @ParameterizedTest
    @Description("Позитивный тест. Введение корректного логина и пароля.")
    @CsvSource(value={"Авто Пользователь,12345678", "Тест,Тест"})
    @Step("Вход в аккаунт и проверка пользователя")
    void initTheAccountAndCheckUser(String login, String password) {
        loginpage.openWebSite()
                .enterParamLogin(login)
                .enterParamPassword(password)
                .clickSubmitButton();
        assertEquals("https://tt-develop.quality-lab.ru/report/group/edit", WebDriverRunner.url(), "AssertionFailedError");
        loginpage.clickProfileImg();

        assertTrue(PROFILE_USER_NAME_LOCATOR.getText().contains("Пользователь"), "AssertionFailedError");
        assertEquals(loginpage.ACTUAL_USER_EMAIL, USER_EMAIL_LOCATOR.getText(), "AssertionFailedError");
    }
}
