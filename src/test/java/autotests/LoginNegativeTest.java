package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({core.TestListener.class})
public class LoginNegativeTest extends TestBase {

    @Test
    @Description("Негативный тест. Введение некорректных логина и пароля.")
    @Step("Ввести некорректные логин и пароль")
    void incorrectUserNameAndPassword() {

        loginpage.openWebSite()
                .enterData(loginpage.USER_NAME_FIELD_LOCATOR, PropertiesReader.incorrect_login)
                .enterData(loginpage.PASSWORD_FIELD_LOCATOR, PropertiesReader.incorrect_password);
        loginpage.catchErrorElementShouldNotBeVisible();
        loginpage.clickSubmitButton();
        loginpage.catchErrorElementShouldBeVisible();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(PropertiesReader.incorrect_login, loginpage.getValueAttribute(loginpage.USER_NAME_FIELD_LOCATOR), "AssertionFailedError: Login is not the same");
        softAssert.assertTrue(loginpage.PASSWORD_FIELD_LOCATOR.is(Condition.empty), "AssertionFailedError: Password is displayed");
        softAssert.assertAll();
    }

    @Test
    @Description("Негативный тест. Пароль и логин не вводится.")
    @Step("Попытка авторизоваться не вводя логин и пароль")
    void doNotEnterUserNameAndPassword() {
        loginpage.openWebSite()
                .clickSubmitButton();
        loginpage.catchErrorElementShouldNotBeVisible();
        Assert.assertEquals("https://tt-develop.quality-lab.ru/login", WebDriverRunner.url(), "AssertionFailedError: Url is not the same");
    }
}
