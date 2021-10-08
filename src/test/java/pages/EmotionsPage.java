package pages;

import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class EmotionsPage extends TestBase {

    public static final SelenideElement REPORT_MENU_BUTTON = $x("(//i[@class='m-menu__link-icon flaticon-line-graph'])[1]");
    public static final SelenideElement TODAY_REPORT_BUTTON = $x("//span[@class='m-menu__link-text'][contains(.,'Отчет за сегодня')]");
    public static final SelenideElement HAPPY_EMOTION_BUTTON = $x("(//div[@class='image'])[1]");
    public static final SelenideElement INSPIRATION_EMOTION_BUTTON = $x("(//div[@class='image'])[2]");
    public static final SelenideElement OK_EMOTION_BUTTON = $x("(//div[@class='image'])[3]");
    public static final SelenideElement EXPECTED_POP_UP = $x("(//div[@class='modal-content'])[3]");
    public static final SelenideElement POP_UP_TITLE_SELECTOR = $x("(//h5[contains(@class,'modal-title')])[3]");
    public static final SelenideElement POP_UP_CANCEL_BUTTON = $x("(//button[@class='btn btn-secondary'])[3]");
    public static final String POP_UP_TEXT = "Вы хотите залогировать больше или меньше 8 часов, которые по графику запланированы у вас на сегодня";

    @Step("Навести на блок меню 'Отчёты'")
    public EmotionsPage pointToReportMenuIcon() {
        REPORT_MENU_BUTTON.hover();
        return this;
    }

    @Step("Выбрать и кликнуть по элементу")
    public EmotionsPage clickTheButton(SelenideElement button){
        button.click();
        return this;
    }
}