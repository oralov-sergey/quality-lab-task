package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;

@Listeners({core.TestListener.class})
public class LoginNegativeTest extends TestBase {
    private final TextBlock MESSAGE_LOCATOR = new TextBlock($x("(//div[contains(.,'Invalid credentials.')])[8]"));


    @Test
    @Description("Негативный тест. Введение некорректных логина и пароля.")
    @Step("Ввести некорректные логин и пароль")
    void incorrectUserNameAndPassword() {

        loginpage.openWebSite()
                .enterIncorrectName()
                .enterIncorrectPassword();
        try {
            ((SelenideElement) this.MESSAGE_LOCATOR.getWrappedElement()).shouldNotBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
        loginpage.clickSubmitButton();
        try {
            ((SelenideElement) this.MESSAGE_LOCATOR.getWrappedElement()).shouldBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
        Assert.assertEquals(loginpage.INCORRECT_USER_NAME, loginpage.USER_NAME_FIELD_LOCATOR.getAttribute("value"), "AssertionFailedError");
        Assert.assertEquals("Пароль", loginpage.PASSWORD_FIELD_LOCATOR.getAttribute("placeholder"), "AssertionFailedError");
    }

    @Test
    @Description("Негативный тест. Пароль и логин не вводится.")
    @Step("Попытка авторизоваться не вводя логин и пароль")
    void doNotEnterUserNameAndPassword() {
        loginpage.openWebSite()
                .clickSubmitButton();

        try {
            ((SelenideElement) this.MESSAGE_LOCATOR.getWrappedElement()).shouldNotBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
        Assert.assertEquals("https://tt-develop.quality-lab.ru/login", WebDriverRunner.url(), "AssertionFailedError");
    }
}
