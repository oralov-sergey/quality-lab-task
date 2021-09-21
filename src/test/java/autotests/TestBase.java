package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


@ExtendWith(TestResults.class)
public class TestBase {

    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;
    public static Props props;

    @RegisterExtension
    static ScreenShooterExtension screenshotemall = new ScreenShooterExtension(true).to("C:\\Users\\79817\\IdeaProjects\\quality-lab-task3\\screenshots");

    @BeforeEach
    void initBrowser() throws IOException {
        Configuration.browser = "edge";
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
