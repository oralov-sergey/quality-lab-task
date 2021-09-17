package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginNegativeTest extends TestBase {
    private final TextBlock MESSAGE_LOCATOR = new TextBlock($x("(//div[contains(.,'Invalid credentials.')])[8]"));


    @Test
    @Description("Негативный тест. Введение некорректных логина и пароля.")
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
        assertEquals(loginpage.INCORRECT_USER_NAME, loginpage.USER_NAME_FIELD_LOCATOR.getAttribute("value"), "AssertionFailedError");
        assertEquals("Пароль", loginpage.PASSWORD_FIELD_LOCATOR.getAttribute("placeholder"), "AssertionFailedError");
    }

    @Test
    @Description("Негативный тест. Пароль и логин не вводится.")
    void DoNotEnterUserNameAndPassword() {
        loginpage.openWebSite()
                .clickSubmitButton();

        try {
            ((SelenideElement) this.MESSAGE_LOCATOR.getWrappedElement()).shouldNotBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
        assertEquals("https://tt-develop.quality-lab.ru/login", WebDriverRunner.url(), "AssertionFailedError");
    }


    @Test
    public void test() throws IOException {
        authorization.logIntoQualityLabByAPI();


    }


}
