package autotests;

import com.codeborne.selenide.WebDriverRunner;
import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({core.TestListener.class})
public class LoginPositiveTest extends TestBase {


    @Description("Позитивный тест. Введение корректного логина и пароля.")
    @Step("Вход в аккаунт и проверка пользователя")
    @Test
    @Parameters({"login", "pass"})
    public void initTheAccountAndCheckUser(String login, String password) {
        loginpage.openWebSite()
                .enterData(loginpage.USER_NAME_FIELD_LOCATOR, login)
                .enterData(loginpage.PASSWORD_FIELD_LOCATOR, password)
                .clickSubmitButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(PropertiesReader.baseUrl + "/report/group/edit", WebDriverRunner.url(), "AssertionFailedError: Url is not the same");
        accountPage.clickProfileImg();

        softAssert.assertTrue(accountPage.PROFILE_USER_NAME_LOCATOR.getText().contains("Оралов"), "AssertionFailedError: User name does not contain specified text");
        softAssert.assertEquals(loginpage.ACTUAL_USER_EMAIL, accountPage.USER_EMAIL_LOCATOR.getText(), "AssertionFailedError: User`s e-mail is not the same");
        softAssert.assertAll();
    }
}
