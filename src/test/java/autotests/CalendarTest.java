package autotests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CalendarTest extends TestBase {


    @Override
    @BeforeEach
    void initBrowser() throws IOException {
        super.initBrowser();
        authorization.logIntoQualityLabByAPI();
    }


    @Test

    @Description("Проверка календаря. Текущий месяц.")
    void currentMonth() {
        assertEquals(calendarPage.getCurrentWorkMonthAndYear(), calendarPage.getCurrentDate(), "AssertionFailedError");
        checkDaysInMonth();
    }


    @Test

    @Description("Проверка календаря. Смена месяца.")
    void changeMonth() {
        calendarPage.chooseNextMonth();
        checkDaysInMonth();
    }


    @Test

    @Description("Проверка календаря. Смена пользователя.")
    void changeWorker() {
        calendarPage.chooseAnotherWorker();
        checkDaysInMonth();
    }

    @Step("Проверка наличия рабочих и выходных дней.")
    public void checkDaysInMonth() {
        assertTrue(calendarPage.getWorkingDays() > 0, "AssertionFailedError");
        assertTrue(calendarPage.getWeekends() > 0, "AssertionFailedError");
    }
}



