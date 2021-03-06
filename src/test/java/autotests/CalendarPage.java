package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Table;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;


public class CalendarPage {

    final Button CHOOSE_MONTH_BUTTON = new Button($x("(//span[@class='input-group-addon'])[1]"));
    final Button APPLY_CHANGES_BUTTON = new Button($x("//button[@class='btn btn-brand m-btn m-btn--icon btn_do_filter']"));
    final Button NEXT_MONTH = new Button($x("(//span[@class='month focused active']/following::span)[1]"));
    final TextBlock CURRENT_MONTH = new TextBlock($x("//span[@id='schedule-month-title']"));
    final TextBlock CALENDAR_DOWNLOADING_MESSAGE = new TextBlock($x("//div[@id='schedule-overlay']//child::span"));
    final String CALENDAR_ALL_WORKING_DAYS = "//td[@class='fc-event-container']//child::a";
    final Table CALENDAR_TABLE = new Table($x("(//div[@class='fc-view fc-month-view fc-basic-view']//child::table)[1]"));
    final Button ANOTHER_WORKER_BUTTON = new Button($x("(//span[@class='select2-selection__rendered'])[1]"));
    final Button ANOTHER_WORKER = new Button($x("//li[@class='select2-results__option']"));


    @Step("Получение текущих месяца и года.")
    public CalendarPage getCurrentWorkMonthAndYear() {
        CURRENT_MONTH.getText();
        return this;
    }

    @Step("Получение текущей дата.")
    public CalendarPage getCurrentDate() {
        LocalDate.now().format(DateTimeFormatter.ofPattern("LLLL yyyy"));
        return this;
    }

    @Step("Получение рабочих дней.")
    public int getWorkingDays() {
        ((SelenideElement)CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        List<WebElement> days = CALENDAR_TABLE.findElements(By.xpath(CALENDAR_ALL_WORKING_DAYS));
        days.removeIf(day -> day.getCssValue("background-color").equals("rgba(234, 234, 234, 1)"));
        return days.size();
    }

    @Step("Получение выходных дней.")
    public int getWeekends() {
        ((SelenideElement)CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        List<WebElement> days = CALENDAR_TABLE.findElements(By.xpath(CALENDAR_ALL_WORKING_DAYS));
        days.removeIf(day -> day.getCssValue("background-color").equals("rgba(52, 191, 163, 1)"));
        return days.size();
    }

    @Step("Выбор следующего месяца.")
    public CalendarPage chooseNextMonth() {
        CHOOSE_MONTH_BUTTON.click();
        NEXT_MONTH.click();
        APPLY_CHANGES_BUTTON.click();
        ((SelenideElement)CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Выбор другого сотрудника.")
    public CalendarPage chooseAnotherWorker() {
        ANOTHER_WORKER_BUTTON.click();
        ANOTHER_WORKER.click();
        APPLY_CHANGES_BUTTON.click();
        ((SelenideElement)CALENDAR_DOWNLOADING_MESSAGE.getWrappedElement()).shouldNotBe(Condition.visible);
        return this;
    }
}













