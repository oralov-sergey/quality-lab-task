package autotests;

import com.codeborne.selenide.WebDriverRunner;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPositiveTest extends TestBase {

    final TextBlock PROFILE_USER_NAME_LOCATOR = new TextBlock($x("(//span[@class='m-card-user__name m--font-weight-500'])"));
    final TextBlock USER_EMAIL_LOCATOR = new TextBlock($x("//span[@class='m-card-user__email m--font-weight-300 m-link']"));

    @Description("Позитивный тест. Введение корректного логина и пароля.")
    @Step("Вход в аккаунт и проверка пользователя")
    @Test
    @Parameters ({"login", "pass"})
  public void initTheAccountAndCheckUser(String login, String password) {
        loginpage.openWebSite()
                .enterParamLogin(login)
                .enterParamPassword(password)
                .clickSubmitButton();
        Assert.assertEquals("https://tt-develop.quality-lab.ru/report/group/edit", WebDriverRunner.url(), "AssertionFailedError");
        loginpage.clickProfileImg();

        Assert.assertTrue(PROFILE_USER_NAME_LOCATOR.getText().contains("Оралов"), "AssertionFailedError");
        Assert.assertEquals(loginpage.ACTUAL_USER_EMAIL, USER_EMAIL_LOCATOR.getText(), "AssertionFailedError");
    }
}
