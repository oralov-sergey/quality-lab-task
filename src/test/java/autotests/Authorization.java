package autotests;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Authorization extends TestBase {
    final String CALENDAR_URL = "https://tt-develop.quality-lab.ru/calendar/";

    @Step("Авторизация на сайте и переход на страницу календаря: " + CALENDAR_URL)
    public void logInToQualityLabTT() {
        open(props.whichParam(loginpage.URL));
        loginpage.enterName()
                .enterPassword()
                .clickSubmitButton();
        open(CALENDAR_URL);
        ((SelenideElement) calendarPage.CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        assertEquals(CALENDAR_URL, WebDriverRunner.url(), "AssertionFailedError");
    }
}
