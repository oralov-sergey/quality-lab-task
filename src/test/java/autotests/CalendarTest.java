package autotests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarTest extends TestBase {



    @Override
    @BeforeEach
    void initBrowser() {
        super.initBrowser();
        authorization.logInToQualityLabTT();
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

    public void checkDaysInMonth() {
        assertTrue(calendarPage.getWorkingDays() > 0, "AssertionFailedError");
        assertTrue(calendarPage.getWeekends() > 0, "AssertionFailedError");
    }
}



