package core;

import autorization.Authorization;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.CalendarPage;
import pages.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.io.IOException;

@Listeners(ScreenShooter.class)
public class TestBase {

    public static LoginPage loginpage;
    public static CalendarPage calendarPage;
    public Authorization authorization;
    public static Props props;

    @BeforeMethod
   public void initBrowser() throws IOException {
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
