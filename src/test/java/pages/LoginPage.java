package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class LoginPage extends TestBase {
    public final SelenideElement USER_NAME_FIELD_LOCATOR = $x("//input[@id='username']");
    public final SelenideElement z = $x("//div[@class='form-group m-form__group']");
    public final Button LOG_IN_BUTTON_LOCATOR = new Button($x("//input[@id='_submit']"));
    public final SelenideElement PASSWORD_FIELD_LOCATOR = $x("//input[@name='_password']");
    public final String ACTUAL_USER_EMAIL = "fake+518@quality-lab.ru";
    public final TextBlock MESSAGE_LOCATOR = new TextBlock($x("//div[contains(.,'Invalid credentials.')]"));

    @Step("Открыть сайт")
    public LoginPage openWebSite() {
        open(PropertiesReader.baseUrl);
        return this;
    }

    @Step("Ввести данные")
    public LoginPage enterData(SelenideElement locator, String data){
        locator.sendKeys(data);
        return this;
    }

    @Step("Нажать кнопку входа.")
    public LoginPage clickSubmitButton() {
        LOG_IN_BUTTON_LOCATOR.click();
        return this;
    }

    @Step("Получить значение value элемента")
    public String getValueAttribute(SelenideElement locator) {
       return locator.getWrappedElement().getAttribute("value");
           }

    public void catchErrorElementShouldNotBeVisible(){
        try {
            ((SelenideElement) MESSAGE_LOCATOR.getWrappedElement()).shouldNotBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
    }

    public void catchErrorElementShouldBeVisible(){
        try {
            ((SelenideElement) MESSAGE_LOCATOR.getWrappedElement()).shouldBe(Condition.visible);
        } catch (ElementNotFound e) {
            e.printStackTrace();
        }
    }
}
