package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;


    @BeforeEach
    void initBrowser() {
        Configuration.startMaximized = true;
        loginpage = new LoginPage();
        calendarPage = new CalendarPage();
        this.authorization = new Authorization();
        //   WebDriverRunner.clearBrowserCache();
    }

    @AfterEach
    void closeWebDrive() {
        WebDriverRunner.closeWebDriver();
    }
}
