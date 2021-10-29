package autotests;

import core.PropertiesReader;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

@Listeners({core.TestListener.class})
public class CalendarTest extends TestBase {

    @Override
    @BeforeMethod
    public void initBrowser() throws IOException {
        super.initBrowser();
        authorization.logIntoQualityLabByAPI(PropertiesReader.baseUrl + "/calendar");
    }

    @Test
    @Description("Проверка календаря. Текущий месяц.")
    @Step("Провекрить текущий месяц")
    void currentMonth() {
        Assert.assertEquals(commonActions.getCurrentDate(), calendarPage.getCurrentWorkMonthAndYear(), "AssertionFailedError: Current month is not the same.");
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
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(calendarPage.getDaysFromCalendar(), 0, "AssertionFailedError: Working days is less than 1");
        softAssert.assertNotEquals(calendarPage.getDaysFromCalendar(), 0, "AssertionFailedError: Weekends days is less than 1");
        softAssert.assertAll();
    }
}



