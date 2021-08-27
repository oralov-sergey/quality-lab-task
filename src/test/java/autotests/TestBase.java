package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;
    public static Props props;

    @BeforeEach
    void initBrowser() {
        Configuration.startMaximized = true;
        loginpage = new LoginPage();
        calendarPage = new CalendarPage();
        props = new Props();
        this.authorization = new Authorization();
        props.setUrl();
        props.setCorrectPassword();
        props.setCorrectLogin();
        WebDriverRunner.clearBrowserCache();
    }

      /*  @AfterEach
    void closeWebDrive() {
        WebDriverRunner.closeWebDriver();
    } */
}
