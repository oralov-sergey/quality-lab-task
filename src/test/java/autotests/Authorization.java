package autotests;

import com.codeborne.selenide.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Authorization extends TestBase {
    final String CALENDAR_URL = "https://tt-develop.quality-lab.ru/calendar/";

    void logInToQualityLabTT() {
        open(loginpage.URL);
        loginpage.enterName(loginpage.CORRECT_USER_NAME)
                .enterPassword(loginpage.CORRECT_USER_PASSWORD)
                .clickSubmitButton();
        open(CALENDAR_URL);
        ((SelenideElement) calendarPage.CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        assertEquals(CALENDAR_URL, WebDriverRunner.url());
    }
}
