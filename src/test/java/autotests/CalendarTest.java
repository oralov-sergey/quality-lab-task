package autotests;

import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CalendarTest extends TestBase {

    @Override
    @BeforeMethod
    public void initBrowser() throws IOException {
        super.initBrowser();
        authorization.logIntoQualityLabByAPI();
    }

    @Test
    @Description("Проверка календаря. Текущий месяц.")
    @Step("Провекрить текущий месяц")
    void currentMonth() {
        Assert.assertEquals(calendarPage.getCurrentWorkMonthAndYear(), calendarPage.getCurrentDate(), "AssertionFailedError");
        checkDaysInMonth();
    }

    @Test
    @Description("Проверка календаря. Смена месяца.")
    @Step("Сменить месяц")
    void changeMonth() {
        calendarPage.chooseNextMonth();
        checkDaysInMonth();
    }

    @Test
    @Description("Проверка календаря. Смена пользователя.")
    @Step("Сменить пользователя")
    void changeWorker() {
        calendarPage.chooseAnotherWorker();
        checkDaysInMonth();
    }

    @Step("Проверка будней и выходных дней")
    public void checkDaysInMonth() {
        Assert.assertTrue(calendarPage.getWorkingDays() > 0, "AssertionFailedError");
        Assert.assertTrue(calendarPage.getWeekends() > 0, "AssertionFailedError");
    }
}



