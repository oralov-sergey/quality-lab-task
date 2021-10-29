package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.Table;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$x;


public class CalendarPage {

    public final Button CHOOSE_MONTH_FIELD = new Button($x("//input[@name='filter-date']"));
    public final Button APPLY_CHANGES_BUTTON = new Button($x("//button[contains(@class,'filter')]"));
    public final Button NEXT_MONTH = new Button($x("(//span[@class='month focused active']/following::span)[1]"));
    public final Button NEXT_YEAR = new Button($x("//div[contains(@class,'m-grid m-grid--h')]"));
    public final TextBlock CURRENT_MONTH = new TextBlock($x("//span[@id='schedule-month-title']"));
    public final TextBlock CALENDAR_DOWNLOADING_MESSAGE = new TextBlock($x("//div[@id='schedule-overlay']//child::span"));
    public final String CALENDAR_ALL_WORKING_DAYS = "//td[@class='fc-event-container']//child::a";
    public final Table CALENDAR_TABLE = new Table($x("(//div[contains(@class, 'fc-view')]//child::table)[1]"));
    public final Select WORKERS = new Select($x("//select[contains(@class, 'm-select2 u')]"));

    public void selectWorker() {
        WORKERS.selectByIndex(29);
    }

    public SelenideElement getCalendarDownloadingMessageElement() {
        return ((SelenideElement) CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement());
    }

    public String getCurrentWorkMonthAndYear() {
        return CURRENT_MONTH.getText().toLowerCase(Locale.ROOT);
    }


    public int getDaysFromCalendar() {
        getCalendarDownloadingMessageElement().shouldNotBe(Condition.visible);
        List<WebElement> days = CALENDAR_TABLE.findElements(By.xpath(CALENDAR_ALL_WORKING_DAYS));
        days.removeIf(day -> day.getAttribute("class").contains("no-event"));
        return days.size();
    }


    public CalendarPage chooseNextYearIfNowDecember() {
        LocalDate MONTH = LocalDate.now();
        if (MONTH.getMonthValue() == 12) {
            NEXT_YEAR.click();
        }
        return this;
    }

    public CalendarPage chooseNextMonth() {
        CHOOSE_MONTH_FIELD.click();
        chooseNextYearIfNowDecember();
        NEXT_MONTH.click();
        APPLY_CHANGES_BUTTON.click();
        getCalendarDownloadingMessageElement().shouldNotBe(Condition.visible);
        return this;
    }

    public CalendarPage chooseAnotherWorker() {
        selectWorker();
        APPLY_CHANGES_BUTTON.click();
        getCalendarDownloadingMessageElement().shouldNotBe();
        return this;
    }
}













