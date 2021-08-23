package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.jupiter.api.*;
import ru.yandex.qatools.htmlelements.element.TextBlock;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginNegativeTest extends TestBase {
    private final TextBlock MESSAGE_LOCATOR = new TextBlock($x("(//div[contains(.,'Invalid credentials.')])[8]"));

    @Test
    void incorrectUserNameAndPassword() {

        loginpage.openWebSite()
                .enterName(loginpage.INCORRECT_USER_NAME)
                .enterPassword(loginpage.INCORRECT_PASSWORD);
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
        assertEquals(loginpage.INCORRECT_USER_NAME, loginpage.USER_NAME_FIELD_LOCATOR.getAttribute("value"));
        assertEquals("Пароль", loginpage.PASSWORD_FIELD_LOCATOR.getAttribute("placeholder"));
    }


    @Test
    void DoNotEnterUserNameAndPassword() {
        loginpage.openWebSite()
                .clickSubmitButton();

        try {
            ((SelenideElement) this.MESSAGE_LOCATOR.getWrappedElement()).shouldNotBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
        assertEquals("https://tt-develop.quality-lab.ru/login", WebDriverRunner.url());
    }
}
