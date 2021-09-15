package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(TestResults.class)
public class TestBase {

    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;
    public static Props props;

    @RegisterExtension
    static ScreenShooterExtension screenshotemall = new ScreenShooterExtension(true).to("C:\\Users\\79817\\IdeaProjects\\quality-lab-task3\\screenshots");

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

     /*   @AfterEach
    void closeWebDrive() throws IOException {
     //   WebDriverRunner.closeWebDriver();

    }*/
}
