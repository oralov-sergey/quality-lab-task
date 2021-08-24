package autotests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
    void currentMonth() {
        assertEquals(calendarPage.getCurrentWorkMonthAndYear(), calendarPage.getCurrentDate());
        checkDaysInMonth();

       }

    @Test
    void changeMonth() {
    calendarPage.chooseNextMonth();
        checkDaysInMonth();

        }

        @Test
    void changeWorker(){
            calendarPage.chooseAnotherWorker();
            checkDaysInMonth();
        }

        public void checkDaysInMonth(){
            assertTrue(calendarPage.getWorkingDays()>0);
            assertTrue(calendarPage.getWeekends()>0);
        }
    }



